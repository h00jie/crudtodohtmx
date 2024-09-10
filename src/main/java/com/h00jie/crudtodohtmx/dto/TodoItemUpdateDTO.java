package com.h00jie.crudtodohtmx.dto;

public record TodoItemUpdateDTO(Long id, String description, boolean completed) {
    // Add validation for updates if needed (e.g., id must not be null)
}
