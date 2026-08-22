/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {
    public Result assess(Request request) {
        int score = 0;
        if (request.amount().compareTo(request.customerAverageAmount().multiply(new BigDecimal("5"))) >= 0) score += 30;
        else if (request.amount().compareTo(request.customerAverageAmount().multiply(new BigDecimal("2"))) >= 0) score += 15;
        if (request.transactionsLastHour() >= 8) score += 25;
        else if (request.transactionsLastHour() >= 4) score += 12;
        score += Math.round(request.deviceRiskScore() * 0.20f);
        score += Math.round(request.locationRiskScore() * 0.15f);
        if (request.newPayee()) score += 12;
        if (request.impossibleTravel()) score += 35;
        score = Math.min(100, score);
        String decision = score >= 70 ? "BLOCK" : score >= 40 ? "CHALLENGE" : "ALLOW";
        List<String> reasons = new ArrayList<>();
        if (request.impossibleTravel()) reasons.add("出现不可能旅行特征");
        if (request.transactionsLastHour() >= 8) reasons.add("短时交易频率异常");
        if (request.newPayee()) reasons.add("首次向该收款方付款");
        if (request.deviceRiskScore() >= 70) reasons.add("设备风险评分偏高");
        if (reasons.isEmpty()) reasons.add("未命中高风险行为规则");
        String nextStep = "BLOCK".equals(decision) ? "冻结交易并转人工调查"
            : "CHALLENGE".equals(decision) ? "触发增强身份验证" : "正常放行并留存评分";
        return new Result(request.transactionNo(), score, decision, nextStep, reasons, true);
    }

    public LinkRiskResult analyzeLinks(LinkRiskRequest request) {
        int score = Math.min(100,
            Math.min(request.sharedDeviceAccounts() * 6, 30)
                + Math.min(request.sharedIpAccounts() * 3, 15)
                + Math.min(request.highRiskNeighbors() * 12, 36)
                + Math.min(request.suspiciousTransactions24Hours() * 4, 20)
                + (request.deviceFingerprintChanged() ? 10 : 0));
        String clusterLevel = score >= 70 ? "HIGH" : score >= 40 ? "MEDIUM" : "LOW";
        String action = score >= 70 ? "HOLD_AND_INVESTIGATE"
            : score >= 40 ? "CHALLENGE_AND_MONITOR" : "MONITOR";
        List<String> evidence = new ArrayList<>();
        if (request.sharedDeviceAccounts() >= 5) evidence.add("同设备关联账户数量异常");
        if (request.highRiskNeighbors() > 0) evidence.add("关联网络中存在已知高风险账户");
        if (request.suspiciousTransactions24Hours() >= 3) evidence.add("关联账户短时可疑交易集中");
        if (request.deviceFingerprintChanged()) evidence.add("设备指纹近期发生显著变化");
        if (evidence.isEmpty()) evidence.add("未发现显著的账户设备聚集风险");
        int reviewPriority = Math.min(5, Math.max(1, (score + 19) / 20));
        return new LinkRiskResult(request.subjectAccount(), score, clusterLevel, action,
            reviewPriority, evidence, request.sharedDeviceAccounts() + request.sharedIpAccounts());
    }

    public record Request(@NotBlank String transactionNo,
                          @DecimalMin("0.01") BigDecimal amount,
                          @DecimalMin("0.01") BigDecimal customerAverageAmount,
                          @Min(0) int transactionsLastHour,
                          @Min(0) @Max(100) int deviceRiskScore,
                          @Min(0) @Max(100) int locationRiskScore,
                          boolean newPayee, boolean impossibleTravel) {}
    public record Result(String transactionNo, int riskScore, String decision,
                         String nextStep, List<String> reasons,
                         boolean manualReviewSupported) {}
    public record LinkRiskRequest(@NotBlank String subjectAccount,
                                  @Min(0) int sharedDeviceAccounts,
                                  @Min(0) int sharedIpAccounts,
                                  @Min(0) int highRiskNeighbors,
                                  @Min(0) int suspiciousTransactions24Hours,
                                  boolean deviceFingerprintChanged) {}
    public record LinkRiskResult(String subjectAccount, int networkRiskScore,
                                 String clusterRiskLevel, String recommendedAction,
                                 int reviewPriority, List<String> evidence,
                                 int observedLinkCount) {}
}
