/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contactai;
import cn.zhuatech.contactai.service.ContactAnalysisService; import org.junit.jupiter.api.Test; import java.math.BigDecimal; import static org.assertj.core.api.Assertions.assertThat;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ContactAnalysisServiceTests { private final ContactAnalysisService service=new ContactAnalysisService();
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void escalatesComplianceRisk(){var result=service.analyze(new ContactAnalysisService.Request("C-1001",25,180,3,true,new BigDecimal("12000"),true));assertThat(result.severity()).isEqualTo("CRITICAL");assertThat(result.humanApprovalRequired()).isTrue();}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void archivesNormalConversation(){var result=service.analyze(new ContactAnalysisService.Request("C-1002",88,12,0,false,BigDecimal.ZERO,false));assertThat(result.tags()).contains("服务正常");}}
