package jp.alhinc.orchestra.ticket.app;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Mapper dorzerMapper() {
        return new DozerBeanMapperBuilder();
    }
}
