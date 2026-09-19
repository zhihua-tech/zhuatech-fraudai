/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.service;import org.junit.jupiter.api.Test;import java.math.BigDecimal;import static org.assertj.core.api.Assertions.assertThat;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class FraudCaseTriageServiceTest{private final FraudCaseTriageService s=new FraudCaseTriageService();
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void monitorsLowRiskCase(){var r=s.triage(req(30,false,false,false,true));assertThat(r.decision()).isEqualTo(FraudCaseTriageService.Decision.MONITOR);assertThat(r.responseSlaHours()).isEqualTo(24);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void containsOngoingAccountTakeover(){var r=s.triage(req(65,true,true,true,true));assertThat(r.decision()).isEqualTo(FraudCaseTriageService.Decision.CONTAIN);assertThat(r.priority()).isEqualTo(FraudCaseTriageService.Priority.P1);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void holdsUntraceableAlert(){var r=s.triage(req(75,false,false,false,false));assertThat(r.decision()).isEqualTo(FraudCaseTriageService.Decision.HOLD);assertThat(r.governanceGaps()).contains("告警证据无法回溯到原始事件");}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private FraudCaseTriageService.Request req(int score,boolean takeover,boolean vulnerable,boolean ongoing,boolean evidence){return new FraudCaseTriageService.Request("C-1",score,new BigDecimal("5000"),new BigDecimal("10000"),takeover,vulnerable,ongoing,evidence,true,true);}}
