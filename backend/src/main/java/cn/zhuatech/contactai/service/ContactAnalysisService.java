/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.contactai.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 对会话情绪、等待、复联与合规信号进行可解释质检。 */
@Service
public class ContactAnalysisService {
    public Result analyze(Request request) {
        int score = Math.max(0, 55 - request.sentimentScore());
        if (request.waitSeconds() > 120) score += 18;
        else if (request.waitSeconds() > 45) score += 8;
        score += Math.min(24, request.repeatContacts() * 8);
        if (request.compliancePhraseMissed()) score += 30;
        if (request.refundAmount().compareTo(new BigDecimal("5000")) >= 0) score += 15;
        if (request.vip()) score += 8;
        score = Math.min(100, score);
        String severity = score >= 75 ? "CRITICAL" : score >= 45 ? "HIGH" : score >= 25 ? "MEDIUM" : "LOW";
        List<String> tags = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (request.sentimentScore() < 40) { tags.add("负向情绪"); actions.add("由资深坐席在 30 分钟内回访"); }
        if (request.repeatContacts() > 1) { tags.add("重复来电"); actions.add("合并历史工单并指定唯一责任人"); }
        if (request.compliancePhraseMissed()) { tags.add("合规话术缺失"); actions.add("暂停自动结案并进入合规复核"); }
        if (request.waitSeconds() > 120) tags.add("等待过长");
        if (tags.isEmpty()) { tags.add("服务正常"); actions.add("按常规抽检归档"); }
        return new Result(request.conversationId(), score, severity, score >= 75 ? "SUPERVISOR_NOW" : score >= 45 ? "REVIEW_TODAY" : "NORMAL_QUEUE",
            tags, actions, request.compliancePhraseMissed() || request.refundAmount().compareTo(new BigDecimal("10000")) >= 0);
    }

    public record Request(@NotBlank String conversationId, @Min(0) @Max(100) int sentimentScore,
                          @Min(0) int waitSeconds, @Min(0) @Max(10) int repeatContacts,
                          boolean compliancePhraseMissed, @DecimalMin("0") BigDecimal refundAmount,
                          boolean vip) {}
    public record Result(String conversationId, int riskScore, String severity, String queue,
                         List<String> tags, List<String> actions, boolean humanApprovalRequired) {}
}
