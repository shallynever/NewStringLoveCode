package com.zhouj.endless.cloa_extension;

import com.zhouj.endless.BaseTest;
import com.zhouj.endless.cola_extension.BizScenario;
import com.zhouj.endless.cola_extension.manager.IExtensionExecutor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 天启
 * @date 2020-02-28 09:45
 * @description 扩展点测试
 */
public class ExtensionExecutorTest extends BaseTest {

    @Autowired
    private IExtensionExecutor extensionExecutor;

    @Test
    public void testHello() {
        extensionExecutor.execute(ITestExtensionExtPt.class, BizScenario.valueOf("zhouj", "test", "hello"), iTestExtension -> iTestExtension.print());
    }

    @Test
    public void testH() {
        extensionExecutor.execute(ITestExtensionExtPt.class, BizScenario.valueOf("zhouj", "test", "hi"), iTestExtension -> iTestExtension.print());
    }


}