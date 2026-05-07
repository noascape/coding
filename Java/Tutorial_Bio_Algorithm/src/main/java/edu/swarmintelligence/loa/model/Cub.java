package edu.swarmintelligence.loa.model;

/**
 * Represents a temporary offspring relation used during cub growth.
 *
 * @param lion generated cub
 * @param mother mother lion used as possible growth target
 * @param father father lion used as possible growth target
 */
public record Cub(
        Lion lion,
        Lion mother,
        Lion father
) {
}