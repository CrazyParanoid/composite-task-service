package ru.agiletech.composite.task.service.client.teammate;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeammateServiceFallBackFactory implements FallbackFactory<TeammateClientService> {

    @Override
    public TeammateClientService create(Throwable ex) {
        log.error(ex.getMessage(), ex);

        return id -> createFailedTeammate();
    }

    private Teammate createFailedTeammate(){
         return new Teammate();
    }

}
