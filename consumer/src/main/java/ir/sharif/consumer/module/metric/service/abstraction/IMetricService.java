package ir.sharif.consumer.module.metric.service.abstraction;

import ir.sharif.consumer.module.metric.service.dto.MetricServiceRecord;

public interface IMetricService {
    void record(MetricServiceRecord record);
}
