package com.example.demo;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories(basePackages = "com.example.demo")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner insert(DepartmentRepository repository) {
		return (args) -> {
//			repository.save(new DepartmentEntity("Economic"));
//			repository.save(new DepartmentEntity("Marketing"));
//			repository.save(new DepartmentEntity("Production"));
//			repository.deleteAll();
		};
	}
}
