package com.zhouj.endless.biz_rule.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 天启
 * @date 2020-02-28 16:33
 * @description 银行卡信息
 */
@Data
public class BankCardInfo implements Serializable {
    /**
     * 绑卡id
     */
    private String bankcardId;

    /**
     * 卡号
     */
    private String cardNo;
}
