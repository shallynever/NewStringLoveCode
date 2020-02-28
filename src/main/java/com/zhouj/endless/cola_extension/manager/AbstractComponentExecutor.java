package com.zhouj.endless.cola_extension.manager;

import com.zhouj.endless.cola_extension.BizScenario;
import com.zhouj.endless.cola_extension.ExtensionCoordinate;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author 天启
 * @date 2020-02-27 21:24
 * @description 扩展点执行器抽象类
 */
public abstract class AbstractComponentExecutor implements IExtensionExecutor {

    /**
     * 执行扩展点并返回结果
     *
     * @param targetClz   扩展点类
     * @param bizScenario 业务场景标识
     * @param exeFunction 要执行的扩展接口的方法
     * @param <R>         返回值类型
     * @param <T>         扩展接口类型
     * @return R
     */
    @Override
    public <R, T> R execute(Class<T> targetClz, BizScenario bizScenario, Function<T, R> exeFunction) {
        T component = locateComponent(targetClz, bizScenario);
        return exeFunction.apply(component);
    }


    @Override
    public <R, T> R execute(ExtensionCoordinate extensionCoordinate, Function<T, R> exeFunction) {
        return execute((Class<T>) extensionCoordinate.getExtensionPointClass(), extensionCoordinate.getBizScenario(), exeFunction);
    }

    /**
     * 执行扩展点无返回结果
     *
     * @param targetClz   扩展点类
     * @param bizScenario 业务场景标识
     * @param exeFunction 要执行的扩展接口的方法
     * @param <T>         扩展接口类型
     * @return R
     */
    @Override
    public <T> void executeVoid(Class<T> targetClz, BizScenario bizScenario, Consumer<T> exeFunction) {
        T component = locateComponent(targetClz, bizScenario);
        exeFunction.accept(component);
    }

    @Override
    public <T> void executeVoid(ExtensionCoordinate extensionCoordinate, Consumer<T> exeFunction) {
        executeVoid(extensionCoordinate.getExtensionPointClass(), extensionCoordinate.getBizScenario(), exeFunction);
    }

    /**
     * @param targetClz   扩展点类
     * @param bizScenario 业务场景标识
     * @return 返回T
     */
    protected abstract <T> T locateComponent(Class<T> targetClz, BizScenario bizScenario);
}
