package danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.controller;

import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.service.ChatService;
import danil.teterin.atomic.lessons.lessons4.HIBERNATE.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ChatService chat;

    @RequestMapping(path = "/login",
                    method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam("login") String login) {
        if(login.length() > 1 && login.length() < 20)
            //if(loginService.getByLogin(login) != null)
            {
                loginService.login(login);
                return ResponseEntity.ok().build();
            }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path = "/users",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(loginService.findAll().toString());
    }

    @RequestMapping(path = "/impl",
                    method = RequestMethod.POST)
    public ResponseEntity<?> say(@RequestParam("sender")    String sender,
                                 @RequestParam("recipient") String recipient,
                                 @RequestParam("value")     String msg){
        chat.say(sender, recipient, msg);
    }
}
