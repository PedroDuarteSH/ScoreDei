package com.example.scoredei;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.example.*")
@EntityScan("com.example.*")
@SpringBootApplication
public class ScoreDeiApplication {
	public static void main(String[] args){
		SpringApplication.run(ScoreDeiApplication.class, args);
	}
}
