package com.zhouj.endless.biz_rule.param;

import lombok.Data;

/**
 * @author 天启
 * @date 2020-02-28 16:35
 * @description
 */
@Data
public class WithdrawRequest {
    /**
     * 提现类型
     */
    private String withdrawType;

    /**
     * 是否需要审核（默认需要）
     */
    private Boolean needAudit = true;

    /**
     * 绑定卡号id
     */
    private String bindCardId;
}
