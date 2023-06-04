package com.nrx.basicauth.config;

import com.nrx.basicauth.dto.ModuleDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "modules")
@Configuration
@Getter
@Setter
public class AppProperties {

    public void setCredentials(List<ModuleDto> credentials) {
        this.credentials = credentials;
    }

    private List<ModuleDto> credentials;

    @Bean
    public AppProperties myAppProperties() {
        return new AppProperties();
    }
}
