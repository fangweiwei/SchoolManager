package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiverId(Long receiverId);

    List<Message> findAllByReceiverIdAndChecked(Long receiverId, Integer checked);

    List<Message> findAllBySenderId(Long senderId);

    List<Message> findAllBySender(User sender);

    List<Message> findAllByReceiver(User receiver);

    int countByReceiver(User receiver);

    int countBySender(User sender);

    int countByReceiverAndChecked(User receiver, int checked);

}
