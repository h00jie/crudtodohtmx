package com.h00jie.crudtodohtmx.dto;

import java.util.List;


public record TodoItemsResponseDTO(Long id, String name, String sortBy, List<TodoItemResponseDTO> todoItems) {}
