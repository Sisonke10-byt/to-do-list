package com.example.to_do_list.controller;

import com.example.to_do_list.model.Todo;
import com.example.to_do_list.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // Create a new todo
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    // Get all todos or filter by completion status
    @GetMapping
    public List<Todo> getTodos(@RequestParam(required = false) Boolean completed) {
        return service.getTodos(completed);
    }

    // Mark a todo as completed
    @PutMapping("/{id}/complete")
    public Todo completeTodo(@PathVariable Long id) {
        return service.markCompleted(id);
    }

    // Delete a todo by ID
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
        return "Todo with id " + id + " has been deleted!";
    }
}
