package com.zhouj.endless.cloa_extension;

import com.zhouj.endless.cola_extension.Extension;

/**
 * @author 天启
 * @date 2020-02-28 09:59
 * @description
 */
@Extension(bizId = "zhouj", useCase = "test", scenario = "hello")
public class HelloTestExtension implements ITestExtensionExtPt {
    @Override
    public String print() {
        System.out.println("Hello.......");
        return "Hello";
    }
}
