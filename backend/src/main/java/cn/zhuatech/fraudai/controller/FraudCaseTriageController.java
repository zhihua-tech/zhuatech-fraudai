/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.controller;import cn.zhuatech.fraudai.common.ApiResponse;import cn.zhuatech.fraudai.service.FraudCaseTriageService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController@RequestMapping("/api/enterprise/fraudai")public class FraudCaseTriageController{private final FraudCaseTriageService service;/**
                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                              */
public FraudCaseTriageController(FraudCaseTriageService s){service=s;}/**
                                                                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                    */
@PostMapping("/case-triage")public ApiResponse<FraudCaseTriageService.Result>triage(@Valid@RequestBody FraudCaseTriageService.Request r){return ApiResponse.ok("反欺诈案件分级完成",service.triage(r));}}
