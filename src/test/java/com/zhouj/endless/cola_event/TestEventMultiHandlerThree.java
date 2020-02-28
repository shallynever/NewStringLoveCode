package com.zhouj.endless.cola_event;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 天启
 * @date 2020-02-28 18:37
 * @description
 */
@Order(2)
@Component
public class TestEventMultiHandlerThree implements IEventHandler<EventMulti> {
    @Override
    public void execute(EventMulti eventMulti) {
        System.out.println("TestEventMultiHandlerThree执行：" + eventMulti.getEventName());
    }
}
