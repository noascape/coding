package services.interaction;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import events.InteractionEvent;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InteractionService implements IInteractionService {
    private final AsyncEventBus eventBus;

    @Inject
    public InteractionService(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
        log.info("InteractionService initialized and registered with EventBus");
    }

    @Override
    public void handleInteraction(String inputType, String command) {
        log.info("Processing interaction: inputType={}, command={}", inputType, command);

        switch (command.toUpperCase()) {
            case "SHOW_TIME":
                log.info("Command: SHOW_TIME");
                eventBus.post(new InteractionEvent(inputType, "TIME_DISPLAYED"));
                break;

            case "OPEN_NAVIGATION":
                log.info("Command: OPEN_NAVIGATION");
                eventBus.post(new InteractionEvent(inputType, "NAVIGATION_STARTED"));
                break;

            default:
                log.warn("Unknown command: {}", command);
        }
    }

    @Subscribe
    public void onInteractionEvent(InteractionEvent event) {
        log.info("Received InteractionEvent: {}", event);
        handleInteraction(event.getInputType(), event.getCommand());
    }
}

