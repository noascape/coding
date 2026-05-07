package edu.swarmintelligence.loa;

import edu.swarmintelligence.loa.config.LoaConfig;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * Small IntelliJ runnable demo for the LOA implementation.
 */
public final class LoaDemo {
    private LoaDemo() {
    }

    public static void main(final String[] args) {
        final int dimensions = 2;
        final var objective = new AckleyFunction();

        final var config = LoaConfig
                .defaultFor(30, 50, dimensions,
                        AckleyFunction.minBounds(dimensions),
                        AckleyFunction.maxBounds(dimensions),
                        42L)
                .withLogging(true, Path.of("src/resources/algorithm_run.log"));

        final var algorithm = new LionOptimizationAlgorithm(config, objective);
        final double[] best = algorithm.optimize();

        System.out.println("Best position: " + Arrays.toString(best));
        System.out.println("Best fitness:  " + objective.evaluate(best));
    }
}