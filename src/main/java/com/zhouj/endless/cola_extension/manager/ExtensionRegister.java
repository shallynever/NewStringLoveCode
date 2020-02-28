package com.zhouj.endless.cola_extension.manager;

import com.google.common.collect.Lists;
import com.zhouj.endless.cola_extension.BizScenario;
import com.zhouj.endless.cola_extension.Extension;
import com.zhouj.endless.cola_extension.ExtensionCoordinate;
import com.zhouj.endless.cola_extension.ExtensionRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.MapUtils;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 天启
 * @date 2020-02-27 22:42
 * @description 扩展点注册类
 */
@Component
public class ExtensionRegister implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private ExtensionRepository extensionRepository;

    private List<IExtensionPoint> extensionPointList;

    private ApplicationContext applicationContext;

    private final static String EXTENSION_POINT_NAMING = "ExtPt";


    private void doRegistration(IExtensionPoint extensionPoint) {
        Extension extensionAnn = extensionPoint.getClass().getDeclaredAnnotation(Extension.class);
        if (extensionAnn == null) {
            throw new RuntimeException("扩展点 " + extensionPoint.getClass() + "没有使用@Extension注解");
        }
        Class extPtClass = calculateExtensionPoint(extensionPoint.getClass());
        BizScenario bizScenario = BizScenario.valueOf(extensionAnn.bizId(), extensionAnn.useCase(), extensionAnn.scenario());
        ExtensionCoordinate extensionCoordinate = new ExtensionCoordinate(extPtClass.getName(), bizScenario.getUniqueIdentity());
        IExtensionPoint preVal = extensionRepository.getExtensionRepo().put(extensionCoordinate, extensionPoint);
        if (preVal != null) {
            throw new RuntimeException("Duplicate registration is not allowed for :" + extensionCoordinate);
        }
    }

    private void init() {
        if (CollectionUtils.isEmpty(extensionPointList)) {
            return;
        }
        for (IExtensionPoint extensionPoint : extensionPointList) {
            doRegistration(extensionPoint);
        }
    }

    /**
     * @param targetClz 扩展点类
     * @return String
     */
    private Class calculateExtensionPoint(Class<?> targetClz) {
        Class[] interfaces = targetClz.getInterfaces();
        if (ArrayUtils.isEmpty(interfaces)) {
            throw new RuntimeException("Please assign a extension point interface for " + targetClz);
        }
        for (Class clazz : interfaces) {
            String extensionPoint = clazz.getSimpleName();
            if (StringUtils.contains(extensionPoint, EXTENSION_POINT_NAMING)) {
                return clazz;
            }
        }
        throw new RuntimeException("Your name of ExtensionPoint for " + targetClz + " is not valid, must be end of " + EXTENSION_POINT_NAMING);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /**
         * 在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
         * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
         * 这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，
         * 可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理，修改后代码
         */
        if (event.getApplicationContext().getParent() == null) {
            Map<String, IExtensionPoint> extensionPointMap = this.applicationContext.getBeansOfType(IExtensionPoint.class);
            if (MapUtils.isEmpty(extensionPointMap)) {
                return;
            }
            this.extensionPointList = Lists.newArrayList(extensionPointMap.values());
            this.init();
        }
    }
}
