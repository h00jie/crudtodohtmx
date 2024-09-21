package com.h00jie.crudtodohtmx.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.h00jie.crudtodohtmx.dto.TodoItemsCreateDTO;
import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.service.TodoItemsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoItemsController {

    private final TodoItemsService todoItemsService;

    @GetMapping("/loadTodoItems")
    public String loadTodoItems(Model model) {
        List<TodoItemsList> todoItemsList = todoItemsService.findAll();
        
        model.addAttribute("todoItemsList", todoItemsList);
        return "fragments/todoFormWithListFragment :: todoFormWithListFragment";
    }

    @PostMapping("/todoItems/add")
    public String addTodoItems(@ModelAttribute TodoItemsCreateDTO todoItemsCreateDTO, Model model) {
        todoItemsService.createTodoItems(todoItemsCreateDTO);

        model.addAttribute("todoItemsList", todoItemsService.findAll());
        return "fragments/todoItemsList :: todoItemsList";
    }

    @DeleteMapping("/todoItems/{id}/delete")
    public String deleteTodoItems(@PathVariable Long id, Model model) {
        todoItemsService.deleteById(id);
        model.addAttribute("todoItemsList", todoItemsService.findAll());
        return "fragments/todoItemsList :: todoItemsList";
    }
}
