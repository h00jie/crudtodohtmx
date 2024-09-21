package com.h00jie.crudtodohtmx;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.h00jie.crudtodohtmx.dto.TodoItemsCreateDTO;
import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.repository.TodoItemsRepository;
import com.h00jie.crudtodohtmx.service.TodoItemsService;

@SpringBootTest
@Transactional
public class TodoItemsListIntegrationTest {

    @Autowired
    private TodoItemsService todoItemsService;

    @Autowired
    private TodoItemsRepository todoItemsRepository;

    @Test
    public void testCreateAndRetrieveTodoItemsList() {
        TodoItemsCreateDTO todoItemsCreateDTO = new TodoItemsCreateDTO("Integration Test List", "priority");

        TodoItemsList savedList = todoItemsService.createTodoItems(todoItemsCreateDTO);

        TodoItemsList fetchedTodoItemsList = todoItemsRepository.findById(savedList.getId()).orElse(null);

        assertThat(fetchedTodoItemsList).isNotNull();
        assertThat(fetchedTodoItemsList.getName()).isEqualTo(savedList.getName());
        assertThat(fetchedTodoItemsList.getSortBy()).isEqualTo("priority");
    }

}