package com.test;

import com.test.repositories.DepartmentRepository;
import com.test.services.TaskDistributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;


@SpringBootApplication
@ComponentScan(basePackageClasses = {TaskDistributor.class, DepartmentRepository.class})
public class TestTaskApplication implements CommandLineRunner {
	@Autowired
	private TaskDistributor taskDistributor;

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		taskDistributor.run();
	}
}
