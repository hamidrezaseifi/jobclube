package com.seifi.jobclube.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackageClasses = { CoreApplication.class }, exclude = { DataSourceAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class }
)
@EnableJpaRepositories(basePackages = "com.seifi.jobclube.core")
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
