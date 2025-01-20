package facade;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.Inject;
import events.DisplayEvent;
import events.EnvironmentEvent;
import events.InteractionEvent;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;

@Slf4j
public class SmartGlassesFacade implements ISmartGlassesFacade {
    private final AsyncEventBus eventBus;

    @Inject
    public SmartGlassesFacade(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        log.info("SmartGlassesFacade initialized and registered with EventBus");
    }

    @Override
    public void displayContent(String contentType, String content, int durationMs) {
        log.info("Facade: Sending DisplayEvent: contentType={}, content={}, durationMs={}", contentType, content, durationMs);
        eventBus.post(new DisplayEvent(contentType, content, durationMs));
    }

    @Override
    public void processInteraction(String inputType, String command) {
        log.info("Facade: Sending InteractionEvent: inputType={}, command={}", inputType, command);
        eventBus.post(new InteractionEvent(inputType, command));
    }

    @Override
    public void getEnvironmentStatus() {
        log.info("Facade: Requesting environment status");
        eventBus.post(new EnvironmentEvent("ENVIRONMENT_CHECK", "Requesting environment status", Instant.now()));
    }
}


