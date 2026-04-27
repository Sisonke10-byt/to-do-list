package com.example.to_do_list;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.to_do_list.model.Todo;
import com.example.to_do_list.repository.TodoRepository;

import java.time.LocalDate;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(TodoRepository todoRepository) {
		return args -> {
			// Only add sample data if the database is empty
			if (todoRepository.count() == 0) {
				todoRepository.save(new Todo("Complete project documentation", "Write comprehensive documentation for the to-do list project", false, LocalDate.of(2026, 5, 1)));
				todoRepository.save(new Todo("Review code changes", "Review the latest pull request for the authentication module", false, LocalDate.of(2026, 4, 25)));
				todoRepository.save(new Todo("Update dependencies", "Update all npm packages to their latest versions", true, LocalDate.of(2026, 4, 20)));
				todoRepository.save(new Todo("Setup CI/CD pipeline", "Configure GitHub Actions for automated testing and deployment", false, LocalDate.of(2026, 4, 30)));
				todoRepository.save(new Todo("Database optimization", "Optimize database queries for better performance", false, LocalDate.of(2026, 5, 15)));
				todoRepository.save(new Todo("User interface improvements", "Enhance the UI/UX based on user feedback", true, LocalDate.of(2026, 4, 18)));
				todoRepository.save(new Todo("Security audit", "Perform security audit on the application", false, LocalDate.of(2026, 5, 10)));
				todoRepository.save(new Todo("API documentation", "Create OpenAPI/Swagger documentation for REST endpoints", false, LocalDate.of(2026, 5, 5)));
			}
		};
	}

}
