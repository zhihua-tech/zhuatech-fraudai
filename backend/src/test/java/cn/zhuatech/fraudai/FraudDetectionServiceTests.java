/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.fraudai;

import cn.zhuatech.fraudai.service.FraudDetectionService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class FraudDetectionServiceTests {
    private final FraudDetectionService service = new FraudDetectionService();

    @Test void blocksImpossibleTravelAndVelocitySpike() {
        var result = service.assess(new FraudDetectionService.Request("TX-001", new BigDecimal("18000"),
            new BigDecimal("1200"), 11, 82, 75, true, true));
        assertThat(result.decision()).isEqualTo("BLOCK");
        assertThat(result.riskScore()).isGreaterThanOrEqualTo(70);
    }

    @Test void allowsNormalReturningTransaction() {
        var result = service.assess(new FraudDetectionService.Request("TX-002", new BigDecimal("600"),
            new BigDecimal("800"), 1, 10, 8, false, false));
        assertThat(result.decision()).isEqualTo("ALLOW");
        assertThat(result.nextStep()).contains("正常放行");
    }
}
