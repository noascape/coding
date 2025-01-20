package services.display;

import events.DisplayEvent;

public interface IDisplayService {
    void handleDisplayEvent(String contentType, String content, int durationMs);
    void onDisplayEvent(DisplayEvent event);
}

