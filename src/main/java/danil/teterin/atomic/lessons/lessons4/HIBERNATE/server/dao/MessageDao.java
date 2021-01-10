package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getAll();

    Message getByLoginSender();

    Message getByLoginRecepient();

    void save(Message msg);
}
