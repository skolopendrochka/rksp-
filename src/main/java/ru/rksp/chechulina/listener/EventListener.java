package ru.rksp.chechulina.listener;

import ru.rksp.chechulina.dto.EventDto;
import ru.rksp.chechulina.entity.EventEntity;
import ru.rksp.chechulina.repo.EventRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private final EventRepository repo;

    public EventListener(EventRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = "events.raw")
    public void listen(EventDto dto) {
        EventEntity e = new EventEntity();
        e.setIdentifier(dto.id);
        e.setOrderNumber(dto.orderNumber);
        e.setCustomerPhoneNumber(dto.customerPhoneNumber);
        e.setOrderDescription(dto.orderDescription);
        e.setOrderAmount(dto.orderAmount);
        e.setEventDate(dto.eventDate);
        repo.save(e);
    }
}
