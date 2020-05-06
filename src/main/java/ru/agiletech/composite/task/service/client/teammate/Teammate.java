package ru.agiletech.composite.task.service.client.teammate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teammate {

    private String    id;
    private String    login;
    private String    name;
    private String    surName;
    private String    patronymic;

}
