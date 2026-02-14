package ir.sharif.consumer.module.message.store.abstraction;

import ir.sharif.consumer.core.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageModelRepository extends JpaRepository<MessageModel, String> {
}
