package com.h00jie.crudtodohtmx.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.h00jie.crudtodohtmx.dto.TodoItemCreateDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemResponseDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemUpdateDTO;
import com.h00jie.crudtodohtmx.model.TodoItem;
import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.repository.TodoItemRepository;
import com.h00jie.crudtodohtmx.repository.TodoItemsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final TodoItemsRepository todoItemsRepository;

    public List<TodoItemResponseDTO> findAll() {
        log.info("Fetching all todo items");
        return todoItemRepository.findAll().stream()
                .map(todo -> new TodoItemResponseDTO(todo.getId(), todo.getDescription(),
                        todo.isCompleted(), todo.getPriority(), todo.getCreatedAt(), todo.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public void addTodoToTodoItems(Long todoItemsId, TodoItemCreateDTO todoRequest) {
        log.info("Adding todo item to TodoItemsList id: {}", todoItemsId);
        TodoItemsList todoItems = todoItemsRepository.findById(todoItemsId)
                .orElseThrow(() -> new IllegalArgumentException("TodoItems not found"));

        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setDescription(todoRequest.description());
        newTodoItem.setPriority(todoRequest.priority());
        newTodoItem.setCompleted(todoRequest.completed());

        todoItems.getTodoItems().add(newTodoItem);
        todoItemsRepository.save(todoItems); 
    }

    public void updateTodoItem(TodoItemUpdateDTO todoRequest) {
        TodoItem todoItem = todoItemRepository.findById(todoRequest.id())
                .orElseThrow(() -> new IllegalArgumentException("TodoItem not found"));

        todoItem.setDescription(todoRequest.description());
        todoItem.setPriority(todoRequest.priority());
        todoItem.setCompleted(todoRequest.completed());

        todoItemRepository.save(todoItem); 
    }

    public void completeTodoItem(Long id) {
        log.info("completing todo item with id: {}", id);
        TodoItem todoItem = todoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem not found"));
        todoItem.setCompleted(true);
        todoItemRepository.save(todoItem); 
    }

    public void deleteById(Long id) {
        log.info("Deleting todo item with id: {}", id);
        todoItemRepository.deleteById(id);
    }

    public Optional<TodoItemResponseDTO> findById(Long id) {
        log.info("Finding todo item by id: {}", id);
        return todoItemRepository.findById(id)
                .map(todo -> new TodoItemResponseDTO(todo.getId(), todo.getDescription(),
                        todo.isCompleted(), todo.getPriority(), todo.getCreatedAt(), todo.getUpdatedAt()));
    }
}
