package ru.agiletech.composite.task.service;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.composite.task.service.client.sprint.Sprint;
import ru.agiletech.composite.task.service.client.sprint.SprintServiceClient;
import ru.agiletech.composite.task.service.client.task.TaskServiceClient;
import ru.agiletech.composite.task.service.client.task.dto.Task;
import ru.agiletech.composite.task.service.client.teammate.Teammate;
import ru.agiletech.composite.task.service.client.teammate.TeammateClientService;
import ru.agiletech.composite.task.service.config.TestConfiguration;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertTrue;


@Slf4j
@GraphQLTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import(TestConfiguration.class)
@AutoConfigureWireMock(port = 0)
@ContextConfiguration(classes = Application.class)
public class TestTask {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    @MockBean
    private SprintServiceClient sprintServiceClient;

    @Autowired
    @MockBean
    private TaskServiceClient taskServiceClient;

    @Autowired
    @MockBean
    private TeammateClientService teammateClientService;

    @Before
    public void setup() {
        Mockito.when(sprintServiceClient.getSprint(ArgumentMatchers.anyString()))
                .thenReturn(createSprintResponse());
        Mockito.when(taskServiceClient.getTask(ArgumentMatchers.anyString()))
                .thenReturn(createTaskResponse());
        Mockito.when(teammateClientService.getTeammate(ArgumentMatchers.anyString()))
                .thenReturn(createAssigneeResponse());
    }

    @Test
    public void testSprintById() throws IOException {
        GraphQLResponse response = graphQLTestTemplate.perform("queries/find-task-by-id.graphql", null);
        assertTrue(response.isOk());
    }

    @NotNull
    private Sprint createSprintResponse(){
        Sprint sprint = new Sprint();

        sprint.setName("Test Name");
        sprint.setId("7cd80807-807c-498f-8cf9-fe0c2bed403c");
        sprint.setProjectKey("TST");

        return sprint;
    }

    @NotNull
    private Task createTaskResponse(){
        Task task = new Task();

        String assigneeId = UUID.randomUUID().toString();
        String taskId = UUID.randomUUID().toString();

        task.setSummary("Test Summary");
        task.setAssignee(assigneeId);
        task.setDescription("Test Description");
        task.setId(taskId);
        task.setPriority("LOW");
        task.setWorkFlowStatus("TODO");
        task.setSprintId("7cd80807-807c-498f-8cf9-fe0c2bed403c");
        task.setProjectKey("TST");

        return task;
    }

    @NotNull
    private Teammate createAssigneeResponse(){
        Teammate assignee = new Teammate();

        String id = UUID.randomUUID().toString();

        assignee.setId(id);
        assignee.setLogin("testLogin");
        assignee.setName("testName");
        assignee.setSurName("testSurName");
        assignee.setPatronymic("testPatronymic");

        return assignee;
    }

}
