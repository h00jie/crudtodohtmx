package com.h00jie.crudtodohtmx.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h00jie.crudtodohtmx.dto.TodoItemRequestDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemResponseDTO;
import com.h00jie.crudtodohtmx.service.TodoItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoService;

    @GetMapping
    public String getTodos(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "todoList";
    }

    @PostMapping
    public String addTodo(@ModelAttribute TodoItemRequestDTO todoRequest, Model model) {
        todoService.save(todoRequest);
        model.addAttribute("todos", todoService.findAll());
        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/{id}/complete")
    public String completeTodo(@PathVariable Long id, Model model) {
        Optional<TodoItemResponseDTO> todoOptional = todoService.findById(id);
        todoOptional.ifPresent(todo -> {
            TodoItemRequestDTO updatedTodo = new TodoItemRequestDTO(todo.description(), true);
            todoService.save(updatedTodo);
        });

        model.addAttribute("todos", todoService.findAll());
        return "fragments/todoList :: todoListFragment";
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id, Model model) {
        todoService.deleteById(id);
        model.addAttribute("todos", todoService.findAll());
        return "fragments/todoList :: todoListFragment";
    }
}
