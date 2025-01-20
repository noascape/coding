package events;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class EnvironmentEvent {
    private String eventType;
    private String details;
    private Instant timestamp;
}