package com.h00jie.crudtodohtmx.dto;

import java.time.LocalDateTime;

import com.h00jie.crudtodohtmx.model.TodoItem.Priority;

public record TodoItemResponseDTO(Long id, String description, boolean completed, 
                                  Priority priority, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
