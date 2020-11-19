package danil.teterin.atomic.lessons.lessons1.chat.server;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class ChatController {
    private Queue<String> messages = new ConcurrentLinkedQueue<>();
    private Map<String, String> usersOnline = new ConcurrentHashMap<>();

    /**
     * curl -X POST -i localhost:8080/chat/login -d "name=I_AM_STUPID"
     */
    @RequestMapping(
            path = "login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") String name) {
        if (name.length() < 1) {
            return ResponseEntity.badRequest().body("Too short name sorry");
        }
        if (name.length() > 20) {
            return ResponseEntity.badRequest().body("Too long name sorry");
        }
        if (usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("User already login");
        }
        usersOnline.put(name, name);
        messages.add("[" + name + "] logged in");
        return ResponseEntity.ok().build();
    }


    /**
     * curl -i localhost:8080/chat/online
     */
    @RequestMapping(
            path = "online",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity online() {
        String responseBody = usersOnline
                .keySet()
                .stream()
                .sorted()
                .collect(Collectors.joining("\n"));
        return ResponseEntity.ok().body(responseBody);
    }

    /**
     * curl -X POST -i localhost:8080/chat/logout -d "name=I_AM_STUPID"
     */
    //TODO
    @RequestMapping(
            path = "logout",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> logout(String name){
        if(!usersOnline.containsKey(name))
            return ResponseEntity.badRequest().body("This user already logout");
        usersOnline.remove(name);
        messages.add("[User logout]" + name);
        return ResponseEntity.ok().build();
    }


    /**
     * curl -X POST -i localhost:8080/chat/say -d "name=I_AM_STUPID&msg=Hello everyone in this chat"
     */
    @RequestMapping(
            path = "say",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> say(String name, String msg) {
        if(!usersOnline.containsKey(name))
            return ResponseEntity.badRequest().body("User already logout");
        String responseBody = name + ": " + msg;
        messages.add(responseBody);
        return ResponseEntity.ok(responseBody);
    }


    /**
     * curl -i localhost:8080/chat/chat
     */
    //TODO
    @RequestMapping(
            path = "chat",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity chat(){
        String responseBody = String.join("\n", messages);
        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(
            path = "clear",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity clear(){
        messages.clear();
        return ResponseEntity.ok(Integer.toString(messages.size()));
    }
}
