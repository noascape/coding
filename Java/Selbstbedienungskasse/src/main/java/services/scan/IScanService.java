package services.scan;

import events.ScanEvent;

public interface IScanService {
    void handleScanEvent(ScanEvent scanEvent);
}
