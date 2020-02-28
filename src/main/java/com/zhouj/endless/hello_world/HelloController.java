package com.zhouj.endless.hello_world;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 天启
 * @date 2020-02-27 21:13
 * @description Hello服务检测
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        return "hello world!";
    }
}
