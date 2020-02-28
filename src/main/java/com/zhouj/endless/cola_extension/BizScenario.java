package com.zhouj.endless.cola_extension;

/**
 * @author 天启
 * @date 2020-02-27 21:31
 * @description 业务场景
 */
public class BizScenario {
    public final static String DEFAULT_BIZ_ID = "defaultBizId";
    public final static String DEFAULT_USE_CASE = "defaultUseCase";
    public final static String DEFAULT_SCENARIO = "defaultScenario";
    public final static String DOT_SEPARATOR = ".";

    /**
     * 业务ID（Business）：就是一个自负盈亏的财务主体，比如tmall、淘宝和零售通就是三个不同的业务。
     * 业务场景ID,如果只有一个业务场景，该值可空。比如tmall、淘宝和零售通就是三个不同的业务。
     */
    private String bizId = DEFAULT_BIZ_ID;

    /**
     * 用例（Use Case）：描述了用户和系统之间的互动，每个用例提供了一个或多个场景。比如，支付订单就是一个典型的用例。
     */
    private String useCase = DEFAULT_USE_CASE;

    /**
     * 场景（Scenario）：场景也被称为用例的实例（Instance），包括用例所有的可能情况（正常的和异常的）。
     * 比如对于“订单支付”这个用例，就有“可以使用花呗”，“支付宝余额不足”，“银行账户余额不足”等多个场景。
     */
    private String scenario = DEFAULT_SCENARIO;

    /**
     * 获取业务场景唯一标识
     *
     * @return BizScenario
     */
    public String getUniqueIdentity() {
        return bizId + DOT_SEPARATOR + useCase + DOT_SEPARATOR + scenario;
    }

    public static BizScenario valueOf(String bizId, String useCase, String scenario) {
        BizScenario bizScenario = new BizScenario();
        bizScenario.bizId = bizId;
        bizScenario.useCase = useCase;
        bizScenario.scenario = scenario;
        return bizScenario;
    }

    public static BizScenario valueOf(String bizId) {
        return BizScenario.valueOf(bizId, DEFAULT_USE_CASE, DEFAULT_SCENARIO);
    }

    public static BizScenario valueOf(String useCase, String scenario) {
        return BizScenario.valueOf(DEFAULT_BIZ_ID, useCase, scenario);
    }

    public static BizScenario newDefault() {
        return BizScenario.valueOf(DEFAULT_BIZ_ID, DEFAULT_USE_CASE, DEFAULT_SCENARIO);
    }
}
