package com.zhouj.endless.biz_rule;

import com.zhouj.endless.biz_rule.enums.RuleEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 天启
 * @date 2020-02-28 15:52
 * @description
 */
@Slf4j
@Component
public class BizRuleExecutor {

    /**
     * 规则执行
     *
     * @param t           参数
     * @param ruleBizEnum 执行的规则类型
     * @param <T>         参数类型
     */
    public static <T> void execute(T t, RuleEnum ruleBizEnum) {
        if (t == null || ruleBizEnum == null) {
            throw new RuntimeException("规则错误");
        }
        List<IBizRule<T>> ruleList = BizRuleFactory.getBizRule(t, ruleBizEnum);
        if (CollectionUtils.isEmpty(ruleList)) {
            return;
        }
        for (IBizRule bizRule : ruleList) {
            //规则执行
            bizRule.execute(t);
        }
    }
}
