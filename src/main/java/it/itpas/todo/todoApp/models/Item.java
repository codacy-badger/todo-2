package it.itpas.todo.todoApp.models;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Item extends BaseEntity {

    private String name;
    private String description;
    private List<User> assignedTo;
    private Tag tag;
    private Date completedAt;
    private String note;
    private Boolean completed;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
