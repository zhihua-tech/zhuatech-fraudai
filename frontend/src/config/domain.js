/* Copyright 2026 上海如静知华信息科技有限公司 */
export const domain={
 code:'FRAUDAI',systemName:'知华交易反欺诈 AI 平台',englishName:'AI FRAUD DETECTION',theme:{primary:'#624d67',dark:'#332b3a',accent:'#c48245'},
 workspace:'风险管理中心 / 智能风控组',fieldWorkspace:'支付安全 / 调查一组',period:'2026-08-15 · 实时风控',liveText:'交易风险流于 10:39 更新',fieldContextLabel:'值守周期',fieldContext:'白班 08:00—20:00',fieldUser:'沈知微',fieldRole:'风险调查员',adminUser:'罗谨',adminRole:'反欺诈负责人',
 adminTitle:'交易风险控制中心',adminBreadcrumb:'反欺诈 AI / 全渠道态势',adminSubtitle:'结合交易、设备、位置与行为信号，对高风险事件进行拦截、验证和人工调查。',exportAction:'导出风控日报',createAction:'新建调查任务',
 chartTitle:'风险事件处置趋势',chartSubtitle:'已闭环比例 / 值守目标',chartLabels:['00时','03时','06时','09时','12时','15时','18时','21时','24时'],loadTitle:'渠道风险负荷',loadSubtitle:'调查队列占团队并发能力',recordsTitle:'重点风险调查任务',recordsSubtitle:'按风险分、金额和客户影响排序',issueTitle:'实时风险事件',issueSubtitle:'需要验证、冻结或跨团队升级',
 recordName:'调查任务',itemName:'风险场景',unitName:'调查团队',batchName:'触发策略',planName:'事件',doneName:'已核实',exceptionName:'风险',unitLabel:'笔',
 listBreadcrumb:'交易风控 / 调查任务',listSubtitle:'管理风险评分、增强验证、交易拦截和人工调查，保留每次规则与模型证据。',listSummary:[['今日交易','86,420'],['风险事件','238'],['待调查','32',true],['自动处置','78.6%']],tabs:['全部','待处理','进行中','待确认','已归档'],
 fieldBreadcrumb:'调查工作台 / 风险调查员',fieldTitle:'风险事件工作台',fieldSubtitle:'我的队列 12 项 · 高风险 4 项 · 平均处理 6.8 分钟',fieldSecondary:'查看值守日历',reportAction:'提交调查结论',fieldNoticeTitle:'实时决策服务正常',fieldNotice:'P95 决策时延 86 ms',
 steps:['实时评分','策略路由','增强验证','人工调查','结果回流'],documentAction:'查看调查指引',printAction:'导出证据包',resourceCardTitle:'风控资源状态',resourceValueLabel:'每秒决策',resourceHealthLabel:'服务健康度',quickSubtitle:'常用调查入口',
 quickActions:[['事件调查','/shopfloor/report','交易、设备与行为证据'],['风险场景','/shopfloor/material','策略、阈值和处置动作'],['决策资源','/shopfloor/resources','模型、规则和特征服务'],['重大升级','/shopfloor/andon','团伙、账户接管和损失事件']],
 reportDefaults:[6,1],reportTitle:'风险调查反馈',reportSubtitle:'记录欺诈与否、证据、损失和处置意见。',reportSuccess:'风险调查反馈已提交',reportPlaceholder:'填写交易背景、验证结果、风险证据和处置建议',reportFootnote:'冻结与黑名单决策必须由授权人员确认',ruleTitle:'自动决策安全门禁',ruleSubtitle:'FRAUD-AI · V1.0',rules:[['高额拦截','人工二次确认'],['增强验证','保留客户结果'],['特征使用','遵循最小必要'],['策略复盘','每日',true]],fieldTotals:[['12','我的队列'],['4','高风险事件'],['6.8分','平均处理'],['86ms','P95 时延']],
 adminMenus:[['/admin','home','风险控制中心'],['/admin/work-orders','order','调查任务'],['/admin/samples','box','风险场景'],['/admin/schedule','calendar','值守排班'],['/admin/methods','process','策略中心'],['/admin/reviews','quality','决策复核'],['/admin/resources','machine','风控资源'],['/admin/report','chart','风险分析']],
 fieldMenus:[['/shopfloor','home','调查工作台'],['/shopfloor/report','report','调查反馈'],['/shopfloor/tasks','order','我的队列'],['/shopfloor/material','box','风险场景'],['/shopfloor/resources','machine','决策状态'],['/shopfloor/andon','risk','重大升级',4]],
 moduleTitles:{tasks:['我的调查队列','查看风险分、金额和剩余时限'],material:['风险场景台账','查看策略、模型和处置动作'],resources:['风控资源中心','管理实时特征、规则和模型版本'],andon:['重大风险升级','提交团伙欺诈、账户接管和损失事件'],samples:['风险场景台账','维护渠道、客群、策略与责任人'],schedule:['值守与发布日历','协调调查班次和策略窗口'],methods:['策略中心','维护规则、阈值与增强验证'],reviews:['决策复核','记录申诉、误杀和调查结论'],report:['风险表现分析','分析拦截、损失、误报和响应时长']},
 tagline:'让风险决策更快，也让每次拦截有据可查',storyTitle:'模型负责发现可疑行为，<br/>调查人员负责最终判断',storyText:'将实时评分、增强验证与人工调查串成闭环，兼顾交易安全、客户体验和合规审计。',pattern:[2,5,7,10,13,16,18,21,24,27,31],loginStats:[['86,420','今日交易'],['78.6%','自动处置率'],['32','待调查事件']],loginTitle:'交易风险控制中心',adminDemo:'风险 / 策略 / 调查',fieldDemo:'事件 / 证据 / 处置'
}
export const records=[
 {no:'FR-260815-018',name:'高额新收款方转账',code:'NEW-PAYEE-HIGH',unit:'支付调查一组',group:'支付安全',plan:42,done:31,exception:8,due:'10:50',batch:'RF-102+模型',status:'进行中',progress:74,priority:'加急'},
 {no:'FR-260815-021',name:'异地设备账户接管',code:'ATO-DEVICE',unit:'账户安全组',group:'客户安全',plan:26,done:14,exception:6,due:'11:20',batch:'ATO-v3.6',status:'待确认',progress:54,priority:'加急'},
 {no:'FR-260815-026',name:'营销优惠套利团伙',code:'PROMO-ABUSE',unit:'营销风控组',group:'增长平台',plan:68,done:20,exception:11,due:'14:00',batch:'图谱策略',status:'待处理',progress:29,priority:'关注'},
 {no:'FR-260814-015',name:'商户退款异常',code:'REFUND-RISK',unit:'商户风控组',group:'支付安全',plan:34,done:34,exception:3,due:'08-14',batch:'规则集 2.4',status:'已归档',progress:100,priority:'正常'},
 {no:'FR-260815-031',name:'短时交易速度异常',code:'VELOCITY',unit:'支付调查二组',group:'支付安全',plan:54,done:33,exception:7,due:'15:30',batch:'实时规则',status:'进行中',progress:61,priority:'关注'}
]
export const resources=[{code:'DECISION-01',name:'实时风险决策引擎',unit:'风控平台组',status:'运行中',health:99,value:'486',valueUnit:'TPS',note:'P95 决策时延 86 ms'},{code:'FEATURE-02',name:'流式特征服务',unit:'数据风控组',status:'运行中',health:97,value:'128',valueUnit:'项',note:'关键特征新鲜度低于 2 秒'},{code:'GRAPH-03',name:'关联关系图谱',unit:'反欺诈算法组',status:'预警',health:89,value:'3.8亿',valueUnit:'边',note:'一个渠道同步延迟 12 分钟'}]
export const reviews=[{no:'RV-260815-032',title:'TX-48391 高额转账拦截',type:'冻结复核',detail:'风险 92 · 罗谨',result:'待确认'},{no:'RV-260815-011',title:'账户异地登录增强验证',type:'账户接管',detail:'设备首次出现 · 沈知微',result:'通过'},{no:'RV-260814-018',title:'忠诚客户高额交易误拦',type:'客户申诉',detail:'规则阈值待调整',result:'异常'}]
export const adminMetrics=[['今日交易','86,420','峰值 486 TPS','blue'],['风险事件','238','命中率 0.28%','orange'],['已阻断损失','¥386万','较昨日 +12%','green'],['待调查事件','32','4 项超过高风险线','red']]
export const fieldMetrics=[['我的队列','12','4 项高风险','blue'],['今日已调查','28','确认欺诈 9 项','green'],['即将超时','3','剩余不足 15 分钟','orange'],['平均处理','6.8分','较上周缩短 1.2 分','slate']]
export const chartActual=[62,68,72,77,81,84,87,90,92],chartTarget=[65,70,75,80,84,87,90,93,95]
export const loads=[['支付转账',88,'高风险 18 项'],['账户安全',76,'高风险 7 项'],['营销活动',64,'高风险 4 项'],['商户退款',52,'高风险 3 项']]
export const issues=[['拦截','同一设备关联 14 个账户连续转账','已冻结 8 笔高风险交易','调查中'],['策略','忠诚客户高额规则误报上升','建议增加历史行为豁免','待评审'],['数据','设备图谱同步延迟 12 分钟','已切换保守决策策略','处理中']].map(x=>({type:x[0],title:x[1],detail:x[2],status:x[3]}))
