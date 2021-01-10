package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public void save(User user) {
        log.warn(user.getLogin());
        em.persist(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("Select t from " + User.class.getSimpleName() + " t").getResultList();
    }
}
