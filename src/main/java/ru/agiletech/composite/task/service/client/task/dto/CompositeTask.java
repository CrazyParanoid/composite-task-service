package ru.agiletech.composite.task.service.client.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.agiletech.composite.task.service.client.sprint.Sprint;
import ru.agiletech.composite.task.service.client.teammate.Teammate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompositeTask extends AbstractTask{

    private Sprint      sprint;
    private Teammate    assignee;

    public CompositeTask(AbstractTask   task){
        super(task);;
    }

}
