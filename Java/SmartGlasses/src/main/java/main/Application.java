package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.SmartGlassesModule;
import facade.ISmartGlassesFacade;
import lombok.extern.slf4j.Slf4j;
import models.MotionData;
import models.SensorData;
import services.environment.IEnvironmentService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SmartGlassesModule());
        ISmartGlassesFacade facade = injector.getInstance(ISmartGlassesFacade.class);

        byte[] cameraFeed = new byte[]{1, 2, 3};
        MotionData motionData = new MotionData(0.5f, 0.8f, 0.3f);
        SensorData sensorData = new SensorData(cameraFeed, motionData);
        IEnvironmentService environmentService = injector.getInstance(IEnvironmentService.class);
        environmentService.analyzeEnvironment(sensorData);

        facade.displayContent("TEXT", "Welcome to Smart Glasses!", 500000);
        facade.processInteraction("TOUCH", "SHOW_TIME");
        facade.getEnvironmentStatus();

        facade.processInteraction("VOICE", "OPEN_NAVIGATION");

        // Warten, bis asynchrone Aufgaben abgeschlossen sind (funktioniert anscheinend nicht oder man sieht nicht alles, weil es asynchron abläuft)
        ExecutorService executorService = injector.getInstance(ExecutorService.class);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown of ExecutorService...");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Shutdown interrupted!" + e);
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Application finished execution.");
    }
}





