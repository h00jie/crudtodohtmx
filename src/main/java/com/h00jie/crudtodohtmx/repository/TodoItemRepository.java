package com.h00jie.crudtodohtmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h00jie.crudtodohtmx.model.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}
