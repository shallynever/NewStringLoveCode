package com.zhouj.endless.cola_event.manager;

import com.google.common.collect.Lists;
import com.zhouj.endless.cola_event.IEvent;
import com.zhouj.endless.cola_event.IEventHandler;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.MapUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author 天启
 * @date 2020-02-28 18:15
 * @description
 */
@Getter
@Component
public class EventRegister implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    private List<IEventHandler> eventHandlerList;

    private EventHub eventHub;

    private static final String EXE_METHOD = "execute";

    public void doRegistration(Class<? extends IEvent> targetClz, IEventHandler eventHandler) {
        eventHub.register(targetClz, eventHandler);
    }

    private void init() {
        if (CollectionUtils.isEmpty(eventHandlerList)) {
            return;
        }
        for (IEventHandler eventHandler : eventHandlerList) {
            Class<? extends IEvent> eventClz = getEventFromExecutor(eventHandler.getClass());
            doRegistration(eventClz, eventHandler);
        }
    }

    private Class<? extends IEvent> getEventFromExecutor(Class<?> eventExecutorClz) {
        Method[] methods = eventExecutorClz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)) {
                return checkAndGetEventParamType(method);
            }
        }
        throw new RuntimeException("处理者 " + eventExecutorClz + " " + EXE_METHOD + "() 方法没有参数");
    }

    private boolean isExecuteMethod(Method method) {
        return EXE_METHOD.equals(method.getName()) && !method.isBridge();
    }

    private Class checkAndGetEventParamType(Method method) {
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0) {
            throw new RuntimeException(method.getDeclaringClass() + " 应该至少有一个参数");
        }
        if (!IEvent.class.isAssignableFrom(exeParams[0])) {
            throw new RuntimeException(method.getDeclaringClass() + " 的参数应该是IEvent的子类");
        }
        return exeParams[0];
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Map<String, IEventHandler> eventHandlerMap = this.applicationContext.getBeansOfType(IEventHandler.class);
            if (MapUtils.isEmpty(eventHandlerMap)) {
                return;
            }
            this.eventHub = this.applicationContext.getBean(EventHub.class);
            this.eventHandlerList = Lists.newArrayList(eventHandlerMap.values());
            // bean 按照order定义的顺序排序
            AnnotationAwareOrderComparator.sort(eventHandlerList);
            this.init();
        }
    }
}