package danil.teterin.atomic.lessons.lessons3.JDBC.controller;


import danil.teterin.atomic.lessons.lessons3.JDBC.dao.MessageDao;
import danil.teterin.atomic.lessons.lessons3.JDBC.dao.UserDao;
import danil.teterin.atomic.lessons.lessons3.JDBC.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("chat")
public class ChatController {

    private final UserDao userDao = new UserDao();

    private final MessageDao messageDao = new MessageDao();

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
}
