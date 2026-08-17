/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.contactai.controller;

import cn.zhuatech.contactai.common.ApiResponse;
import cn.zhuatech.contactai.service.ContactAnalysisService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/contact")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class ContactAnalysisController {
    private final ContactAnalysisService service;
    public ContactAnalysisController(ContactAnalysisService service) { this.service = service; }
    @PostMapping("/analyze")
    public ApiResponse<ContactAnalysisService.Result> analyze(@Valid @RequestBody ContactAnalysisService.Request request) {
        return ApiResponse.ok("会话质检完成", service.analyze(request));
    }
}
