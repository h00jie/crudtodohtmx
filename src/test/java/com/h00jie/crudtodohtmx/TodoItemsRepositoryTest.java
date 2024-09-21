package com.h00jie.crudtodohtmx;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.h00jie.crudtodohtmx.model.TodoItemsList;
import com.h00jie.crudtodohtmx.repository.TodoItemsRepository;

@DataJpaTest
@Rollback
public class TodoItemsRepositoryTest {

    @Autowired
    private TodoItemsRepository todoItemsRepository;

    @Test
    public void testSaveAndFindTodoItemsList() {
        TodoItemsList todoList = new TodoItemsList();
        todoList.setName("Repository Test List");
        todoList.setSortBy("priority");
        todoList.setSortDirectionBy("desc");
        TodoItemsList savedList = todoItemsRepository.save(todoList);

        Optional<TodoItemsList> fetchedList = todoItemsRepository.findById(savedList.getId());
    
        assertThat(fetchedList).isPresent();
        assertThat(fetchedList.get().getName()).isEqualTo(savedList.getName());
    }

    @Test
    public void testDeleteTodoItemsList() {    
        TodoItemsList todoList = new TodoItemsList();
        todoList.setName("Repository Test List");
        todoList.setSortBy("priority");
        todoList.setSortDirectionBy("desc");
        TodoItemsList savedList = todoItemsRepository.save(todoList);

        todoItemsRepository.delete(savedList);
    
        Optional<TodoItemsList> fetchedList = todoItemsRepository.findById(savedList.getId());
        assertThat(fetchedList).isEmpty();
    }
}