package ir.sharif.consumer.core.db.repository;

import ir.sharif.consumer.core.db.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageModelRepository extends JpaRepository<MessageModel, String> {
}
