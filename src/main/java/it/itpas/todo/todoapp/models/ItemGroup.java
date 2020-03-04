package it.itpas.todo.todoapp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ItemGroup extends BaseEntity {

    private String name;
    private String description;
    private User owner;
    private List<User> relatedUser;
    private Date deadline;
    private List<Item> items;
    private List<Tag> tags;


}
