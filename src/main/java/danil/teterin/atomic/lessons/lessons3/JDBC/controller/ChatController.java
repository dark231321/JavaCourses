package danil.teterin.atomic.lessons.lessons3.JDBC.controller;

import danil.teterin.atomic.lessons.lessons3.JDBC.dao.MessageDao;
import danil.teterin.atomic.lessons.lessons3.JDBC.dao.UserDao;
import danil.teterin.atomic.lessons.lessons3.JDBC.model.Message;
import danil.teterin.atomic.lessons.lessons3.JDBC.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class ChatController {

    private static Logger log = LoggerFactory.getLogger(ChatController.class);

    private final UserDao userDao = new UserDao();

    private final MessageDao messageDao = new MessageDao();


    @RequestMapping(path = "/say",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity say(@RequestParam("idFrom") long    idUserTo,
                              @RequestParam("idTo")   long    idUserFrom,
                              @RequestParam("value")  String  value) {
        User userTo   = userDao.findById(idUserTo).orElse(null);
        User userFrom = userDao.findById(idUserFrom).orElse(null);

        if(userFrom != null && userTo != null) {
            Message message = new Message()
                    .setUserFrom(userFrom)
                    .setUserTo(userTo)
                    .setValue(value);
            messageDao.insert(message);
            return ResponseEntity.ok().build();
        } else {
            log.info("userFrom {} or userTo {} null", userFrom, userTo);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/online",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(userDao.getAll());
    }

    @RequestMapping(path = "/login",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void insertNewUser(@RequestParam("login") String login){
        User user = new User().setLogin(login);
        userDao.insert(user);
    }

    @RequestMapping(path = "/search",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getByName(@RequestParam("login") String login) {
        List<User> users = userDao.getAllWhere("chat.user.login= '" + login + "'");
        return ResponseEntity.ok().body(users.toString());
    }

    @RequestMapping(path = "/all",
                    method = RequestMethod.GET)
    public ResponseEntity<?> get(){
        List<Message> messages = messageDao.getAll();
        if(messages.size() != 0)
            return ResponseEntity.ok().body(
                    messages.stream()
                            .map(Message::toString)
                            .collect(Collectors.joining("\n")));
        return ResponseEntity.ok().body("Messages is empty");
    }

    @RequestMapping(path = "/iml",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getIml(@RequestParam("idFirst") long idFirst,
                                    @RequestParam("idSecond") long idSecond){

        User userFirst = userDao.findById(idFirst).orElse(null);
        User userSecond = userDao.findById(idSecond).orElse(null);
        if(userFirst == null || userSecond == null){
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages =
                messageDao.getAllWhere("\"userTo\"=" + idFirst, "\"userFrom\"=" + idSecond);
        log.info(messages.toString());
        messages.addAll(messageDao.getAllWhere("\"userTo\"=" + idSecond, "\"userFrom\"=" + idFirst));
        if(messages.size() != 0){
            return ResponseEntity.ok().body(
                    messages.stream()
                            .map(Message::toString)
                            .collect(Collectors.joining("\n"))
            );
        }
        return ResponseEntity.ok().body("no correspondence");
    }
}
