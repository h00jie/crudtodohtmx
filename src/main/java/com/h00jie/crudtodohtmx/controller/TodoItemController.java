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
import com.h00jie.crudtodohtmx.dto.TodoItemListSortDto;
import com.h00jie.crudtodohtmx.dto.TodoItemUpdateDTO;
import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.service.TodoItemService;
import com.h00jie.crudtodohtmx.service.TodoItemsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoService;

    private final TodoItemsService todoListService;

    @GetMapping("/{id}")
    public String getTodos(@PathVariable Long id, Model model) {
        TodoItemsList todoItemsList = todoListService.findById(id);
        model.addAttribute("todoItemsList", todoItemsList);
        
        return "todoList";
    }

    @PostMapping("/{todoItemsId}/add")
    public String addTodo(@PathVariable Long todoItemsId, @ModelAttribute TodoItemCreateDTO todoRequest, Model model) {
        todoService.addTodoToTodoItems(todoItemsId, todoRequest);
        model.addAttribute("todoItemsList", todoListService.findById(todoItemsId));

        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/{todoItemsId}/update/{id}")
    public String updateTodo(@PathVariable Long todoItemsId, @PathVariable Long id, @ModelAttribute TodoItemUpdateDTO todoRequest, Model model) {
        todoService.updateTodoItem(todoRequest);
        model.addAttribute("todoItemsList", todoListService.findById(todoItemsId));

        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/{todoItemsId}/complete/{id}")
    public String completeTodo(@PathVariable Long todoItemsId, @PathVariable Long id, Model model) {
        todoService.completeTodoItem(id);
        model.addAttribute("todoItemsList", todoListService.findById(todoItemsId));

        return "fragments/todoList :: todoListFragment";
    }

    @DeleteMapping("/{todoItemsId}/delete/{id}")
    public String deleteTodo(@PathVariable Long todoItemsId, @PathVariable Long id, Model model) {
        todoService.deleteById(id);
        model.addAttribute("todoItemsList", todoListService.findById(todoItemsId));

        return "fragments/todoList :: todoListFragment";
    }

    @PostMapping("/sort")
    public String sort(@ModelAttribute TodoItemListSortDto todoItemListSortDto, Model model) {
        
        model.addAttribute("todoItemsList", todoListService.sort(todoItemListSortDto));

        return "fragments/todoList :: todoListFragment";
    }
}
