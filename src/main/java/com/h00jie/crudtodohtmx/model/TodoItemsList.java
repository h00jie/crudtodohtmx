package com.h00jie.crudtodohtmx.model;

import java.util.Comparator;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodoItemsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "todoItems_id")
    public List<TodoItem> todoItems;

    public String sortBy;

    
    public void sortItems(String sortBy, String direction) {
        Comparator<TodoItem> comparator;

        switch (sortBy) {
            case "priority" -> comparator = Comparator.comparing(TodoItem::getPriority);
            case "createdAt" -> comparator = Comparator.comparing(TodoItem::getCreatedAt);
            case "updatedAt" -> comparator = Comparator.comparing(TodoItem::getUpdatedAt);
            default -> {
                return;
            }
        }

        if ("desc".equalsIgnoreCase(direction)) {
            comparator = comparator.reversed();
        }

        todoItems.sort(comparator);
    }
}
