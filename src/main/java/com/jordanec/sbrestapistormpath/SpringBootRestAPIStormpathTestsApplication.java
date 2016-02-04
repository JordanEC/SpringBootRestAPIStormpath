package com.jordanec.sbrestapistormpath;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "com.jordanec.sbrestapistormpath.repository")
@EntityScan(basePackages = "com.jordanec.sbrestapistormpath.model")
@EnableTransactionManagement
@ComponentScan
@SpringBootApplication
public class SpringBootRestAPIStormpathTestsApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestAPIStormpathTestsApplication.class, args);
	}

}
