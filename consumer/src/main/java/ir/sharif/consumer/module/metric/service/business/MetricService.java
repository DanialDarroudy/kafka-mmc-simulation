package ir.sharif.consumer.module.metric.service.business;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import ir.sharif.consumer.module.metric.service.abstraction.IMetricService;
import ir.sharif.consumer.module.metric.service.dto.MetricServiceDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class MetricService implements IMetricService {
    private final MeterRegistry registry;

    private final Counter completedMessages;
    private final DistributionSummary waitingTimeSummary;
    private final DistributionSummary responseTimeSummary;
    private final DistributionSummary serviceTimeSummary;

    private final ConcurrentMap<String, Counter> serverBusyTimeCounters;

    public MetricService(MeterRegistry registry) {
        this.registry = registry;
        this.serverBusyTimeCounters = new ConcurrentHashMap<>();

        this.completedMessages = Counter.builder("messages_completed_total")
                .description("Total number of completed messages")
                .register(registry);

        this.waitingTimeSummary = DistributionSummary.builder("messages_waiting_time_ms")
                .description("Message waiting time in queue (milliseconds)")
                .baseUnit("milliseconds")
                .register(registry);

        this.responseTimeSummary = DistributionSummary.builder("messages_response_time_ms")
                .description("Message total response time (milliseconds)")
                .baseUnit("milliseconds")
                .register(registry);

        this.serviceTimeSummary = DistributionSummary.builder("messages_service_time_ms")
                .description("Message service time (milliseconds)")
                .baseUnit("milliseconds")
                .register(registry);
    }

    @Override
    public void record(MetricServiceDto dto) {
        completedMessages.increment();

        waitingTimeSummary.record(dto.getWaitingTime());
        responseTimeSummary.record(dto.getResponseTime());
        serviceTimeSummary.record(dto.getServiceTime());

        serverBusyTimeCounters.computeIfAbsent(dto.getServerId(), id ->
                Counter.builder("server_busy_time_total_ms")
                        .description("Total busy time of server in milliseconds")
                        .tag("server", id)
                        .baseUnit("milliseconds")
                        .register(registry)).increment(dto.getBusyDuration());
    }
}
