package pe.com.interbank.spectrum.promart.microservice.domain.config;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import oracle.jdbc.pool.OracleDataSource;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import pe.com.interbank.spectrum.promart.microservice.util.Constantes;
import pe.com.interbank.spectrum.promart.microservice.util.PtCache;
import pe.com.interbank.spectrum.transference.microservice.domain.repository.CollectSP;
import pe.com.interbank.spectrum.transference.microservice.util.Encryptor;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"pe.com.interbank.spectrum.promart.microservice.domain.repository" })
@EnableAutoConfiguration(exclude = { DataSourceTransactionManagerAutoConfiguration.class,
		DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableTransactionManagement
public class DatabaseConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "entityManager")
	public EntityManager entityManager() throws SQLException {
		return entityManagerFactory().getObject().createEntityManager();
	}
//	@Bean(name="checkInUpdateStoreProcedure")
//	public CheckInUpdateStoreProcedure checkInUpdateStoreProcedure(@Qualifier("dataSource") DataSource dataSource){
//		return new CheckInUpdateStoreProcedure(dataSource);
//	}
//	
//	@Bean(name="getTransactionSP")
//	public GetTransactionsSP getTransactionsSP(@Qualifier("dataSource") DataSource dataSource){
//		return new GetTransactionsSP(dataSource);
//	}
//	
//	@Bean(name="getTransactionReportsSP")
//    public GetTransactionReportsSP getTransactionReportsSP(@Qualifier("dataSource") DataSource dataSource){
//        return new GetTransactionReportsSP(dataSource);
//    }
//	
//	@Bean(name="saveTransTempSP")
//	public SaveTransTempSP saveTransTempSP(@Qualifier("dataSource") DataSource dataSource){
//		return new SaveTransTempSP(dataSource);
//	}
//	
//	
//	@Bean(name="getValidTransactionSP")
//	public GetValidTransactionSP getValidTransactionSP(@Qualifier("dataSource") DataSource dataSource){
//		return new GetValidTransactionSP(dataSource);
//	}	
//	
//	@Bean(name="getPaySP")
//    public GetPaySP getPaySP(@Qualifier("dataSource") DataSource dataSource){
//        return new GetPaySP(dataSource);
//    } 
	


	Properties jpaProperties() {
		Properties properties = new Properties();

		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("hibernate.max_fetch_depth", "3");
		properties.setProperty("hibernate.jdbc.fetch_size", "50");
		properties.setProperty("hibernate.jdbc.batch_size", "10");
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("org.hibernate.flushMode","COMMIT");
		properties.setProperty("hibernate.enable_lazy_load_no_trans","true");
		return properties;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("pe.com.interbank.spectrum.transaction.microservice.domain.entity");
		emf.setPersistenceUnitName("default"); // <- giving 'default' as name
		emf.setJpaProperties(jpaProperties());
		emf.afterPropertiesSet();
		return emf;
	}


	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager() throws SQLException {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory().getObject());
		return tm;
	}
}
