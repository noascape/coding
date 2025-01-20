package services.environment;

import events.EnvironmentEvent;
import models.SensorData;

public interface IEnvironmentService {
    void analyzeEnvironment(SensorData sensorData);
    void onEnvironmentEvent(EnvironmentEvent event);
}

