package com.example.demo;

import com.example.demo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private DatabaseService databaseService;
	public static void main(String[] args) {
		System.out.printf("Hello, Spring Boot!");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseService.checkDatabaseConnection();

		// Example query execution
		String sql = "SELECT * FROM L_BRANCH";
		List<Map<String, Object>> result = databaseService.executeQuery(sql);

		// Process the query result as needed
		if (result != null) {
			for (Map<String, Object> row : result) {
				System.out.println(row);
			}
		}
	}

}
