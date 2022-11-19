package com.example.scoredei.web;

import com.example.data.Game;
import com.example.scoredei.service.GameService;
import com.example.scoredei.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    TeamService teamService;

     @GetMapping("/editGame")
    public String editPlayer(@RequestParam(name="id", required=true) long id, Model m) {
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            m.addAttribute("game", op.get());
            m.addAttribute("allTeams", teamService.getAllTeams());
            return "game/editGame";
        }
        else {
            return "redirect:/listGames";
        }
    }

    @GetMapping("/listGames")
    public String listGames(Model m){
        m.addAttribute("games", gameService.getAllGames());
        return "game/listGames";
    }

    @GetMapping("/createGame")
    public String createGame(Model m){
        m.addAttribute("game", new Game());
        m.addAttribute("allTeams", teamService.getAllTeams());
        return "game/editGame";
    }

    @PostMapping("/editGame")
    public String saveGame(@ModelAttribute Game game, Model m) {
         if(game.getTeamA() == game.getTeamB()){
             m.addAttribute("errorCode", 1);
             m.addAttribute("game", game);
             m.addAttribute("allTeams", teamService.getAllTeams());
             return "game/editGame";
         }
        this.gameService.addGame(game);
        return "redirect:/listGames";
    }

    @GetMapping("/gameDetails")
    public String gameDetails(@RequestParam(name="id", required=true) long id, Model m) {
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            m.addAttribute("game", op.get());
            String score = gameService.getTeamScore(op.get(), op.get().getTeamA())
                    + " VS " + gameService.getTeamScore(op.get(), op.get().getTeamB());
            m.addAttribute("score", score);
            m.addAttribute("gameEvents", this.gameService.gameEventsList(op.get()));
            return "game/gameDetails";
        }
        else {
            //TODO ERROR DETAILS
            return "redirect:/listGames";
        }
    }

}
