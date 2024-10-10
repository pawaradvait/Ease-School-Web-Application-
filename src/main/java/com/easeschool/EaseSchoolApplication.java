package com.easeschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
public class EaseSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaseSchoolApplication.class, args);
	}

}
