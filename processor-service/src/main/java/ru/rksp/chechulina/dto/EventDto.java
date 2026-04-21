package ru.rksp.chechulina.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventDto {
    public UUID id;
    public String orderNumber;
    public String customerPhoneNumber;
    public String orderDescription;
    public BigDecimal orderAmount;
    public LocalDateTime eventDate;
}
