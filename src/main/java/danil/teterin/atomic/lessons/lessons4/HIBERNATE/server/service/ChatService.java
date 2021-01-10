package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.service;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao.MessageDaoImpl;
import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.dao.UserDaoImpl;
import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.Message;
import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChatService {
    @Autowired
    private MessageDaoImpl messageDao;

    @Autowired
    private UserDaoImpl userDao;

    @Transactional
    public void say(String sender,
                    String recipient,
                    String value){
        final List<User> loginUsers = userDao.findAll();
        Message msg = new Message();
        msg.setValue(value);
        msg.setUserTo(loginUsers.stream().map(user -> user.getLogin().equals(sender)));
    }
}
