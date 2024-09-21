package com.h00jie.crudtodohtmx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.h00jie.crudtodohtmx.dto.TodoItemListSortDto;
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

        newTodoItems.setName(todoItemsCreateDTO.name());
        newTodoItems.setSortBy(todoItemsCreateDTO.sortBy());
        newTodoItems.setTodoItems(java.util.Collections.emptyList());
        
        return todoItemsRepository.save(newTodoItems);
    }

    public void deleteById(Long id) {
        todoItemsRepository.deleteById(id);
    }

    public TodoItemsList findById(Long id) {
        TodoItemsList todoItemsList = todoItemsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("TodoItemsList not found"));
        todoItemsList.sortItems();
        
        return todoItemsList;
    }

    public TodoItemsList sort(TodoItemListSortDto todoItemListSortDto) {
        TodoItemsList todoItemsList = todoItemsRepository.findById(todoItemListSortDto.id()).orElseThrow(() -> new IllegalArgumentException("TodoItemsList not found"));
        
        todoItemsList.setSortBy(todoItemListSortDto.sortBy());
        todoItemsList.setSortDirectionBy(todoItemListSortDto.direction());
        todoItemsList.sortItems();
        
        todoItemsRepository.save(todoItemsList);

        return todoItemsList;
    }
}
