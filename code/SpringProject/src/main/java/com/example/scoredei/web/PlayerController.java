package com.example.scoredei.web;

import com.example.data.Player;

import com.example.scoredei.service.PlayerService;
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
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;


    @GetMapping("/listPlayers")
    public String listPlayers(Model model) {
        model.addAttribute("players", this.playerService.getAllPlayers());
        return "players/listPlayers";
    }

    @PostMapping("/editPlayer")
    public String savePlayer(@ModelAttribute Player player) {
        this.playerService.addPlayer(player);
        return "redirect:/listPlayers";
    }


    @GetMapping("/editPlayer")
    public String editPlayer(@RequestParam(name="id", required=true) long id, Model m) {
        Optional<Player> op = this.playerService.getPlayer(id);
        if (op.isPresent()) {
            m.addAttribute("player", op.get());
            m.addAttribute("allTeams", this.teamService.getAllTeams());
            m.addAttribute("allPositions", this.playerService.positionsList());
            return "players/editPlayer";
        }
        else {
            return "redirect:/listPlayers";
        }
    }

    @GetMapping("/createPlayer")
    public String createStudent(Model m) {
        m.addAttribute("player", new Player());
        m.addAttribute("allTeams", this.teamService.getAllTeams());
        m.addAttribute("allPositions", this.playerService.positionsList());
        return "players/editPlayer";
    }


}