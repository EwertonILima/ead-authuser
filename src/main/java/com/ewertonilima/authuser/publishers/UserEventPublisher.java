package com.ewertonilima.authuser.publishers;

import com.ewertonilima.authuser.dtos.UserEventDto;
import com.ewertonilima.authuser.enums.ActionType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${ead.broker.exchange.userEvent}")
    private String exchangeUserEvent;

    public void publisherUserEvent(UserEventDto userEventDto, ActionType actionType) {
        userEventDto.setActionType(actionType.toString());
        rabbitTemplate.convertAndSend(exchangeUserEvent, "", userEventDto);
    }
}
