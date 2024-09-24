package com.devsouzx.ecommerce_with_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.devsouzx.ecommerce_with_docker.model")
@EnableJpaRepositories(basePackages = "com.devsouzx.ecommerce_with_docker.repositories")
public class EcommerceWithDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWithDockerApplication.class, args);
	}

}
