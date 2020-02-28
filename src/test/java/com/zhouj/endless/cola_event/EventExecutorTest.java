package com.zhouj.endless.cola_event;

import com.zhouj.endless.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 天启
 * @date 2020-02-28 18:22
 * @description
 */
public class EventExecutorTest extends BaseTest {

    @Autowired
    private IEventExecutor eventExecutor;

    @Test
    public void testSend() {
        EventOne e = new EventOne();
        e.setEventName("zhouj-test");
        eventExecutor.send(e);
    }

    @Test
    public void testSendMultiHandlerEvent() {
        EventMulti e = new EventMulti();
        e.setEventName("zhouj-test");
        eventExecutor.send(e);
    }



}