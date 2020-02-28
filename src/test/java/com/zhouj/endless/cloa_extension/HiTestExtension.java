package com.zhouj.endless.cloa_extension;

import com.zhouj.endless.cola_extension.Extension;

/**
 * @author 天启
 * @date 2020-02-28 10:00
 * @description
 */
@Extension(bizId = "zhouj", useCase = "test", scenario = "hi")
public class HiTestExtension implements ITestExtensionExtPt {
    @Override
    public String print() {
        System.out.println("Hi.........");
        return "Hi";
    }
}
