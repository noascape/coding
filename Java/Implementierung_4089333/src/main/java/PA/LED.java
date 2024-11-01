package PA;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class LED {

    private LEDStatus status = LEDStatus.RED; // Initially locked

    public void updateStatus(LEDStatus newStatus) {
        this.status = newStatus;
        log.info("LED status updated to: {}", status);
    }
}
