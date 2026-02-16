package ir.sharif.consumer.core.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class MessageModel {
    @Id
    private String jobId;

    private long arrivalTime;

    private long dequeueTime;

    private long finishTime;

    private long waitingTimeMs;

    private long serviceTimeMs;

    private long responseTimeMs;

    private String serverThread;
}
