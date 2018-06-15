package com.ac.journalentries.configuration;

import static org.hibernate.cfg.AvailableSettings.*;

import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "datasource.journalentries")
@EnableJpaRepositories(basePackages = "com.ac.journalentries.repositories", 
                       entityManagerFactoryRef = "entityManagerFactoryRef", 
                       transactionManagerRef = "transactionManagerRef")
@EnableTransactionManagement
public class JpaConfig extends HikariConfig {
	@Autowired
	private Environment environment;

	@Value("${datasource.sampleapp.maxPoolSize:10}")
	private int maxPoolSize;

	@Bean(name = "jeDataSource")
	@Primary
	public DataSource jeDataSource() {
		return new HikariDataSource(this);
	}

	/*
	 * Entity Manager Factory setup.
	 */
	@Bean(name = "entityManagerFactoryRef")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(jeDataSource());
		factoryBean.setPackagesToScan(new String[] { "com.ac.journalentries.model" });
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Provider specific adapter.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		return hibernateJpaVendorAdapter;
	}

	/*
	 * Here you can specify any provider specific properties.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				environment.getRequiredProperty("datasource.journalentries.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto",
				environment.getRequiredProperty("datasource.journalentries.hibernate.hbm2ddl.method"));
		properties.put("hibernate.show_sql",
				environment.getRequiredProperty("datasource.journalentries.hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				environment.getRequiredProperty("datasource.journalentries.hibernate.format_sql"));
		properties.put(HBM2DDL_IMPORT_FILES, "sql/je_data.sql");
		if (StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.journalentries.defaultSchema"))) {
			properties.put("hibernate.default_schema",
					environment.getRequiredProperty("datasource.journalentries.defaultSchema"));
		}
		return properties;
	}

	@Bean(name="transactionManagerRef")
	@Primary
	public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactoryRef") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return txManager;
	}
}
