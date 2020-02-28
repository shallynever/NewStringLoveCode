package com.zhouj.endless.biz_rule.enums;

/**
 * @author 天启
 * @date 2020-02-28 16:35
 * @description
 */
public enum WithdrawTypeEnum {

    /**
     * 个人对私提现到指定卡
     */
    COMMON("个人对私提现到指定卡"),

    /**
     * 个人对私提现到默认卡
     */
    DEFAULT("个人对私提现到默认卡"),

    /**
     * 个人提现到关联企业对公卡
     */
    CORPORATE("个人提现到关联企业对公卡");

    private String desc;

    public String getDesc() {
        return desc;
    }

    WithdrawTypeEnum(String desc) {
        this.desc = desc;
    }
}
