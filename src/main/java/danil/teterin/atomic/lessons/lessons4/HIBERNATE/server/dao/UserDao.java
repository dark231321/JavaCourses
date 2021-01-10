package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.User;

import java.util.List;

public interface UserDao {

    User getByLogin(String login);

    void save(User user);

    List<User> findAll();
}
