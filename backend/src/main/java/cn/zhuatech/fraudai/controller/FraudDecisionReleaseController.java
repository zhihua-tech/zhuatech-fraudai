/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.controller;

import cn.zhuatech.fraudai.common.ApiResponse;
import cn.zhuatech.fraudai.service.FraudDecisionReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/fraudai")
public class FraudDecisionReleaseController {
    private final FraudDecisionReleaseService service;
    public FraudDecisionReleaseController(FraudDecisionReleaseService service) { this.service = service; }
    @PostMapping("/fraud-decision-release")
    public ApiResponse<FraudDecisionReleaseService.Assessment> assess(
            @Valid @RequestBody FraudDecisionReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
