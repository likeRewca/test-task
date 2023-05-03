package com.test;

import com.test.services.TaskDistributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class TestTaskApplication implements CommandLineRunner {
	@Autowired
	private TaskDistributor taskDistributor;

	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		boolean runProject;

		if (args.length == 0) {
			runProject = false;
		}

		runProject = Arrays
				.stream(args)
				.filter(f -> f.equalsIgnoreCase("true"))
				.map(Boolean::parseBoolean).findFirst().orElse(false);

		taskDistributor.run(runProject);
	}
}
