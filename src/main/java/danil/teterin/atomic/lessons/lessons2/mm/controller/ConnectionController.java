package danil.teterin.atomic.lessons.lessons2.mm.controller;

import danil.teterin.atomic.lessons.lessons2.mm.model.User;
import danil.teterin.atomic.lessons.lessons2.mm.service.ConnectionProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/connection")
public class ConnectionController {
    private static final Logger log = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private ConnectionProducer connectionProducer;

    @RequestMapping(
            path = "/connect",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> connect(@RequestParam("id") long id,
                                     @RequestParam("name") String name){
        log.info("New connection id = {} name = {}", id, name);
        connectionProducer.putPlayer(new User(id, name));
        return ResponseEntity.ok().body("New connection");
    }

    @RequestMapping(
            path = "/list",
            produces = MediaType.TEXT_PLAIN_VALUE,
            method = RequestMethod.GET
    )
    @ResponseBody
    public String list(){
        return connectionProducer.getAll().toString();
    }
}
