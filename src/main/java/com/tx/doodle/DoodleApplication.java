package com.tx.doodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EntityScan("com.tx.doodle.model")
@EnableMongoRepositories(basePackages = "com.tx.doodle.repository")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoodleApplication.class, args);
	}

}
