package com.zhouj.endless.cola_event;

/**
 * @author 天启
 * @date 2020-02-28 18:06
 * @description 事件执行器
 */
public interface IEventExecutor {
    /**
     * 通知事件到总线执行
     *
     * @param event
     * @return
     */
    void send(IEvent event);

    /**
     * 异步通知事件到总线执行
     *
     * @param event
     */
    void asyncSend(IEvent event);
}
