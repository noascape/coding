package events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisplayEvent {
    private String contentType;
    private String content;
    private int durationMs;
}
