/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.fraudai.controller;

import cn.zhuatech.fraudai.common.ApiResponse;
import cn.zhuatech.fraudai.service.FraudDetectionService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/fraud")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class FraudDetectionController {
    private final FraudDetectionService service;
    public FraudDetectionController(FraudDetectionService service) { this.service = service; }

    @PostMapping("/assess")
    public ApiResponse<FraudDetectionService.Result> assess(@Valid @RequestBody FraudDetectionService.Request request) {
        return ApiResponse.ok("交易风险评估完成", service.assess(request));
    }
}
