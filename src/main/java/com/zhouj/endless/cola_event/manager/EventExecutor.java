package com.zhouj.endless.cola_event.manager;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zhouj.endless.cola_event.IEvent;
import com.zhouj.endless.cola_event.IEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 天启
 * @date 2020-02-28 18:09
 * @description
 */
@Component
public class EventExecutor implements IEventExecutor {
    @Autowired
    private EventHub eventHub;
    /**
     * 默认线程池
     */
    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder().setNameFormat("event-bus-pool-%d").build());


    @Override
    public void send(IEvent event) {
        eventHub.getEventHandler(event.getClass()).forEach(p -> {
            p.execute(event);
        });

    }

    @Override
    public void asyncSend(IEvent event) {
        eventHub.getEventHandler(event.getClass()).forEach(p -> {
            executorService.submit(() -> p.execute(event));
        });
    }
}
