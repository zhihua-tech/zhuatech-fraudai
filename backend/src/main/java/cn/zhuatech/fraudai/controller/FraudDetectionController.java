/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.controller;

import cn.zhuatech.fraudai.common.ApiResponse;
import cn.zhuatech.fraudai.service.FraudDetectionService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/ai/fraud")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class FraudDetectionController {
    private final FraudDetectionService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public FraudDetectionController(FraudDetectionService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/assess")
    public ApiResponse<FraudDetectionService.Result> assess(@Valid @RequestBody FraudDetectionService.Request request) {
        return ApiResponse.ok("交易风险评估完成", service.assess(request));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/link-risk")
    public ApiResponse<FraudDetectionService.LinkRiskResult> analyzeLinks(
        @Valid @RequestBody FraudDetectionService.LinkRiskRequest request) {
        return ApiResponse.ok("账户设备关联风险分析完成", service.analyzeLinks(request));
    }
}
