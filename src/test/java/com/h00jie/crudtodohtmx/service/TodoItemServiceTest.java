package com.h00jie.crudtodohtmx.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.h00jie.crudtodohtmx.dto.TodoItemCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.h00jie.crudtodohtmx.dto.TodoItemResponseDTO;
import com.h00jie.crudtodohtmx.model.TodoItem;
import com.h00jie.crudtodohtmx.repository.TodoItemRepository;


class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    @SuppressWarnings("unused")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        TodoItem item1 = new TodoItem(1L, "Proto todo", false);
        TodoItem item2 = new TodoItem(2L, "Deutero todo", true);

        when(todoItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<TodoItemResponseDTO> todos = todoItemService.findAll();

        assertEquals(2, todos.size());
        assertEquals("Proto todo", todos.get(0).description());
        assertEquals("Deutero todo", todos.get(1).description());
    }

    @Test
    void testSave() {
        TodoItemCreateDTO requestDTO = new TodoItemCreateDTO("Test 123", false);
        TodoItem savedItem = new TodoItem(1L, "Test 123", false);

        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(savedItem);

        TodoItemResponseDTO responseDTO = todoItemService.save(requestDTO);

        assertEquals("Test 123", responseDTO.description());
        assertEquals(1L, responseDTO.id());
    }

    @Test
    void testFindById() {
        TodoItem item = new TodoItem(1L, "TestTodozz", false);

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<TodoItemResponseDTO> todoOptional = todoItemService.findById(1L);

        assertTrue(todoOptional.isPresent());
        assertEquals("TestTodozz", todoOptional.get().description());
    }

    @Test
    void testDeleteById() {
        doNothing().when(todoItemRepository).deleteById(1L);

        todoItemService.deleteById(1L);

        verify(todoItemRepository, times(1)).deleteById(1L);
    }
}