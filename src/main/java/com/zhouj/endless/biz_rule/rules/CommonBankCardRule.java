package com.zhouj.endless.biz_rule.rules;

import com.zhouj.endless.biz_rule.IBizRule;
import com.zhouj.endless.biz_rule.dto.WithdrawBankCardDto;
import com.zhouj.endless.biz_rule.enums.RuleEnum;
import com.zhouj.endless.biz_rule.enums.WithdrawTypeEnum;
import com.zhouj.endless.biz_rule.model.BankCardInfo;
import org.springframework.stereotype.Component;

/**
 * @author 天启
 * @date 2020-02-28 16:30
 * @description 获取指定银行卡信息
 */
@Component
public class CommonBankCardRule implements IBizRule<WithdrawBankCardDto> {
    @Override
    public RuleEnum processRule() {
        return RuleEnum.WITHDRAW_BANK_CARD;
    }

    @Override
    public boolean canHandle(WithdrawBankCardDto withdrawBankCardDto) {
        return WithdrawTypeEnum.COMMON.name().equals(withdrawBankCardDto.getWithdrawRequest().getWithdrawType());
    }

    @Override
    public void execute(WithdrawBankCardDto withdrawBankCardDto) {
        BankCardInfo bankCardInfo = new BankCardInfo();
        bankCardInfo.setBankcardId(withdrawBankCardDto.getWithdrawRequest().getBindCardId());
        bankCardInfo.setCardNo("123456789");
        withdrawBankCardDto.setBankCardInfo(bankCardInfo);
    }
}
