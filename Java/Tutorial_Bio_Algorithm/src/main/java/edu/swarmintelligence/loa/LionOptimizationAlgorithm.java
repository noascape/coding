package edu.swarmintelligence.loa;

import edu.swarmintelligence.loa.config.LoaConfig;
import edu.swarmintelligence.loa.function.ObjectiveFunction;
import edu.swarmintelligence.loa.model.Cub;
import edu.swarmintelligence.loa.model.Lion;
import edu.swarmintelligence.loa.model.PopulationStats;
import edu.swarmintelligence.loa.model.Pride;
import edu.swarmintelligence.loa.types.Gender;
import edu.swarmintelligence.loa.types.Role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Refactored Java implementation of the Lion Optimization Algorithm.
 *
 * <p>The algorithm is designed for minimization problems. It models pride-based and
 * nomadic lion behaviour by applying hunting, roaming, territorial defense, mating,
 * cub growth, nomad movement, takeover, migration and population control.</p>
 */
public final class LionOptimizationAlgorithm {
    private static final double ROAMING_PERCENTAGE = 0.10;
    private static final double MATING_RATE = 0.40;
    private static final double MUTATION_PROBABILITY = 0.10;
    private static final double CUB_GROWTH_TOWARDS_MOTHER_PROBABILITY = 0.50;

    private static int nextId = 1;

    final List<Pride> prides = new ArrayList<>();
    final List<Lion> nomads = new ArrayList<>();

    private final LoaConfig config;
    private final ObjectiveFunction objective;
    private final Random random;

    double globalBestFitness = Double.POSITIVE_INFINITY;
    double[] globalBestPosition;

    /**
     * Creates and initializes one LOA instance.
     *
     * @param config configuration of population, search space, seed and logging
     * @param objective objective function to minimize
     * @throws NullPointerException if config or objective is null
     */
    public LionOptimizationAlgorithm(final LoaConfig config, final ObjectiveFunction objective) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.objective = Objects.requireNonNull(objective, "objective must not be null");
        this.random = new Random(config.seed());

        initializePrides();
        initializePopulation();
    }

    /**
     * Executes all configured optimization iterations.
     *
     * @return copy of the best position found during optimization
     * @throws IllegalStateException if the optional log file cannot be written
     */
    public double[] optimize() {
        try (AlgorithmLogger logger = AlgorithmLogger.create(config.logFile(), config.loggingEnabled())) {
            for (int iteration = 1; iteration <= config.maxIterations(); iteration++) {
                executeIteration(iteration, logger);
                updateGlobalBest();
            }

            return globalBestPosition.clone();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to write algorithm log", exception);
        }
    }

    private void executeIteration(final int iteration, final AlgorithmLogger logger) throws IOException {
        for (final Pride pride : prides) {
            final double[] centre = pride.territoryCentre(config.dimensions());

            for (final Lion lioness : pride.females()) {
                moveAndLog(iteration, lioness, () -> huntingCandidate(lioness, pride.prey(), centre), logger);
            }

            pride.updatePrey();

            pride.male().ifPresent(male -> {
                try {
                    moveAndLog(iteration, male, () -> roamingCandidate(male), logger);
                } catch (IOException exception) {
                    throw new LoggingRuntimeException(exception);
                }
            });

            pride.updatePrey();
            defendTerritory(pride);
            pride.updatePrey();
        }

        final List<Cub> cubs = new ArrayList<>();

        for (final Pride pride : prides) {
            cubs.addAll(mate(pride));
        }

        for (final Cub cub : cubs) {
            moveAndLog(iteration, cub.lion(), () -> cubGrowthCandidate(cub), logger);
            nomads.add(cub.lion());
        }

        final Lion bestNomad = bestNomad();

        for (final Lion nomad : new ArrayList<>(nomads)) {
            moveAndLog(iteration, nomad, () -> nomadMovementCandidate(nomad, bestNomad), logger);
        }

        takeover();
        migrateFemales();
        populationControl();
        ensureEveryPrideHasMale();
        prides.forEach(Pride::updatePrey);
    }

    /**
     * Applies a movement operator, accepts improvements and writes one log entry.
     *
     * @param iteration current iteration number
     * @param lion affected agent
     * @param factory movement candidate factory
     * @param logger optional CSV logger
     * @throws IOException if logging fails
     */
    private void moveAndLog(
            final int iteration,
            final Lion lion,
            final CandidateFactory factory,
            final AlgorithmLogger logger
    ) throws IOException {
        final double[] before = lion.position().clone();
        final double[] candidate = factory.create();

        tryImprove(lion, candidate);

        final PopulationStats stats = populationStats();

        logger.log(new AlgorithmLogger.IterationLogEntry(
                iteration,
                lion.id(),
                before,
                lion.position(),
                lion.personalBestPosition(),
                lion.personalBestFitness(),
                globalBestPosition,
                globalBestFitness,
                stats.averageFitness(),
                stats.standardDeviation(),
                distanceToOptimum(globalBestPosition)
        ));
    }

    private double[] huntingCandidate(final Lion lion, final double[] prey, final double[] centre) {
        final double[] candidate = new double[config.dimensions()];
        final double r1 = random.nextDouble();
        final double r2 = random.nextDouble();

        for (int d = 0; d < config.dimensions(); d++) {
            candidate[d] = clamp(lion.position()[d]
                    + r1 * (prey[d] - lion.position()[d])
                    + r2 * (centre[d] - lion.position()[d]), d);
        }

        return candidate;
    }

    private double[] roamingCandidate(final Lion male) {
        final double[] candidate = new double[config.dimensions()];

        for (int d = 0; d < config.dimensions(); d++) {
            final double range = config.maxBounds()[d] - config.minBounds()[d];
            final double step = random.nextDouble(-1.0, 1.0) * ROAMING_PERCENTAGE * range;
            candidate[d] = clamp(male.position()[d] + step, d);
        }

        return candidate;
    }

    private void defendTerritory(final Pride pride) {
        final Lion residentMale = pride.male().orElse(null);
        final List<Lion> nomadMales = nomads.stream().filter(Lion::isMale).toList();

        if (residentMale == null || nomadMales.isEmpty()) {
            return;
        }

        final Lion challenger = nomadMales.get(random.nextInt(nomadMales.size()));

        if (challenger.fitness() < residentMale.fitness()) {
            nomads.remove(challenger);
            pride.members().remove(residentMale);

            residentMale.becomeNomad();
            nomads.add(residentMale);

            challenger.becomePrideMember(pride.id());
            pride.members().add(challenger);
        }
    }

    private List<Cub> mate(final Pride pride) {
        final Lion father = pride.male().orElse(null);
        final List<Lion> females = new ArrayList<>(pride.females());

        if (father == null || females.isEmpty()) {
            return List.of();
        }

        Collections.shuffle(females, random);

        final int selectedMothers = Math.max(1, (int) Math.ceil(MATING_RATE * females.size()));
        final List<Cub> cubs = new ArrayList<>();

        for (int i = 0; i < Math.min(selectedMothers, females.size()); i++) {
            final Lion mother = females.get(i);
            cubs.addAll(crossoverAndMutate(mother, father));
        }

        return cubs;
    }

    private List<Cub> crossoverAndMutate(final Lion mother, final Lion father) {
        int pointA = random.nextInt(config.dimensions());
        int pointB = random.nextInt(config.dimensions());

        if (pointA > pointB) {
            final int temp = pointA;
            pointA = pointB;
            pointB = temp;
        }

        final double[] childOne = new double[config.dimensions()];
        final double[] childTwo = new double[config.dimensions()];

        for (int d = 0; d < config.dimensions(); d++) {
            final boolean useFatherSegment = d >= pointA && d <= pointB;
            childOne[d] = useFatherSegment ? father.position()[d] : mother.position()[d];
            childTwo[d] = useFatherSegment ? mother.position()[d] : father.position()[d];
        }

        mutate(childOne);
        mutate(childTwo);

        final Lion cubOne = new Lion(nextAgentId(), childOne, objective.evaluate(childOne), randomGender());
        final Lion cubTwo = new Lion(nextAgentId(), childTwo, objective.evaluate(childTwo), randomGender());

        cubOne.becomeNomad();
        cubTwo.becomeNomad();

        return List.of(new Cub(cubOne, mother, father), new Cub(cubTwo, mother, father));
    }

    private void mutate(final double[] position) {
        for (int d = 0; d < config.dimensions(); d++) {
            if (random.nextDouble() < MUTATION_PROBABILITY) {
                position[d] = random.nextDouble(config.minBounds()[d], config.maxBounds()[d]);
            }
        }
    }

    private double[] cubGrowthCandidate(final Cub cub) {
        final Lion parent = random.nextDouble() < CUB_GROWTH_TOWARDS_MOTHER_PROBABILITY
                ? cub.mother()
                : cub.father();

        final double[] candidate = new double[config.dimensions()];

        for (int d = 0; d < config.dimensions(); d++) {
            candidate[d] = clamp(cub.lion().position()[d]
                    + random.nextDouble() * (parent.position()[d] - cub.lion().position()[d]), d);
        }

        return candidate;
    }

    private double[] nomadMovementCandidate(final Lion nomad, final Lion bestNomad) {
        final Lion target = selectNomadTarget(nomad, bestNomad);
        final double[] candidate = new double[config.dimensions()];

        for (int d = 0; d < config.dimensions(); d++) {
            candidate[d] = clamp(nomad.position()[d]
                    + random.nextDouble() * (target.position()[d] - nomad.position()[d]), d);
        }

        return candidate;
    }

    private Lion selectNomadTarget(final Lion nomad, final Lion bestNomad) {
        if (nomad.isFemale() && bestNomad != null) {
            return bestNomad;
        }

        final List<Lion> others = nomads.stream()
                .filter(other -> other != nomad)
                .toList();

        return others.isEmpty() ? nomad : others.get(random.nextInt(others.size()));
    }

    private void takeover() {
        for (final Pride pride : prides) {
            if (pride.members().isEmpty()) {
                continue;
            }

            final Lion weakest = pride.members().stream()
                    .max(Comparator.comparingDouble(Lion::fitness))
                    .orElseThrow();

            final Lion bestSameGenderNomad = nomads.stream()
                    .filter(nomad -> nomad.gender() == weakest.gender())
                    .min(Comparator.comparingDouble(Lion::fitness))
                    .orElse(null);

            if (bestSameGenderNomad != null && bestSameGenderNomad.fitness() < weakest.fitness()) {
                exchangePrideAndNomad(pride, weakest, bestSameGenderNomad);
            }
        }
    }

    private void exchangePrideAndNomad(final Pride pride, final Lion prideLion, final Lion nomadLion) {
        pride.members().remove(prideLion);
        nomads.remove(nomadLion);

        prideLion.becomeNomad();
        nomads.add(prideLion);

        nomadLion.becomePrideMember(pride.id());
        pride.members().add(nomadLion);
    }

    private void migrateFemales() {
        final List<Lion> females = allLions().stream()
                .filter(Lion::isFemale)
                .toList();

        final List<Lion> shuffled = new ArrayList<>(females);
        Collections.shuffle(shuffled, random);

        final int migrations = (int) Math.round(config.migrationRate() * shuffled.size());

        for (int i = 0; i < Math.min(migrations, shuffled.size()); i++) {
            final Lion female = shuffled.get(i);

            if (female.role() == Role.PRIDE) {
                prides.get(female.prideId()).members().remove(female);
                female.becomeNomad();
                nomads.add(female);
            } else if (!prides.isEmpty()) {
                nomads.remove(female);
                final Pride newPride = prides.get(random.nextInt(prides.size()));
                female.becomePrideMember(newPride.id());
                newPride.members().add(female);
            }
        }
    }

    private void populationControl() {
        while (totalLions() > config.populationSize()) {
            final Lion worstNomad = nomads.stream()
                    .max(Comparator.comparingDouble(Lion::fitness))
                    .orElse(null);

            if (worstNomad != null) {
                nomads.remove(worstNomad);
                continue;
            }

            final Lion worstPrideFemale = prides.stream()
                    .flatMap(pride -> pride.members().stream())
                    .filter(Lion::isFemale)
                    .max(Comparator.comparingDouble(Lion::fitness))
                    .orElse(null);

            if (worstPrideFemale == null) {
                break;
            }

            prides.get(worstPrideFemale.prideId()).members().remove(worstPrideFemale);
        }
    }

    private void ensureEveryPrideHasMale() {
        for (final Pride pride : prides) {
            if (pride.male().isPresent()) {
                continue;
            }

            final Lion candidate = nomads.stream()
                    .filter(Lion::isMale)
                    .min(Comparator.comparingDouble(Lion::fitness))
                    .orElse(null);

            if (candidate != null) {
                nomads.remove(candidate);
                candidate.becomePrideMember(pride.id());
                pride.members().add(candidate);
            }
        }
    }

    /**
     * Applies greedy acceptance and updates the personal best of the agent.
     *
     * @param lion moved agent
     * @param candidate candidate position generated by an LOA operator
     */
    private void tryImprove(final Lion lion, final double[] candidate) {
        final double candidateFitness = objective.evaluate(candidate);

        if (candidateFitness < lion.fitness()) {
            lion.moveTo(candidate, candidateFitness);
        }

        if (lion.fitness() < lion.personalBestFitness()) {
            lion.updatePersonalBest();
        }
    }

    private void initializePrides() {
        IntStream.range(0, config.numberOfPrides())
                .mapToObj(Pride::new)
                .forEach(prides::add);
    }

    private void initializePopulation() {
        for (int i = 0; i < config.populationSize(); i++) {
            final double[] position = randomPosition();
            final Lion lion = new Lion(nextAgentId(), position, objective.evaluate(position), Gender.FEMALE);

            if (i < pridePopulationSize()) {
                final Pride pride = prides.get(i % prides.size());
                lion.becomePrideMember(pride.id());
                pride.members().add(lion);
            } else {
                lion.becomeNomad();
                nomads.add(lion);
            }
        }

        assignInitialGenders();
        prides.forEach(Pride::updatePrey);
        updateGlobalBest();
    }

    private int pridePopulationSize() {
        final int requested = (int) Math.round(config.populationSize() * config.prideRatio());
        return Math.min(config.populationSize(), Math.max(config.numberOfPrides(), requested));
    }

    private void assignInitialGenders() {
        for (final Pride pride : prides) {
            final Lion best = pride.members().stream()
                    .min(Comparator.comparingDouble(Lion::fitness))
                    .orElseThrow();

            best.changeGender(Gender.MALE);

            pride.members().stream()
                    .filter(lion -> lion != best)
                    .forEach(lion -> lion.changeGender(Gender.FEMALE));
        }

        final int nomadFemales = (int) Math.round(config.nomadFemaleRatio() * nomads.size());

        for (int i = 0; i < nomads.size(); i++) {
            nomads.get(i).changeGender(i < nomadFemales ? Gender.FEMALE : Gender.MALE);
        }
    }

    private double[] randomPosition() {
        final double[] position = new double[config.dimensions()];

        for (int d = 0; d < config.dimensions(); d++) {
            position[d] = random.nextDouble(config.minBounds()[d], config.maxBounds()[d]);
        }

        return position;
    }

    private void updateGlobalBest() {
        allLions().stream()
                .min(Comparator.comparingDouble(Lion::fitness))
                .ifPresent(best -> {
                    if (best.fitness() < globalBestFitness) {
                        globalBestFitness = best.fitness();
                        globalBestPosition = best.position().clone();
                    }
                });
    }

    int totalLions() {
        return allLions().size();
    }

    private List<Lion> allLions() {
        final List<Lion> result = new ArrayList<>(nomads);
        prides.forEach(pride -> result.addAll(pride.members()));
        return result;
    }

    private Lion bestNomad() {
        return nomads.stream()
                .min(Comparator.comparingDouble(Lion::fitness))
                .orElse(null);
    }

    private PopulationStats populationStats() {
        final List<Lion> lions = allLions();

        final double average = lions.stream()
                .mapToDouble(Lion::fitness)
                .average()
                .orElse(0.0);

        final double variance = lions.stream()
                .mapToDouble(lion -> Math.pow(lion.fitness() - average, 2.0))
                .average()
                .orElse(0.0);

        return new PopulationStats(average, Math.sqrt(variance));
    }

    private double distanceToOptimum(final double[] position) {
        double sum = 0.0;

        for (final double value : position) {
            sum += value * value;
        }

        return Math.sqrt(sum);
    }

    private Gender randomGender() {
        return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    private double clamp(final double value, final int dimension) {
        return Math.max(config.minBounds()[dimension], Math.min(config.maxBounds()[dimension], value));
    }

    private static int nextAgentId() {
        return nextId++;
    }

    @FunctionalInterface
    private interface CandidateFactory {
        double[] create();
    }

    private static final class LoggingRuntimeException extends RuntimeException {
        private LoggingRuntimeException(final IOException cause) {
            super(cause);
        }
    }
}