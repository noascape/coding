package edu.swarmintelligence.loa.model;

import edu.swarmintelligence.loa.types.Gender;
import edu.swarmintelligence.loa.types.Role;

/**
 * Represents one search agent of the Lion Optimization Algorithm.
 *
 * <p>A lion stores its current position and fitness as well as its personal best.
 * The personal best forms the local memory required for observing the algorithmic
 * behaviour of individual agents.</p>
 */
public final class Lion {
    private final int id;
    private final double[] position;
    private double fitness;
    private double[] personalBestPosition;
    private double personalBestFitness;
    private Gender gender;
    private Role role = Role.NOMAD;
    private int prideId = -1;

    /**
     * Creates a lion with an initial position and fitness.
     *
     * @param id stable agent identifier used for logging
     * @param position initial candidate solution
     * @param fitness objective value of the initial position
     * @param gender biological gender used by LOA operators
     */
    public Lion(final int id, final double[] position, final double fitness, final Gender gender) {
        this.id = id;
        this.position = position.clone();
        this.fitness = fitness;
        this.personalBestPosition = position.clone();
        this.personalBestFitness = fitness;
        this.gender = gender;
    }

    public int id() { return id; }
    public double[] position() { return position; }
    public double fitness() { return fitness; }
    public double[] personalBestPosition() { return personalBestPosition.clone(); }
    public double personalBestFitness() { return personalBestFitness; }
    public Gender gender() { return gender; }
    public Role role() { return role; }
    public int prideId() { return prideId; }
    public boolean isMale() { return gender == Gender.MALE; }
    public boolean isFemale() { return gender == Gender.FEMALE; }

    /**
     * Moves the lion to a new accepted position.
     *
     * @param newPosition accepted candidate position
     * @param newFitness objective value of the accepted candidate
     */
    public void moveTo(final double[] newPosition, final double newFitness) {
        System.arraycopy(newPosition, 0, position, 0, position.length);
        fitness = newFitness;
    }

    /**
     * Updates the local memory of the lion with its current position.
     */
    public void updatePersonalBest() {
        personalBestFitness = fitness;
        personalBestPosition = position.clone();
    }

    public void changeGender(final Gender gender) {
        this.gender = gender;
    }

    public void becomeNomad() {
        role = Role.NOMAD;
        prideId = -1;
    }

    public void becomePrideMember(final int prideId) {
        role = Role.PRIDE;
        this.prideId = prideId;
    }
}