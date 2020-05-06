package ru.agiletech.composite.task.service.client.sprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

    private String      id;
    private String      name;
    private String      projectKey;

}
