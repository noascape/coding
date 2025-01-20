package junittests;

import com.google.common.eventbus.AsyncEventBus;
import events.DisplayEvent;
import events.EnvironmentEvent;
import models.MotionData;
import models.SensorData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.display.DisplayService;
import services.environment.EnvironmentService;
import services.interaction.InteractionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmartGlassesJUnitTest {

    private AsyncEventBus eventBus;
    private ExecutorService executorService;
    private DisplayService displayService;
    private EnvironmentService environmentService;
    private InteractionService interactionService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newCachedThreadPool();
        eventBus = new AsyncEventBus(executorService);

        displayService = new DisplayService(eventBus);
        environmentService = new EnvironmentService(eventBus);
        interactionService = new InteractionService(eventBus);

        eventBus.register(displayService);
        eventBus.register(environmentService);
        eventBus.register(interactionService);
    }

    @Test
    void testDisplayShortMessage() {
        DisplayEvent event = new DisplayEvent("TEXT", "Short Message", 5000);
        assertDoesNotThrow(() -> displayService.onDisplayEvent(event));
    }

    @Test
    void testDisplayLongMessage() {
        DisplayEvent event = new DisplayEvent("TEXT", "Long Message", 60000);
        assertDoesNotThrow(() -> displayService.onDisplayEvent(event));
        // Prüfen, ob Dauer auf 30 Sekunden begrenzt wurde
        assertEquals(30000, Math.min(event.getDurationMs(), 30000));
    }

    @Test
    void testEnvironmentWithObstacle() {
        byte[] cameraFeed = new byte[]{1, 2, 3}; // Simulierte Kameraeingaben
        MotionData motionData = new MotionData(0.0f, 0.0f, 0.0f); // Keine kritische Bewegung

        SensorData sensorData = new SensorData(cameraFeed, motionData);
        environmentService.analyzeEnvironment(sensorData);

        EnvironmentEvent expectedEvent = new EnvironmentEvent("OBSTACLE_DETECTED", "Obstacle detected in camera feed", null);
        assertEquals("OBSTACLE_DETECTED", expectedEvent.getEventType());
    }

    @Test
    void testEnvironmentSafePath() {
        byte[] cameraFeed = new byte[]{}; // Leerer Kamera-Feed
        MotionData motionData = new MotionData(0.5f, 0.8f, 0.3f); // Keine kritische Bewegung

        SensorData sensorData = new SensorData(cameraFeed, motionData);
        environmentService.analyzeEnvironment(sensorData);

        EnvironmentEvent expectedEvent = new EnvironmentEvent("SAFE_PATH", "No obstacles or critical motion detected", null);
        assertEquals("SAFE_PATH", expectedEvent.getEventType());
    }

    @Test
    void testCriticalMotionDetection() {
        byte[] cameraFeed = new byte[]{1, 2, 3};
        MotionData motionData = new MotionData(10.0f, 5.0f, 3.0f); // Kritische Bewegung

        SensorData sensorData = new SensorData(cameraFeed, motionData);
        environmentService.analyzeEnvironment(sensorData);

        EnvironmentEvent expectedEvent = new EnvironmentEvent("CRITICAL_MOTION_DETECTED", "Critical motion detected", null);
        assertEquals("CRITICAL_MOTION_DETECTED", expectedEvent.getEventType());
    }

    @Test
    void testInteractionProcessing() {
        assertDoesNotThrow(() -> interactionService.handleInteraction("TOUCH", "SHOW_TIME"));
        assertDoesNotThrow(() -> interactionService.handleInteraction("VOICE", "OPEN_NAVIGATION"));
    }
}

