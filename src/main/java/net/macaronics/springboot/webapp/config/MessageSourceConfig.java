package net.macaronics.springboot.webapp.config;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceConfig {
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages"); // 메시지 파일의 경로
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600); // 캐시 기간을 1시간으로 설정
        return messageSource;
    }
}
