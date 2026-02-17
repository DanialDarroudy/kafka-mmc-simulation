package ir.sharif.consumer.module.metric.service.abstraction;

import ir.sharif.consumer.module.metric.service.dto.MetricServiceDto;

public interface IMetricService {
    void record(MetricServiceDto dto);
}
