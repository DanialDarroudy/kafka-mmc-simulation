package ir.sharif.producer.module.message.common.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageModel {
    private UUID id = UUID.randomUUID();
    private String payload;
    private long arrivalTime;

    public  MessageModel(String payload, long arrivalTime) {
        this.payload = payload;
        this.arrivalTime = arrivalTime;
    }
}
