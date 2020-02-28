package com.zhouj.endless.biz_rule;

import com.zhouj.endless.biz_rule.enums.RuleEnum;

/**
 * @author 天启
 * @date 2020-02-28 15:59
 * @description
 */
public interface IBizRule<T> extends IRule<T> {

    /**
     * 返回当前规则处理的规则类型
     *
     * @return RuleEnum
     */
    RuleEnum processRule();

    /**
     * 根据规则处理参数，判断是可以执行当前业务
     *
     * @param t 规则处理参数
     * @return boolean
     */
    boolean canHandle(T t);

}
