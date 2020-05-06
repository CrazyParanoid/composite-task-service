package ru.agiletech.composite.task.service.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.agiletech.composite.task.service.client.sprint.Sprint;
import ru.agiletech.composite.task.service.client.sprint.SprintServiceClient;
import ru.agiletech.composite.task.service.client.task.TaskServiceClient;
import ru.agiletech.composite.task.service.client.task.dto.CompositeTask;
import ru.agiletech.composite.task.service.client.task.dto.Task;
import ru.agiletech.composite.task.service.client.teammate.Teammate;
import ru.agiletech.composite.task.service.client.teammate.TeammateClientService;

@Component
@RequiredArgsConstructor
public class CompositeTaskQuery implements GraphQLQueryResolver {

    private final TaskServiceClient     taskServiceClient;
    private final SprintServiceClient   sprintServiceClient;
    private final TeammateClientService teammateClientService;

    public CompositeTask taskById(String id){
        Task task = taskServiceClient.getTask(id);

        CompositeTask compositeTask = new CompositeTask(task);

        String sprintId = task.getSprintId();
        String assigneeId = task.getAssignee();

        if(StringUtils.isNotEmpty(sprintId)){
            Sprint sprint = sprintServiceClient.getSprint(task.getSprintId());

            compositeTask.setSprint(sprint);
        }

        if(StringUtils.isNotEmpty(assigneeId)){
            Teammate assignee = teammateClientService.getTeammate(task.getAssignee());

            compositeTask.setAssignee(assignee);
        }

        return compositeTask;
    }

}

