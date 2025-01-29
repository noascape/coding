package SB;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Touchscreen {
    private final SB sb;

    public void touch() {
        log.info("Touchscreen tapped by customer.");
        sb.start();
    }
}
