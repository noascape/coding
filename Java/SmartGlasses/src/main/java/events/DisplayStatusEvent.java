package events;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class DisplayStatusEvent {
    private String status; // e.g., "DISPLAYING", "IDLE"
    private Instant timestamp; // ISO 8601 format
}

