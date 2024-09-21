package com.h00jie.crudtodohtmx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.h00jie.crudtodohtmx.dto.TodoItemsCreateDTO;
import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.repository.TodoItemsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoItemsService {

    private final TodoItemsRepository todoItemsRepository;

    public List<TodoItemsList> findAll() {
        return todoItemsRepository.findAll();
    }

    public TodoItemsList createTodoItems(TodoItemsCreateDTO todoItemsCreateDTO) {
        TodoItemsList newTodoItems = new TodoItemsList();
        newTodoItems.setSortBy(todoItemsCreateDTO.getSortBy());
        return todoItemsRepository.save(newTodoItems);
    }

    public void deleteById(Long id) {
        todoItemsRepository.deleteById(id);
    }
}
