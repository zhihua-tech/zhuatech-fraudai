# 企业级反欺诈决策发布

`POST /api/enterprise/fraudai/fraud-decision-release` 校验拦截阈值、误报率、公平性、可解释原因码、特征新鲜度、人工复核、模型审批、监控和回滚，返回 `RELEASE / SHADOW / BLOCKED`。

生产环境应使用影子流量和分阶段发布验证模型，保留每次决策的规则、特征、版本、原因码与人工处置，并为客户提供合适的申诉和复核渠道。
