package com.example.to_do_list.service;

import com.example.to_do_list.model.Todo;
import com.example.to_do_list.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private static final Logger log = LoggerFactory.getLogger(TodoService.class);
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // Create a new todo
    public Todo createTodo(Todo todo) {
        log.info("Creating todo: {}", todo.getTitle());
        return repository.save(todo);
    }

    // Get all todos or filter by completion status
    public List<Todo> getTodos(Boolean completed) {
        log.debug("Fetching todos, completed={}", completed);
        return completed == null
                ? repository.findAll()
                : repository.findByCompleted(completed);
    }

    // Mark a todo as completed
    public Todo markCompleted(@NonNull Long id) {
        log.warn("Marking todo {} as completed", id);
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(true);
        return repository.save(todo);
    }

    // Delete a todo by ID
    public void deleteTodo(@NonNull Long id) {
        log.error("Deleting todo with id {}", id);
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
        repository.delete(todo);
    }
}
