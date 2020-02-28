package com.zhouj.endless.cola_extension.manager;

import com.zhouj.endless.cola_extension.BizScenario;
import com.zhouj.endless.cola_extension.ExtensionCoordinate;
import com.zhouj.endless.cola_extension.ExtensionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 天启
 * @date 2020-02-27 22:30
 * @description
 */
@Slf4j
@Component
public class ExtensionExecutor extends AbstractComponentExecutor {
    @Resource
    private ExtensionRepository extensionRepository;

    @Override
    protected <Ext> Ext locateComponent(Class<Ext> targetClz, BizScenario bizScenario) {
        Ext extension = locateExtension(targetClz, bizScenario);
        log.debug("[Located Extension]: " + extension.getClass().getSimpleName());
        return extension;
    }

    /**
     * if the bizScenarioUniqueIdentity is "ali.tmall.supermarket"
     * <p>
     * the search path is as below:
     * 1、first try to get extension by "ali.tmall.supermarket", if get, return it.
     * 2、loop try to get extension by "ali.tmall", if get, return it.
     * 3、loop try to get extension by "ali", if get, return it.
     * 4、if not found, try the default extension
     *
     * @param targetClz 扩展点类
     */
    private <Ext> Ext locateExtension(Class<Ext> targetClz, BizScenario bizScenario) {
        checkNull(bizScenario);

        Ext extension;
        String bizScenarioUniqueIdentity = bizScenario.getUniqueIdentity();
        log.debug("BizScenario in locateExtension is : " + bizScenarioUniqueIdentity);

        // first try
        extension = firstTry(targetClz, bizScenarioUniqueIdentity);
        if (extension != null) {
            return extension;
        }

        // loop try
        extension = loopTry(targetClz, bizScenarioUniqueIdentity);
        if (extension != null) {
            return extension;
        }

        throw new RuntimeException("Can not find extension with ExtensionPoint: " + targetClz + " BizScenario:" + bizScenarioUniqueIdentity);
    }

    private <Ext> Ext firstTry(Class<Ext> targetClz, String bizScenario) {
        return (Ext) extensionRepository.getExtensionRepo().get(new ExtensionCoordinate(targetClz.getName(), bizScenario));
    }

    private <Ext> Ext loopTry(Class<Ext> targetClz, String bizScenario) {
        Ext extension;
        if (bizScenario == null) {
            return null;
        }
        int lastDotIndex = bizScenario.lastIndexOf(BizScenario.DOT_SEPARATOR);
        while (lastDotIndex != -1) {
            bizScenario = bizScenario.substring(0, lastDotIndex);
            extension = (Ext) extensionRepository.getExtensionRepo().get(new ExtensionCoordinate(targetClz.getName(), bizScenario));
            if (extension != null) {
                return extension;
            }
            lastDotIndex = bizScenario.lastIndexOf(BizScenario.DOT_SEPARATOR);
        }
        return null;
    }


    private void checkNull(BizScenario bizScenario) {
        if (bizScenario == null) {
            throw new RuntimeException("BizScenario can not be null for extension");
        }
    }


}
