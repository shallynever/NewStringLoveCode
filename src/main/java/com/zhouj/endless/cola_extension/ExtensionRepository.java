package com.zhouj.endless.cola_extension;

import com.zhouj.endless.cola_extension.manager.IExtensionPoint;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 天启
 * @date 2020-02-27 22:43
 * @description 扩展点仓库
 */
@Component
public class ExtensionRepository {
    @Getter
    private Map<ExtensionCoordinate, IExtensionPoint> extensionRepo = new HashMap<>();
}
