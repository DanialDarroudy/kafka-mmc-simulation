package ir.sharif.consumer.module.metric.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricServiceDto {
    String serverId;
    long waitingTime;
    long responseTime;
    long serviceTime;
    long busyDuration;
}
