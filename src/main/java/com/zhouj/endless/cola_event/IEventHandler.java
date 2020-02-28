package com.zhouj.endless.cola_event;

/**
 * @author 天启
 * @date 2020-02-28 18:12
 * @description 事件处理器接口
 */
public interface IEventHandler<E extends IEvent> {
    /**
     * 事件处理
     *
     * @param e
     * @return
     */
    void execute(E e);
}
