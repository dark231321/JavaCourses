package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class MessageDaoImpl implements MessageDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public Message getByLoginSender() {
        return null;
    }

    @Override
    public Message getByLoginRecepient() {
        return null;
    }

    @Override
    public void save(Message msg) {
        em.persist(msg);
    }
}
