package edu.swarmintelligence.loa.function;

/**
 * Represents the objective function optimized by the Lion Optimization Algorithm.
 *
 * <p>The algorithm assumes a minimization problem. Therefore, smaller return values
 * represent better candidate solutions.</p>
 */
@FunctionalInterface
public interface ObjectiveFunction {
    /**
     * Evaluates one candidate solution.
     *
     * @param x candidate vector inside the configured search space
     * @return objective value of the candidate; smaller values are better
     */
    double evaluate(double[] x);
}