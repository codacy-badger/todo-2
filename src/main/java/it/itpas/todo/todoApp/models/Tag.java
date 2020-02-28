package it.itpas.todo.todoApp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tag extends BaseEntity {

    private String name;
    private String color;
    private String icon;
    private String description;
    private List<ItemGroup> itemGroups;

}
