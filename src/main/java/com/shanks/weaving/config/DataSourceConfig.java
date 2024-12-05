package com.shanks.weaving.config;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
public class DataSourceConfig {

    @Bean(name = "defaultDataSource")
    @ConfigurationProperties(prefix = "database")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name= "dataSource")
    public DataSource getProxyDataSource(DataSource dataSource) {
        return ProxyDataSourceBuilder
                .create(dataSource)
                .name("proxyDataSource")
                .countQuery()
                .logSlowQueryBySlf4j(60, TimeUnit.SECONDS)
                .multiline()
                .build();
    }

}
