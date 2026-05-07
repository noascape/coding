package edu.swarmintelligence.loa;

import edu.swarmintelligence.loa.function.ObjectiveFunction;

import java.util.Arrays;

/**
 * Two- or n-dimensional Ackley benchmark function for minimization problems.
 *
 * <p>The global minimum is located at {@code x = (0, ..., 0)} with
 * {@code f(x) = 0}. The recommended search range is
 * {@code [-32.768, 32.768]^n}.</p>
 */
public final class AckleyFunction implements ObjectiveFunction {
    public static final double DEFAULT_MIN = -32.768;
    public static final double DEFAULT_MAX = 32.768;

    private static final double A = 20.0;
    private static final double B = 0.2;
    private static final double C = 2.0 * Math.PI;

    @Override
    public double evaluate(final double[] x) {
        if (x == null || x.length == 0) {
            throw new IllegalArgumentException("x must contain at least one dimension");
        }

        final double n = x.length;
        final double sumSquares = Arrays.stream(x).map(v -> v * v).sum();
        final double sumCosines = Arrays.stream(x).map(v -> Math.cos(C * v)).sum();

        return -A * Math.exp(-B * Math.sqrt(sumSquares / n))
                - Math.exp(sumCosines / n)
                + A + Math.E;
    }

    public static double[] minBounds(final int dimensions) {
        return filled(dimensions, DEFAULT_MIN);
    }

    public static double[] maxBounds(final int dimensions) {
        return filled(dimensions, DEFAULT_MAX);
    }

    private static double[] filled(final int dimensions, final double value) {
        if (dimensions <= 0) {
            throw new IllegalArgumentException("dimensions must be positive");
        }

        final double[] result = new double[dimensions];
        Arrays.fill(result, value);
        return result;
    }
}