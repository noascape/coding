package config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import facade.ISmartGlassesFacade;
import facade.SmartGlassesFacade;
import services.display.DisplayService;
import services.display.IDisplayService;
import services.environment.EnvironmentService;
import services.environment.IEnvironmentService;
import services.interaction.InteractionService;
import services.interaction.IInteractionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartGlassesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ISmartGlassesFacade.class).to(SmartGlassesFacade.class);

    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public AsyncEventBus provideAsyncEventBus(ExecutorService executorService) {
        return new AsyncEventBus(executorService);
    }

    @Provides
    @Singleton
    public IDisplayService provideDisplayService(AsyncEventBus eventBus) {
        DisplayService displayService = new DisplayService(eventBus);
        eventBus.register(displayService);
        return displayService;
    }

    @Provides
    @Singleton
    public IEnvironmentService provideEnvironmentService(AsyncEventBus eventBus) {
        EnvironmentService environmentService = new EnvironmentService(eventBus);
        eventBus.register(environmentService);
        return environmentService;
    }

    @Provides
    @Singleton
    public IInteractionService provideInteractionService(AsyncEventBus eventBus) {
        InteractionService interactionService = new InteractionService(eventBus);
        eventBus.register(interactionService);
        return interactionService;
    }
}
