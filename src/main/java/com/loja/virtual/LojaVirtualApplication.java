package com.loja.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/*import org.springframework.boot.autoconfigure.domain.EntityScan;*/

@SpringBootApplication
/*@EntityScan(basePackages = "loja-virtual")*/
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.loja.virtual.repository"})
@EnableTransactionManagement
public class LojaVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualApplication.class, args);
	}

}
