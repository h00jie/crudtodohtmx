package com.h00jie.crudtodohtmx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h00jie.crudtodohtmx.dto.TodoItemCreateDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemUpdateDTO;
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

    @PostMapping("/{todoItemsId}/add")
    public String addTodo(@PathVariable Long todoItemsId, @ModelAttribute TodoItemCreateDTO todoRequest, Model model) {
        todoService.addTodoToTodoItems(todoItemsId, todoRequest);model.addAttribute("todos", todoService.findAll());
        
        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/{id}/update")
    public String updateTodo(@PathVariable Long id, @ModelAttribute TodoItemUpdateDTO todoRequest, Model model) {
        todoService.updateTodoItem(todoRequest);
        model.addAttribute("todos", todoService.findAll());
        
        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/{id}/complete")
    public String completeTodo(@PathVariable Long id, Model model) {
        todoService.completeTodoItem(id);
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
