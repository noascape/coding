package de.project.builder;

import lombok.Builder;
import lombok.Data;

import java.util.EnumSet;
import java.util.Set;

@Data
@Builder
public class SeatConfiguration {
    private final Set<SeatFeature> features;

    // Utility method to check for a specific feature
    public boolean hasFeature(SeatFeature feature) {
        return features != null && features.contains(feature);
    }

    // Method to provide a default configuration
    public static SeatConfiguration defaultConfiguration() {
        return SeatConfiguration.builder()
                .features(EnumSet.noneOf(SeatFeature.class))
                .build();
    }
}
