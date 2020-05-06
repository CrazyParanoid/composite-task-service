package ru.agiletech.composite.task.service.client.teammate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.agiletech.composite.task.service.config.FeignClientConfig;

@FeignClient(value = "teammate-service", configuration = FeignClientConfig.class,
        fallbackFactory = TeammateServiceFallBackFactory.class)
public interface TeammateClientService {

    @GetMapping(value = "/api/teammates/{id}")
    @ResponseStatus(HttpStatus.OK)
    Teammate getTeammate(@PathVariable String id);

}
