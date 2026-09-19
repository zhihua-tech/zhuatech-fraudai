/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.controller;

import cn.zhuatech.fraudai.common.ApiResponse;
import cn.zhuatech.fraudai.service.FraudDecisionReleaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/fraudai")
public class FraudDecisionReleaseController {
    private final FraudDecisionReleaseService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public FraudDecisionReleaseController(FraudDecisionReleaseService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/fraud-decision-release")
    public ApiResponse<FraudDecisionReleaseService.Assessment> assess(
            @Valid @RequestBody FraudDecisionReleaseService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
