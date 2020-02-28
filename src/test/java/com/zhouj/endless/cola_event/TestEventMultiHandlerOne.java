package com.zhouj.endless.cola_event;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 天启
 * @date 2020-02-28 18:36
 * @description
 */
@Order(0)
@Component
public class TestEventMultiHandlerOne implements IEventHandler<EventMulti> {
    @Override
    public void execute(EventMulti eventMulti) {
        System.out.println("TestEventMultiHandlerOne执行：" + eventMulti.getEventName());
    }
}
