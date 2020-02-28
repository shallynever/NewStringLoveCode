package com.zhouj.endless.cola_event;

import org.springframework.stereotype.Component;

/**
 * @author 天启
 * @date 2020-02-28 18:25
 * @description
 */
@Component
public class TestEventOneHandler implements IEventHandler<EventOne> {
    @Override
    public void execute(EventOne eventOne) {
        System.out.println("TestEventOneHandler执行：" + eventOne.getEventName());
    }
}
