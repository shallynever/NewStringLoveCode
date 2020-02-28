package com.zhouj.endless.biz_rule.enums;

/**
 * @author 天启
 * @date 2020-02-28 15:54
 * @description
 */
public enum RuleEnum {
    /**
     * 提现单创建前校验规则
     */
    WITHDRAW_CREATE_CHECK_BEFORE(1, "提现单创建前校验规则"),
    /**
     * 提现单创建后规则
     */
    WITHDRAW_CREATE_AFTER(2, "提现单创建后规则"),
    /**
     * 转账成功回调后规则
     */
    TRANSFER_CALLBACK_AFTER(3, "转账成功回调后规则"),
    /**
     * 获取提现银行卡信息规则
     */
    WITHDRAW_BANK_CARD(4, "获取提现银行卡信息规则");

    /**
     * 规则类型
     */
    private Integer bizType;

    /**
     * 规则类型描述
     */
    private String desc;

    RuleEnum(Integer bizType, String desc) {
        this.bizType = bizType;
        this.desc = desc;
    }

    public int getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
