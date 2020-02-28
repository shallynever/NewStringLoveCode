package com.zhouj.endless.cola_extension;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 天启
 * @date 2020-02-27 22:49
 * @description 扩展点注释
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface Extension {
    String bizId() default BizScenario.DEFAULT_BIZ_ID;

    String useCase() default BizScenario.DEFAULT_USE_CASE;

    String scenario() default BizScenario.DEFAULT_SCENARIO;
}
