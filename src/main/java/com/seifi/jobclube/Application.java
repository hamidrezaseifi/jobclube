package com.seifi.jobclube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackageClasses = { Application.class }, exclude = { DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class }
)
@EnableJpaRepositories(basePackages = "com.seifi.jobclube")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
