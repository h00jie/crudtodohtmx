package com.h00jie.crudtodohtmx.dto;

import com.h00jie.crudtodohtmx.model.TodoItem.Priority;

public record TodoItemUpdateDTO(Long id, String description, Priority priority, boolean completed) {
}
