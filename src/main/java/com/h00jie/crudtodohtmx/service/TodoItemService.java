package com.h00jie.crudtodohtmx.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.h00jie.crudtodohtmx.dto.TodoItemRequestDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemResponseDTO;
import com.h00jie.crudtodohtmx.model.TodoItem;
import com.h00jie.crudtodohtmx.repository.TodoItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor 
@Slf4j 
public class TodoItemService {

    private final TodoItemRepository repository;


    public List<TodoItemResponseDTO> findAll() {
        log.info("Fetching all todo items");
        return repository.findAll().stream()
                .map(todo -> new TodoItemResponseDTO(todo.getId(), todo.getDescription(), todo.isCompleted()))
                .collect(Collectors.toList());
    }


    public TodoItemResponseDTO save(TodoItemRequestDTO requestDTO) {
        log.info("Saving new todo item: {}", requestDTO.description());

        TodoItem todo = new TodoItem();
        todo.setDescription(requestDTO.description());
        todo.setCompleted(requestDTO.completed());

        TodoItem savedTodo = repository.save(todo);
        return new TodoItemResponseDTO(savedTodo.getId(), savedTodo.getDescription(), savedTodo.isCompleted());
    }


    public void deleteById(Long id) {
        log.info("Deleting todo item with id: {}", id);
        repository.deleteById(id);
    }


    public Optional<TodoItemResponseDTO> findById(Long id) {
        log.info("Finding todo item by id: {}", id);
        return repository.findById(id)
                .map(todo -> new TodoItemResponseDTO(todo.getId(), todo.getDescription(), todo.isCompleted()));
    }
}
