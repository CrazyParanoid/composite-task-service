package ru.agiletech.composite.task.service.client.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractTask {

    private String      summary;
    private String      description;
    private String      projectKey;
    private String      workFlowStatus;
    private String      id;
    private LocalDate   startDate;
    private LocalDate   endDate;
    private Long        workHours;
    private Long        workDays;
    private String      priority;

    public AbstractTask(AbstractTask task) {
        this.summary        = task.summary;
        this.description    = task.description;
        this.projectKey     = task.projectKey;
        this.workFlowStatus = task.workFlowStatus;
        this.id             = task.id;
        this.startDate      = task.startDate;
        this.endDate        = task.endDate;
        this.workHours      = task.workHours;
        this.workDays       = task.workDays;
        this.priority       = task.priority;
    }

}
