package com.h00jie.crudtodohtmx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.h00jie.crudtodohtmx.model.TodoItemsList;

public interface TodoItemsRepository extends JpaRepository<TodoItemsList, Long> {
}
