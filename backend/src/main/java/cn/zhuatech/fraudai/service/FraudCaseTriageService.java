/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fraudai.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.BigDecimal;import java.util.*;
/** 将模型告警转为带处置优先级、SLA 与人工边界的调查案件。 */
@Service public class FraudCaseTriageService{
 public Result triage(Request r){List<String>gaps=new ArrayList<>(),actions=new ArrayList<>();int score=r.riskScore();
  if(r.accountTakeoverSignal())score+=15;if(r.vulnerableCustomer())score+=10;if(r.ongoingLoss())score+=15;if(r.amount().compareTo(r.highValueThreshold())>=0)score+=10;score=Math.min(100,score);
  if(!r.evidenceTraceable())gaps.add("告警证据无法回溯到原始事件");if(!r.modelExplanationAvailable())gaps.add("模型告警缺少可解释原因");if(!r.analystAssigned())gaps.add("尚未指派调查分析师");
  Priority p=score>=85||r.ongoingLoss()?Priority.P1:score>=65?Priority.P2:Priority.P3;int sla=p==Priority.P1?1:p==Priority.P2?4:24;
  Decision d=!r.evidenceTraceable()?Decision.HOLD:p==Priority.P1?Decision.CONTAIN:p==Priority.P2?Decision.INVESTIGATE:Decision.MONITOR;
  if(d==Decision.CONTAIN)actions.add("临时限制高风险交易并由人工复核是否解除");if(r.accountTakeoverSignal())actions.add("启动账户接管核验与凭证轮换流程");if(r.vulnerableCustomer())actions.add("启用弱势客户保护和人工联络");if(actions.isEmpty())actions.add("进入常规调查队列并持续关联新事件");
  return new Result(d,p,score,sla,List.copyOf(gaps),List.copyOf(actions));}
 public record Request(@NotBlank String caseId,@Min(0)@Max(100)int riskScore,@DecimalMin("0")BigDecimal amount,@DecimalMin("0")BigDecimal highValueThreshold,boolean accountTakeoverSignal,boolean vulnerableCustomer,boolean ongoingLoss,boolean evidenceTraceable,boolean modelExplanationAvailable,boolean analystAssigned){}
 public record Result(Decision decision,Priority priority,int adjustedRiskScore,int responseSlaHours,List<String>governanceGaps,List<String>actions){}
 public enum Decision{MONITOR,INVESTIGATE,CONTAIN,HOLD}public enum Priority{P1,P2,P3}
}
