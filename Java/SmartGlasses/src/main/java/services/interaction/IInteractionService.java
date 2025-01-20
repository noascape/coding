package services.interaction;

import events.InteractionEvent;

public interface IInteractionService {
    void handleInteraction(String inputType, String command);
    void onInteractionEvent(InteractionEvent event);
}