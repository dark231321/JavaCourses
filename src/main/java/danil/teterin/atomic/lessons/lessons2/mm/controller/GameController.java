package danil.teterin.atomic.lessons.lessons2.mm.controller;

import danil.teterin.atomic.lessons.lessons2.mm.model.GameSession;
import danil.teterin.atomic.lessons.lessons2.mm.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository repository;

    @RequestMapping(path = "/all",
                    produces = MediaType.TEXT_PLAIN_VALUE,
                    method = RequestMethod.GET)
    @ResponseBody
    public String getAllGames(){ return this.repository.getAll().toString(); }

    @RequestMapping(path = "/getByid",
                    method = RequestMethod.POST)
    @ResponseBody
    public String getById(@RequestParam("id") long id) {
        GameSession gameSession;
        if ((gameSession = this.repository.getById(id)) == null)
            return "Game Isn't found";
        return gameSession.toString();
    }

}
