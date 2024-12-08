package com.shanks.weaving.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
//@EnableJpaAuditing
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class JPAConfig extends JpaBaseConfiguration {

    protected JPAConfig(
            DataSource dataSource,
            JpaProperties properties,
            ObjectProvider<JtaTransactionManager> jtaTransactionManager
    ) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManager(
            EntityManagerFactoryBuilder factoryBuilder,
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("loadTimeWeaver") LoadTimeWeaver loadTimeWeaver
    ) {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.shanks.weaving");
        em.setDataSource(dataSource);
        em.setJpaVendorAdapter(adapter);
        em.setPersistenceUnitName("persistenceUnit");
        em.setLoadTimeWeaver(loadTimeWeaver);
        em.setJpaPropertyMap(getVendorProperties());
        return em;
    }

    @Primary
    @Bean(name = "transactionManager")
    public JpaTransactionManager getICPTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put("eclipselink.ddl-generation", "create-tables");
        map.put("eclipselink.ddl-generation.output-mode", "database");
        map.put("eclipselink.weaving", "true");
        map.put("eclipselink.logging.level.sql", "FINE");
        map.put("eclipselink.logging.parameters", "true");
        map.put("eclipselink.target-database", "org.eclipse.persistence.platform.database.H2Platform");
        // disabling EclipseLink L2 cache (Persistence Unit Cache)- different from
        // L1 cache (Persistence Context Cache) that operates at transactional/EntityManager level
        map.put("eclipselink.cache.shared.default", "false");
        return map;
    }

    @Bean
    public LoadTimeWeaver loadTimeWeaver(){
        return new InstrumentationLoadTimeWeaver();
    }

}

