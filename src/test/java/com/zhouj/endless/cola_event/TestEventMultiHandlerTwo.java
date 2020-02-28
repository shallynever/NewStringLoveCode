package com.zhouj.endless.cola_event;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 天启
 * @date 2020-02-28 18:37
 * @description
 */
@Order(1)
@Component
public class TestEventMultiHandlerTwo implements IEventHandler<EventMulti> {
    @Override
    public void execute(EventMulti eventMulti) {
        System.out.println("TestEventMultiHandlerTwo执行：" + eventMulti.getEventName());
    }
}

