package com.h00jie.crudtodohtmx.dto;

import com.h00jie.crudtodohtmx.model.TodoItem.Priority;

public record TodoItemCreateDTO(String description, Priority priority, boolean completed) {
}
