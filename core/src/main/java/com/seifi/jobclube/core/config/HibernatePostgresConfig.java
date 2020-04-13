package com.seifi.jobclube.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config/db-psg.properties")
@ConditionalOnProperty(value ="app.db" , havingValue = "postgres", matchIfMissing = false)
public class HibernatePostgresConfig {

    @Value("${poolName}")
    private String poolName;

    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${dbUserName}")
    private String dbUserName;

    @Value("${password}")
    private String password;

    @Value("${minIdleConnections}")
    private Integer minIdleConnections;

    @Value("${maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${cachePreparedStatements}")
    private Boolean cachePreparedStatements;

    @Value("${preparedStatementsCacheSize}")
    private Integer preparedStatementsCacheSize;

    @Value("${preparedStatementsCacheSqlLimit}")
    private Integer preparedStatementsCacheSqlLimit;

    @Value("${useServerSidePreparedStatements}")
    private Boolean useServerSidePreparedStatements;

    @Value("${className}")
    private String className;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(this.restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.seifi.jobclube.core.entitys" });
        sessionFactory.setHibernateProperties(HibernatePostgresConfig.hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {

        final HikariConfig configuration = new HikariConfig();
        configuration.setAutoCommit(false);
        configuration.setPoolName(this.poolName);
        configuration.setDriverClassName(this.className);
        configuration.setMinimumIdle(this.minIdleConnections);
        configuration.setJdbcUrl(this.jdbcUrl);
        configuration.setIdleTimeout(600000);
        configuration.setMaximumPoolSize(this.maxPoolSize);
        configuration.setUsername(this.dbUserName);
        configuration.setPassword(this.password);
        configuration.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        configuration.setMaxLifetime(30000);
        configuration.setConnectionTimeout(30000);
        configuration.setInitializationFailTimeout(600000);

        configuration.addDataSourceProperty("cachePrepStmts", this.cachePreparedStatements);
        configuration.addDataSourceProperty("prepStmtCacheSize", this.preparedStatementsCacheSize);
        configuration.addDataSourceProperty("prepStmtCacheSqlLimit", this.preparedStatementsCacheSqlLimit);
        configuration.addDataSourceProperty("useServerPrepStmts", this.useServerSidePreparedStatements);

        final HikariDataSource ds = new HikariDataSource(configuration);
        ds.validate();
        return ds;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {

        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        final PersistenceExceptionTranslationPostProcessor proc = new PersistenceExceptionTranslationPostProcessor();

        return proc;
    }

    /*
     * @Bean public EntityManager entityManager(final LocalSessionFactoryBean sessionFactory) {
     *
     * return sessionFactory.getObject().createEntityManager(); }
     */

    public static Properties hibernateProperties() {

        return new Properties() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
                this.setProperty("hibernate.hbm2ddl.auto", "none");
                this.setProperty("hibernate.show_sql", "true");
                this.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                this.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

            }
        };
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.POSTGRESQL);
        bean.setGenerateDdl(true);
        bean.setShowSql(true);
        return bean;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("com.seifi.jobclube.core");
        return bean;
    }
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}
