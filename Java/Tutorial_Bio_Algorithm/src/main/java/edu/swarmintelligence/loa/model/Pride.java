package edu.swarmintelligence.loa.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Represents a social pride in the Lion Optimization Algorithm.
 *
 * <p>A pride contains resident lions, maintains its best known prey position
 * and provides helper methods for gender-specific operators.</p>
 */
public final class Pride {
    private final int id;
    private final List<Lion> members = new ArrayList<>();
    private double[] prey;
    private double preyFitness = Double.POSITIVE_INFINITY;

    public Pride(final int id) {
        this.id = id;
    }

    public int id() { return id; }
    public List<Lion> members() { return members; }
    public double preyFitness() { return preyFitness; }

    public double[] prey() {
        return prey == null ? null : prey.clone();
    }

    /**
     * Updates the pride-specific best known position.
     */
    public void updatePrey() {
        members.stream().min(Comparator.comparingDouble(Lion::fitness)).ifPresent(best -> {
            if (prey == null || best.fitness() < preyFitness) {
                preyFitness = best.fitness();
                prey = best.position().clone();
            }
        });
    }

    public List<Lion> females() {
        return members.stream().filter(Lion::isFemale).toList();
    }

    public Optional<Lion> male() {
        return members.stream().filter(Lion::isMale).findFirst();
    }

    /**
     * Computes the geometric centre of all members of this pride.
     *
     * @param dimensions number of dimensions of the search space
     * @return average position of all pride members
     */
    public double[] territoryCentre(final int dimensions) {
        final double[] centre = new double[dimensions];

        if (members.isEmpty()) {
            return centre;
        }

        for (final Lion lion : members) {
            for (int d = 0; d < dimensions; d++) {
                centre[d] += lion.position()[d];
            }
        }

        for (int d = 0; d < dimensions; d++) {
            centre[d] /= members.size();
        }

        return centre;
    }
}