package mocktests;

import com.google.common.eventbus.AsyncEventBus;
import events.DisplayEvent;
import events.DisplayStatusEvent;
import events.EnvironmentEvent;
import events.InteractionEvent;
import models.MotionData;
import models.SensorData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.display.DisplayService;
import services.environment.EnvironmentService;
import services.interaction.InteractionService;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class SmartGlassesMockitoTest {

    private AsyncEventBus eventBus;
    private DisplayService displayService;
    private EnvironmentService environmentService;
    private InteractionService interactionService;

    @BeforeEach
    void setUp() {
        eventBus = mock(AsyncEventBus.class);
        displayService = new DisplayService(eventBus);
        environmentService = new EnvironmentService(eventBus);
        interactionService = new InteractionService(eventBus);

        doNothing().when(eventBus).register(any());
    }

    @Test
    void testDisplayEventInteraction() {
        DisplayEvent event = new DisplayEvent("TEXT", "Mock Test Message", 10000);
        displayService.onDisplayEvent(event);

        verify(eventBus, times(1)).post(argThat(arg ->
                arg instanceof DisplayStatusEvent &&
                        "DISPLAYING".equals(((DisplayStatusEvent) arg).getStatus())
        ));
    }

    @Test
    void testInteractionEventProcessing() {
        interactionService.handleInteraction("TOUCH", "SHOW_TIME");

        verify(eventBus, times(1)).post(argThat(arg ->
                arg instanceof InteractionEvent &&
                        "TIME_DISPLAYED".equals(((InteractionEvent) arg).getCommand())
        ));

        interactionService.handleInteraction("VOICE", "OPEN_NAVIGATION");

        verify(eventBus, times(1)).post(argThat(arg ->
                arg instanceof InteractionEvent &&
                        "NAVIGATION_STARTED".equals(((InteractionEvent) arg).getCommand())
        ));
    }

    @Test
    void testEnvironmentEventWithObstacle() {
        byte[] cameraFeed = new byte[]{1, 2, 3};
        MotionData motionData = new MotionData(0.0f, 0.0f, 0.0f);

        SensorData sensorData = new SensorData(cameraFeed, motionData);
        environmentService.analyzeEnvironment(sensorData);

        verify(eventBus, times(1)).post(argThat(arg ->
                arg instanceof EnvironmentEvent &&
                        "OBSTACLE_DETECTED".equals(((EnvironmentEvent) arg).getEventType())
        ));
    }

    @Test
    void testEnvironmentSafePath() {
        byte[] cameraFeed = new byte[]{}; // Leerer Kamera-Feed
        MotionData motionData = new MotionData(0.5f, 0.8f, 0.3f);

        SensorData sensorData = new SensorData(cameraFeed, motionData);
        environmentService.analyzeEnvironment(sensorData);

        verify(eventBus, times(1)).post(argThat(arg ->
                arg instanceof EnvironmentEvent &&
                        "SAFE_PATH".equals(((EnvironmentEvent) arg).getEventType())
        ));
    }
}

