package services.environment;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import events.EnvironmentEvent;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import models.MotionData;
import models.SensorData;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class EnvironmentService implements IEnvironmentService {
    private final AsyncEventBus eventBus;
    private final Set<String> processedEvents = new HashSet<>();

    @Inject
    public EnvironmentService(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
        log.info("EnvironmentService initialized and registered with EventBus");
    }

    @Override
    public void analyzeEnvironment(SensorData sensorData) {
        log.info("Analyzing environment using sensor data...");

        if (sensorData.getCameraFeed() != null && containsObstacle(sensorData.getCameraFeed())) {
            publishEvent("OBSTACLE_DETECTED", "Obstacle detected in camera feed");
            return;
        }

        if (sensorData.getMotionData() != null && isMotionCritical(sensorData.getMotionData())) {
            publishEvent("CRITICAL_MOTION_DETECTED", "Critical motion detected");
            return;
        }

        publishEvent("SAFE_PATH", "No obstacles or critical motion detected");
    }

    private boolean containsObstacle(byte[] cameraFeed) {
        return cameraFeed.length > 0;
    }

    private boolean isMotionCritical(MotionData motionData) {
        return Math.abs(motionData.getX()) > 5.0 ||
                Math.abs(motionData.getY()) > 5.0 ||
                Math.abs(motionData.getZ()) > 5.0;
    }


    private void publishEvent(String eventType, String details) {
        String eventKey = eventType + ":" + details;
        if (processedEvents.contains(eventKey)) {
            log.info("Event {} already processed. Skipping...", eventKey);
            return;
        }

        processedEvents.add(eventKey);
        EnvironmentEvent event = new EnvironmentEvent(eventType, details, Instant.now());
        eventBus.post(event);
        log.info("Published EnvironmentEvent: {}", event);
    }

    @Subscribe
    public void onEnvironmentEvent(EnvironmentEvent event) {
        log.info("Environment event received: {}", event);    //hier vlt. noch was richtiges coden
    }
}


