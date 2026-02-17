package ir.sharif.consumer.module.message.process.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProcessMessageRequestDto {
    private UUID id;
    private String payload;
    private long arrivalTime;
}
