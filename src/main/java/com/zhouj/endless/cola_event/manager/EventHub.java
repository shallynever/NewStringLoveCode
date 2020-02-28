package com.zhouj.endless.cola_event.manager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.zhouj.endless.cola_event.IEvent;
import com.zhouj.endless.cola_event.IEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 天启
 * @date 2020-02-28 18:11
 * @description 事件总线
 */
@Slf4j
@Component
public class EventHub {
    /**
     * 事件存储
     */
    private ListMultimap<Class, IEventHandler> eventRepository = ArrayListMultimap.create(LinkedHashMultimap.create());

    /**
     * 获取对应事件的处理器
     *
     * @param eventClass
     * @return
     */
    public List<IEventHandler> getEventHandler(Class eventClass) {
        List<IEventHandler> eventHandlerList = eventRepository.get(eventClass);
        if (CollectionUtils.isEmpty(eventHandlerList)) {
            log.error(eventClass + "处理者没有注册，请检查");
            eventHandlerList = Lists.newArrayList();
        }
        return eventHandlerList;
    }

    /**
     * 注册事件
     *
     * @param eventClz
     * @param executor
     */
    public void register(Class<? extends IEvent> eventClz, IEventHandler executor) {
        eventRepository.put(eventClz, executor);
    }
}
