package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.service;


import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao.UserDaoImpl;
import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoginService {

    private Logger log = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserDaoImpl userDao;

    @Transactional
    public void login(String login) {
        User user = new User(); user.setLogin(login);
        if (user.getLogin() != null)
            this.userDao.save(user);
        else
            log.info("Login null {}", user.getLogin());
    }

    @Transactional
    public List<User> findAll(){ return this.userDao.findAll(); }

    @Transactional
    public User getByLogin(String login) {
        return this.userDao.getByLogin(login);
    }
}
