# ContactAI API

版权所有 © 2026 上海如静知华信息科技有限公司。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 联络中心质量驾驶舱 |
| GET | `/api/admin/work-orders` | 质检任务列表 |
| GET | `/api/shopfloor/dashboard` | 质检专员工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交人工复核 |
| POST | `/api/ai/contact/analyze` | 分析会话风险与升级策略 |

分析接口输入会话编号、情绪分、等待时间、重复联系、合规缺失、退款金额与 VIP 标记；输出风险、队列、标签、动作和人工审批要求。
