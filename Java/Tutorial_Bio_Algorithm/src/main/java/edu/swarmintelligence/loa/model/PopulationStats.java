package edu.swarmintelligence.loa.model;

/**
 * Aggregated convergence indicators of the current population.
 *
 * @param averageFitness average objective value of the current population
 * @param standardDeviation standard deviation of population fitness values
 */
public record PopulationStats(
        double averageFitness,
        double standardDeviation
) {
}