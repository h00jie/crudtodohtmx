package com.h00jie.crudtodohtmx.dto;

public record TodoItemCreateDTO(String description, boolean completed) {
    // Add validation here if needed (e.g., description must not be blank)
}
