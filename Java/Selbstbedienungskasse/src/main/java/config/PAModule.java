package config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import facade.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import services.payment.*;
import services.scan.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static com.google.inject.name.Names.named;

@Slf4j
@RequiredArgsConstructor
public class PAModule extends AbstractModule {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    protected void configure() {
        bind(ISBFacade.class).to(SBFacade.class);

        bind(IPaymentService.class).annotatedWith(named("CARD_PAYMENT")).to(CardPaymentService.class);
        bind(IPaymentService.class).annotatedWith(named("CASH_PAYMENT")).to(CashPaymentService.class);
        bind(IPaymentService.class).annotatedWith(named("MOBILE_PAYMENT")).to(MobilePaymentService.class);

        bind(ScanService.class).asEagerSingleton();
        bind(PaymentService.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return executorService;
    }

    @Provides
    @Singleton
    public AsyncEventBus provideAsyncEventBus(ExecutorService executorService) {
        return new AsyncEventBus(executorService);
    }


    public synchronized void shutdown() {
        log.info("Shutting down AsyncEventBus");
        try{
            executorService.shutdown();
            long shutdownWaitTime = 100;
            if (!executorService.awaitTermination(shutdownWaitTime, TimeUnit.SECONDS)) {
                log.warn("ExecutorService did not terminate in time | forcing shutdown");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.warn("shutdown was interrupted | forcing shutdown");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        log.info("shutdown completed");
    }
}
