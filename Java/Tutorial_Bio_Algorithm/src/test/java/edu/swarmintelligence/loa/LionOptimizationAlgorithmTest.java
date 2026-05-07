package edu.swarmintelligence.loa;

import edu.swarmintelligence.loa.config.LoaConfig;
import edu.swarmintelligence.loa.model.Lion;
import edu.swarmintelligence.loa.model.Pride;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test suite for the Lion Optimization Algorithm.
 *
 * <p>The tests cover configuration validation, population initialization,
 * deterministic behaviour using seeds, population consistency after optimization,
 * search-space boundaries and structured CSV logging.</p>
 */
class LionOptimizationAlgorithmTest {
    @TempDir
    Path tempDir;

    @Test
    void rejectsInvalidPopulationSize() {
        assertThatThrownBy(() -> LoaConfig.defaultFor(
                0,
                10,
                2,
                AckleyFunction.minBounds(2),
                AckleyFunction.maxBounds(2),
                42L
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsInvalidDimensions() {
        assertThatThrownBy(() -> LoaConfig.defaultFor(
                10,
                10,
                0,
                new double[0],
                new double[0],
                42L
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsBoundsThatDoNotMatchDimensions() {
        assertThatThrownBy(() -> LoaConfig.defaultFor(
                10,
                10,
                2,
                new double[]{-1.0},
                new double[]{1.0},
                42L
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsLowerBoundsGreaterOrEqualToUpperBounds() {
        assertThatThrownBy(() -> LoaConfig.defaultFor(
                10,
                10,
                2,
                new double[]{1.0, -1.0},
                new double[]{1.0, 1.0},
                42L
        )).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Verifies that every pride receives exactly one male during initialization.
     * This is important because mating and territorial defense depend on resident males.
     */
    @Test
    void initializesEachPrideWithExactlyOneMale() {
        final LionOptimizationAlgorithm algorithm = createAlgorithm(30, 10, 2, 42L);

        for (final Pride pride : algorithm.prides) {
            final long males = pride.members().stream()
                    .filter(Lion::isMale)
                    .count();

            assertThat(males).isEqualTo(1);
        }
    }

    @Test
    void initializesConfiguredPopulationSize() {
        final LionOptimizationAlgorithm algorithm = createAlgorithm(30, 10, 2, 42L);

        assertThat(algorithm.totalLions()).isEqualTo(30);
    }

    @Test
    void preservesPopulationSizeAfterOptimization() {
        final LionOptimizationAlgorithm algorithm = createAlgorithm(30, 25, 2, 42L);

        algorithm.optimize();

        assertThat(algorithm.totalLions()).isEqualTo(30);
    }

    @Test
    void keepsBestPositionInsideSearchBounds() {
        final int dimensions = 2;
        final LionOptimizationAlgorithm algorithm = createAlgorithm(30, 50, dimensions, 42L);

        final double[] best = algorithm.optimize();

        assertThat(best).hasSize(dimensions);

        for (final double value : best) {
            assertThat(value)
                    .isGreaterThanOrEqualTo(AckleyFunction.DEFAULT_MIN)
                    .isLessThanOrEqualTo(AckleyFunction.DEFAULT_MAX);
        }
    }

    /**
     * Verifies deterministic behaviour for equal seeds.
     * This allows reproducible evaluation and easier debugging of the stochastic algorithm.
     */
    @Test
    void producesSameResultForSameSeed() {
        final LionOptimizationAlgorithm first = createAlgorithm(30, 50, 2, 42L);
        final LionOptimizationAlgorithm second = createAlgorithm(30, 50, 2, 42L);

        final double[] firstBest = first.optimize();
        final double[] secondBest = second.optimize();

        assertThat(firstBest).containsExactly(secondBest);
    }

    @Test
    void improvesOrKeepsGlobalBestDuringOptimization() {
        final LionOptimizationAlgorithm algorithm = createAlgorithm(30, 50, 2, 42L);

        final double initialBest = algorithm.globalBestFitness;

        algorithm.optimize();

        assertThat(algorithm.globalBestFitness).isLessThanOrEqualTo(initialBest);
    }

    @Test
    void supportsSmallPopulationAtLeastOneLionPerPride() {
        final LoaConfig config = new LoaConfig(
                5,
                10,
                2,
                AckleyFunction.minBounds(2),
                AckleyFunction.maxBounds(2),
                5,
                1.0,
                0.8,
                0.1,
                42L,
                false,
                null
        );

        final LionOptimizationAlgorithm algorithm = new LionOptimizationAlgorithm(config, new AckleyFunction());

        assertThat(algorithm.prides).hasSize(5);
        assertThat(algorithm.prides).allSatisfy(pride -> assertThat(pride.members()).isNotEmpty());
    }

    /**
     * Verifies that a complete CSV log is created for an Ackley run.
     * The log must be machine-readable and contain the required convergence and memory columns.
     *
     * @throws Exception if the temporary log file cannot be read
     */
    @Test
    void writesStructuredCsvLogFile() throws Exception {
        final Path logFile = tempDir.resolve("algorithm_run.log");

        final LoaConfig config = LoaConfig
                .defaultFor(
                        10,
                        3,
                        2,
                        AckleyFunction.minBounds(2),
                        AckleyFunction.maxBounds(2),
                        42L
                )
                .withLogging(true, logFile);

        final LionOptimizationAlgorithm algorithm = new LionOptimizationAlgorithm(config, new AckleyFunction());

        algorithm.optimize();

        assertThat(logFile).exists();

        final List<String> lines = Files.readAllLines(logFile);

        assertThat(lines).isNotEmpty();
        assertThat(lines.getFirst()).isEqualTo(
                "iteration;agentId;positionBefore;positionAfter;personalBest;"
                        + "personalBestFitness;globalBest;globalBestFitness;popAvgFitness;popStdDev;distToOptimum"
        );

        assertThat(lines).hasSizeGreaterThan(1);
        assertThat(lines.get(1).split(";")).hasSize(11);
    }

    private static LionOptimizationAlgorithm createAlgorithm(
            final int populationSize,
            final int maxIterations,
            final int dimensions,
            final long seed
    ) {
        final LoaConfig config = LoaConfig.defaultFor(
                populationSize,
                maxIterations,
                dimensions,
                AckleyFunction.minBounds(dimensions),
                AckleyFunction.maxBounds(dimensions),
                seed
        );

        return new LionOptimizationAlgorithm(config, new AckleyFunction());
    }
}