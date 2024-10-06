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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "todoItems_id")
    private List<TodoItem> todoItems;

    private String sortBy;

    private String sortDirectionBy;

    private String name;

    public void sortItems() {
        Comparator<TodoItem> comparator;

        switch (sortBy) {
            case "priority" -> comparator = Comparator.comparing(TodoItem::getPriority);
            case "description" -> comparator = Comparator.comparing(TodoItem::getDescription);
            case "createdAt" -> comparator = Comparator.comparing(TodoItem::getCreatedAt);
            case "updatedAt" -> comparator = Comparator.comparing(TodoItem::getUpdatedAt);
            case "completed" -> comparator = Comparator.comparing(TodoItem::isCompleted);
            default -> comparator = Comparator.comparing(TodoItem::getDescription);
        }

        if ("desc".equalsIgnoreCase(sortDirectionBy)) {
            comparator = comparator.reversed();
        }

        todoItems.sort(comparator);
    }
}
