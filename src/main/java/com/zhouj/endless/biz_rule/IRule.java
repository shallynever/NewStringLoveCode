package com.zhouj.endless.biz_rule;

/**
 * @author 天启
 * @date 2020-02-28 15:57
 * @description 规则接口
 */
public interface IRule<T> {
    /**
     * 规则处理
     *
     * @param t 规则处理参数
     */
    void execute(T t);
}
