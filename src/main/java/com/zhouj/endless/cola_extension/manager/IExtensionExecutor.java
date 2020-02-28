package com.zhouj.endless.cola_extension.manager;

import com.zhouj.endless.cola_extension.BizScenario;
import com.zhouj.endless.cola_extension.ExtensionCoordinate;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author 天启
 * @date 2020-02-28 09:52
 * @description 扩展点执行接口
 */
public interface IExtensionExecutor {
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
    <R, T> R execute(Class<T> targetClz, BizScenario bizScenario, Function<T, R> exeFunction);


    <R, T> R execute(ExtensionCoordinate extensionCoordinate, Function<T, R> exeFunction);

    /**
     * 执行扩展点无返回结果
     *
     * @param targetClz   扩展点类
     * @param bizScenario 业务场景标识
     * @param exeFunction 要执行的扩展接口的方法
     * @param <T>         扩展接口类型
     * @return R
     */
    <T> void executeVoid(Class<T> targetClz, BizScenario bizScenario, Consumer<T> exeFunction);

    <T> void executeVoid(ExtensionCoordinate extensionCoordinate, Consumer<T> exeFunction);
}
