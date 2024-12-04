package com.shanks.weaving.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class CoreConfig {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "database")
    public DataSource getICPDataSource() {
        return DataSourceBuilder.create().build();
    }

}
