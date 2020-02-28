package it.itpas.todo.todoApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class BaseEntity {

    private Integer id;
    private Date createdAt;
    private Date updatedAt;

}
