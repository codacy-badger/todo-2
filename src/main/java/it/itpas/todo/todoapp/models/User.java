package it.itpas.todo.todoapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;


}
