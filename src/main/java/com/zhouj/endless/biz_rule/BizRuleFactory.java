package com.zhouj.endless.biz_rule;

import com.google.common.collect.Lists;
import com.zhouj.endless.biz_rule.enums.RuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @author 天启
 * @date 2020-02-28 16:08
 * @description 业务规则基础工厂
 */
@Component
public class BizRuleFactory {

    @Autowired
    private List<IBizRule> bizRuleList;


    private static MapList<Integer, IBizRule> bizMapList;

    public static <T> List<IBizRule<T>> getBizRule(T t, RuleEnum rule) {
        if (rule == null) {
            throw new RuntimeException("");
        }
        if (t == null) {
            throw new RuntimeException("");
        }
        if (bizMapList == null) {
            return Collections.EMPTY_LIST;
        }
        List<IBizRule> bizRules = bizMapList.get(rule.getBizType());
        if (CollectionUtils.isEmpty(bizRules)) {
            return Collections.EMPTY_LIST;
        }
        List<IBizRule<T>> filterRules = Lists.newArrayList();
        for (IBizRule bizRule : bizRules) {
            if (bizRule.canHandle(t)) {
                filterRules.add(bizRule);
            }
        }
        return filterRules;
    }

    @PostConstruct
    private void init() {
        if (bizMapList == null) {
            bizMapList = new MapList<>();
        }
        if (CollectionUtils.isEmpty(bizRuleList)) {
            return;
        }
        for (IBizRule bizRule : bizRuleList) {
            if (bizRule.processRule() == null) {
                throw new RuntimeException("");
            }
            bizMapList.put(bizRule.processRule().getBizType(), bizRule);
        }
    }



}
