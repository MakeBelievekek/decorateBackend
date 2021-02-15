package com.example.decorate.config;

import com.example.decorate.domain.dto.BarionConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yamlconfig")
public class YAMLConfig {

    BarionConfig barionConfig;

    public BarionConfig getBarionConfig() {
        return barionConfig;
    }
    public void setBarionConfig(BarionConfig barionConfig) {
        this.barionConfig = barionConfig;
    }

    @Override
    public String toString() {
        return "YAMLConfig{" +
                "barionConfig=" + barionConfig +
                '}';
    }
}

