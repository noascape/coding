package events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InteractionEvent {
    private String inputType;
    private String command;
}