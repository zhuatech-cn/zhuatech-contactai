/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.contactai;
import cn.zhuatech.contactai.service.ContactAnalysisService; import org.junit.jupiter.api.Test; import java.math.BigDecimal; import static org.assertj.core.api.Assertions.assertThat;
class ContactAnalysisServiceTests { private final ContactAnalysisService service=new ContactAnalysisService();
 @Test void escalatesComplianceRisk(){var result=service.analyze(new ContactAnalysisService.Request("C-1001",25,180,3,true,new BigDecimal("12000"),true));assertThat(result.severity()).isEqualTo("CRITICAL");assertThat(result.humanApprovalRequired()).isTrue();}
 @Test void archivesNormalConversation(){var result=service.analyze(new ContactAnalysisService.Request("C-1002",88,12,0,false,BigDecimal.ZERO,false));assertThat(result.tags()).contains("服务正常");}}
