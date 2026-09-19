/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contactai.controller;

import cn.zhuatech.contactai.common.ApiResponse;
import cn.zhuatech.contactai.service.ContactAnalysisService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/ai/contact")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class ContactAnalysisController {
    private final ContactAnalysisService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ContactAnalysisController(ContactAnalysisService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/analyze")
    public ApiResponse<ContactAnalysisService.Result> analyze(@Valid @RequestBody ContactAnalysisService.Request request) {
        return ApiResponse.ok("会话质检完成", service.analyze(request));
    }
}
