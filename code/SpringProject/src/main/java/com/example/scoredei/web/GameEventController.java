package com.example.scoredei.web;

import com.example.data.Game;
import com.example.data.GameEvent;

import com.example.scoredei.service.GameEventService;
import com.example.scoredei.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GameEventController {

    @Autowired
    GameEventService gameEventService;

    @Autowired
    GameService gameService;

    @GetMapping("/editGameEvent")
    public String editGameEvent(@RequestParam(name="id", required=true) long id, Model m){
        Optional<GameEvent> op = this.gameEventService.getGameEvent(id);
        if (op.isPresent()) {
            m.addAttribute("gameEvent", op.get());
            m.addAttribute("allGames", gameService.getAllGames());
            return "gameEvent/editGameEvent";
        }
        else {
            return "redirect:/listGames";
        }
    }


    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute GameEvent gameEvent, Model m){
        this.gameEventService.addGameEvent(gameEvent);
        return "redirect:/listGames";
    }


    @PostMapping("/processEvent")
    public String processEvent(@ModelAttribute GameEvent gameEvent, Model m){
        if(gameEvent.getEventType() == 2 || gameEvent.getEventType() == 3 || gameEvent.getEventType() == 4){
            return "gameEvent/addGameEventDetails";
        }
        return saveEvent(gameEvent, m);
    }

    @GetMapping("/createEvent")
    public String createEvent(@RequestParam(name="id", required=true) long id, Model m){
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            GameEvent gameEvent = new GameEvent();
            gameEvent.setEventGame(op.get());
            m.addAttribute("gameEvent", gameEvent);
            m.addAttribute("allGames", gameService.getAllGames());
        }
        return "gameEvent/editGameEvent";
    }

}
