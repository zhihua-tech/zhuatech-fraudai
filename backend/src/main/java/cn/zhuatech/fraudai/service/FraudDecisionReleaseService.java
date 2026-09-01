/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDecisionReleaseService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.thresholdValidated()) blockers.add("欺诈拦截阈值未完成业务验证");
        if (request.falsePositiveRate() > request.maxFalsePositiveRate()) blockers.add("误报率超过业务容忍阈值");
        if (!request.fairnessAssessmentPassed()) blockers.add("公平性与差异影响评估未通过");
        if (!request.explainabilityReady()) blockers.add("决策解释和原因码未就绪");
        if (!request.dataFreshnessPassed()) blockers.add("特征数据新鲜度未通过");
        if (!request.manualReviewRouteReady()) blockers.add("人工复核与申诉路径未就绪");
        if (!request.modelApproved()) blockers.add("模型与规则版本未批准");
        if (request.openCriticalFindings() > 0) blockers.add("存在未关闭的严重风控问题");
        if (!blockers.isEmpty()) {
            actions.add("阻断实时决策发布并完成模型、数据与流程整改");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.monitoringReady() || !request.rollbackReady()) {
            if (!request.monitoringReady()) actions.add("配置误报、漏报、漂移和业务损失监控");
            if (!request.rollbackReady()) actions.add("准备规则与模型快速回退版本");
            return new Assessment(Decision.SHADOW, blockers, actions);
        }
        actions.add("批准实时发布并持续复核误报、申诉和风险损失");
        return new Assessment(Decision.RELEASE, blockers, actions);
    }

    public record Request(@NotBlank String modelVersion, boolean thresholdValidated,
                          @DecimalMin("0.0") double falsePositiveRate,
                          @DecimalMin("0.0") double maxFalsePositiveRate,
                          boolean fairnessAssessmentPassed, boolean explainabilityReady,
                          boolean dataFreshnessPassed, boolean manualReviewRouteReady,
                          boolean modelApproved, boolean monitoringReady, boolean rollbackReady,
                          @Min(0) int openCriticalFindings) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { RELEASE, SHADOW, BLOCKED }
}
