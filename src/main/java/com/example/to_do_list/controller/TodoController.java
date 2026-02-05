package com.example.to_do_list.controller;

import com.example.to_do_list.model.Todo;
import com.example.to_do_list.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // Root endpoint - display todos in HTML
    @GetMapping("/")
    public String index(Model model) {
        List<Todo> todos = service.getTodos(null);
        model.addAttribute("todos", todos);
        model.addAttribute("newTodo", new Todo());
        return "index";
    }

    // Add a new todo
    @PostMapping("/")
    public String addTodo(@ModelAttribute Todo todo, RedirectAttributes redirectAttributes) {
        service.createTodo(todo);
        redirectAttributes.addFlashAttribute("message", "Todo added successfully!");
        return "redirect:/";
    }

    // Mark todo as complete
    @PostMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.markCompleted(id);
        redirectAttributes.addFlashAttribute("message", "Todo marked as complete!");
        return "redirect:/";
    }

    // Delete a todo
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteTodo(id);
        redirectAttributes.addFlashAttribute("message", "Todo deleted!");
        return "redirect:/";
    }

    // REST API endpoints
    @RestController
    @RequestMapping("/api/todos")
    public class TodoRestController {

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
}
