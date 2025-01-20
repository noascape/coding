package facade;

public interface ISmartGlassesFacade {
    void displayContent(String contentType, String content, int durationMs);
    void processInteraction(String inputType, String command);
    void getEnvironmentStatus();
}
