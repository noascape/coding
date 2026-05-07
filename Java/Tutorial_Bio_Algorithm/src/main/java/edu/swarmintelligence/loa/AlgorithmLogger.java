package edu.swarmintelligence.loa;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Locale;

/**
 * Writes machine-readable CSV logs for one LOA run.
 */
public final class AlgorithmLogger implements Closeable {
    private static final String HEADER = "iteration;agentId;positionBefore;positionAfter;personalBest;"
            + "personalBestFitness;globalBest;globalBestFitness;popAvgFitness;popStdDev;distToOptimum";

    private final boolean enabled;
    private final BufferedWriter writer;

    private AlgorithmLogger(final boolean enabled, final BufferedWriter writer) {
        this.enabled = enabled;
        this.writer = writer;
    }

    public static AlgorithmLogger create(final Path path, final boolean enabled) throws IOException {
        if (!enabled) {
            return new AlgorithmLogger(false, null);
        }

        if (path == null) {
            throw new IllegalArgumentException("path must not be null when logging is enabled");
        }

        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

        writer.write(HEADER);
        writer.newLine();

        return new AlgorithmLogger(true, writer);
    }

    public void log(final IterationLogEntry entry) throws IOException {
        if (!enabled) {
            return;
        }

        writer.write(String.join(";",
                Integer.toString(entry.iteration()),
                Integer.toString(entry.agentId()),
                formatVector(entry.positionBefore()),
                formatVector(entry.positionAfter()),
                formatVector(entry.personalBest()),
                formatDouble(entry.personalBestFitness()),
                formatVector(entry.globalBest()),
                formatDouble(entry.globalBestFitness()),
                formatDouble(entry.populationAverageFitness()),
                formatDouble(entry.populationStandardDeviation()),
                formatDouble(entry.distanceToOptimum())
        ));
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        if (enabled) {
            writer.flush();
            writer.close();
        }
    }

    private static String formatVector(final double[] values) {
        return Arrays.stream(values)
                .mapToObj(AlgorithmLogger::formatDouble)
                .reduce("[", (a, b) -> a.equals("[") ? a + b : a + "," + b) + "]";
    }

    private static String formatDouble(final double value) {
        return String.format(Locale.US, "%.10f", value);
    }

    /**
     * Immutable representation of one CSV line in {@code algorithm_run.log}.
     */
    public record IterationLogEntry(
            int iteration,
            int agentId,
            double[] positionBefore,
            double[] positionAfter,
            double[] personalBest,
            double personalBestFitness,
            double[] globalBest,
            double globalBestFitness,
            double populationAverageFitness,
            double populationStandardDeviation,
            double distanceToOptimum
    ) {
        public IterationLogEntry {
            positionBefore = positionBefore.clone();
            positionAfter = positionAfter.clone();
            personalBest = personalBest.clone();
            globalBest = globalBest.clone();
        }
    }
}