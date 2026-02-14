package ir.sharif.consumer.module.metric.service.dto;

public record MetricServiceRecord(String serverId, long waitingTime, long responseTime, long serviceTime, long busyDuration){

}
