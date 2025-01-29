package SB;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Display {

    public void show(String message) {
        log.info("Display: {}", message);
    }
}
