package com.zhouj.endless.biz_rule.dto;

import com.zhouj.endless.biz_rule.model.BankCardInfo;
import com.zhouj.endless.biz_rule.param.WithdrawRequest;
import lombok.Data;

/**
 * @author 天启
 * @date 2020-02-28 16:32
 * @description 获取提现银行卡信息规则参数
 */
@Data
public class WithdrawBankCardDto {
    /**
     * 卡信息
     */
    private BankCardInfo bankCardInfo;

    /**
     * 提现请求参数
     */
    private WithdrawRequest withdrawRequest;


}
