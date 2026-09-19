/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class FraudDecisionReleaseServiceTest {
    private final FraudDecisionReleaseService service = new FraudDecisionReleaseService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void releasesGovernedDecisionModel() {
        var result = service.assess(new FraudDecisionReleaseService.Request("F1", true, 2, 5, true,
                true, true, true, true, true, true, 0));
        assertThat(result.decision()).isEqualTo(FraudDecisionReleaseService.Decision.RELEASE);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void shadowsOperationallyIncompleteModel() {
        var result = service.assess(new FraudDecisionReleaseService.Request("F2", true, 2, 5, true,
                true, true, true, true, false, false, 0));
        assertThat(result.actions()).hasSize(2);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnsafeDecisionModel() {
        var result = service.assess(new FraudDecisionReleaseService.Request("F3", false, 10, 5, false,
                false, false, false, false, true, true, 1));
        assertThat(result.blockers()).hasSize(8);
    }
}
