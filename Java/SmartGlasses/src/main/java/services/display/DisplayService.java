package services.display;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import events.DisplayEvent;
import events.DisplayStatusEvent;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;

@Slf4j
public class DisplayService implements IDisplayService {
    private static final int maxDurationMs = 30000; // Max display duration in milliseconds
    private final AsyncEventBus eventBus;

    @Inject
    public DisplayService(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
        log.info("DisplayService initialized and registered with EventBus");
    }

    @Override
    public void handleDisplayEvent(String contentType, String content, int durationMs) {
        log.info("Handling DisplayEvent: contentType={}, content={}, durationMs={}", contentType, content, durationMs);

        if (durationMs > maxDurationMs) {
            log.warn("Display duration exceeds limit. Adjusting to 30 seconds.");
            durationMs = maxDurationMs;
        }

        log.info("Displaying content: {} for {} ms", content, durationMs);
        displayContent(contentType, content, durationMs);

        publishStatusEvent("DISPLAYING");

        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            log.error("Display interrupted", e);
            Thread.currentThread().interrupt();
        }
        publishStatusEvent("IDLE");
    }

    @Override
    @Subscribe
    public void onDisplayEvent(DisplayEvent event) {
        log.info("Received DisplayEvent: {}", event);
        handleDisplayEvent(event.getContentType(), event.getContent(), event.getDurationMs());
    }

    private void displayContent(String contentType, String content, int durationMs) {
        switch (contentType.toUpperCase()) {
            case "TEXT":
                log.info("Displaying TEXT content: {} for {} ms", content, durationMs);
                break;
            case "MAP":
                log.info("Displaying MAP content: {} for {} ms", content, durationMs);
                break;
            case "NOTIFICATION":
                log.info("Displaying NOTIFICATION: {} for {} ms", content, durationMs);
                break;
            default:
                log.warn("Unknown content type: {}. Skipping display.", contentType);
        }
    }

    private void publishStatusEvent(String status) {
        Instant timestamp = Instant.now();
        DisplayStatusEvent statusEvent = new DisplayStatusEvent(status, timestamp);
        eventBus.post(statusEvent);
        log.info("Published DisplayStatusEvent: {}", statusEvent);
    }
}



