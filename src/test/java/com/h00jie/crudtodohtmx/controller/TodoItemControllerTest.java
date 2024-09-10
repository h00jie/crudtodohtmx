package com.h00jie.crudtodohtmx.controller;


import com.h00jie.crudtodohtmx.dto.TodoItemCreateDTO;
import com.h00jie.crudtodohtmx.dto.TodoItemResponseDTO;
import com.h00jie.crudtodohtmx.service.TodoItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoItemController.class)
public class TodoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoItemService todoService;

    @Test
    public void shouldReturnListOfTodos() throws Exception {
        List<TodoItemResponseDTO> sampleTodos = List.of(
                new TodoItemResponseDTO(1L, "Task 1", false),
                new TodoItemResponseDTO(2L, "Task 2", true)
        );

        when(todoService.findAll()).thenReturn(sampleTodos);
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todos"))
                .andExpect(model().attribute("todos", sampleTodos))
                .andExpect(view().name("todoList"));
    }

    @Test
    public void shouldCreateNewTodoItem() throws Exception {
        TodoItemResponseDTO responseDTO = new TodoItemResponseDTO(1L, "New Task", false);

        when(todoService.save(any(TodoItemCreateDTO.class))).thenReturn(responseDTO);
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("description", "New Task")
                        .param("completed", "false"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todos"))
                .andExpect(view().name("fragments/todoList :: todoListFragment"));
        Mockito.verify(todoService).save(any(TodoItemCreateDTO.class));
    }


    @Test
    public void shouldDeleteTodoItem() throws Exception {
        Mockito.doNothing().when(todoService).deleteById(1L);

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todos"))
                .andExpect(view().name("fragments/todoList :: todoListFragment"));

        Mockito.verify(todoService).deleteById(1L);
    }



    @Test
    public void shouldCompleteTodoItem() throws Exception {
        Mockito.doNothing().when(todoService).completeTodo(1L);

        mockMvc.perform(post("/todos/1/complete"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todos"))
                .andExpect(view().name("fragments/todoList :: todoListFragment"));

        Mockito.verify(todoService).completeTodo(1L);
    }


}
