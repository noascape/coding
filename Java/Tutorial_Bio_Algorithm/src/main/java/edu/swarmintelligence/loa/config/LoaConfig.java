package edu.swarmintelligence.loa.config;

import java.nio.file.Path;

/**
 * Immutable configuration for one LOA run.
 *
 * @param populationSize number of lions preserved by population control
 * @param maxIterations number of optimization iterations
 * @param dimensions number of decision variables
 * @param minBounds lower bounds per dimension
 * @param maxBounds upper bounds per dimension
 * @param numberOfPrides number of social prides
 * @param prideRatio share of population initially assigned to prides
 * @param nomadFemaleRatio female ratio among initial nomads
 * @param migrationRate share of females migrated per iteration
 * @param seed deterministic random seed
 * @param loggingEnabled enables CSV logging
 * @param logFile path to the structured log file
 */
public record LoaConfig(
        int populationSize,
        int maxIterations,
        int dimensions,
        double[] minBounds,
        double[] maxBounds,
        int numberOfPrides,
        double prideRatio,
        double nomadFemaleRatio,
        double migrationRate,
        long seed,
        boolean loggingEnabled,
        Path logFile
) {
    public LoaConfig {
        if (populationSize <= 0) throw new IllegalArgumentException("populationSize must be positive");
        if (maxIterations <= 0) throw new IllegalArgumentException("maxIterations must be positive");
        if (dimensions <= 0) throw new IllegalArgumentException("dimensions must be positive");
        if (numberOfPrides <= 0) throw new IllegalArgumentException("numberOfPrides must be positive");
        if (populationSize < numberOfPrides) throw new IllegalArgumentException("populationSize must be >= numberOfPrides");

        if (minBounds == null || maxBounds == null || minBounds.length != dimensions || maxBounds.length != dimensions) {
            throw new IllegalArgumentException("bounds must match dimensions");
        }

        for (int d = 0; d < dimensions; d++) {
            if (minBounds[d] >= maxBounds[d]) {
                throw new IllegalArgumentException("minBounds must be smaller than maxBounds");
            }
        }

        if (prideRatio <= 0.0 || prideRatio > 1.0) throw new IllegalArgumentException("prideRatio must be in (0, 1]");
        if (nomadFemaleRatio < 0.0 || nomadFemaleRatio > 1.0) throw new IllegalArgumentException("nomadFemaleRatio must be in [0, 1]");
        if (migrationRate < 0.0 || migrationRate > 1.0) throw new IllegalArgumentException("migrationRate must be in [0, 1]");

        minBounds = minBounds.clone();
        maxBounds = maxBounds.clone();
        logFile = logFile == null ? Path.of("src/resources/algorithm_run.log") : logFile;
    }

    public static LoaConfig defaultFor(
            final int populationSize,
            final int maxIterations,
            final int dimensions,
            final double[] minBounds,
            final double[] maxBounds,
            final long seed
    ) {
        return new LoaConfig(
                populationSize,
                maxIterations,
                dimensions,
                minBounds,
                maxBounds,
                5,
                0.80,
                0.80,
                0.10,
                seed,
                false,
                Path.of("src/resources/algorithm_run.log")
        );
    }

    public LoaConfig withLogging(final boolean enabled, final Path path) {
        return new LoaConfig(
                populationSize,
                maxIterations,
                dimensions,
                minBounds,
                maxBounds,
                numberOfPrides,
                prideRatio,
                nomadFemaleRatio,
                migrationRate,
                seed,
                enabled,
                path
        );
    }
}