package com.example.scoredei.web;

import com.example.data.Team;
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
public class TeamsController {

    @Autowired
    TeamService teamService;

    @GetMapping("/listTeams")
    public String listTeams(Model model) {
        model.addAttribute("teams", this.teamService.getAllTeams());
        return "teams/listTeams";
    }

    @GetMapping("/editTeam")
    public String editTeam(@RequestParam(name="id", required=true) long id, Model m) {
        Optional<Team> op = this.teamService.getTeam(id);
        if (op.isPresent()) {
            m.addAttribute("team", op.get());
            return "teams/editTeam";
        }
        else {
            return "redirect:/listTeams";
        }
    }

    @PostMapping("/editTeam")
    public String saveTeam(@ModelAttribute Team team, Model m){
        int returnCode = this.teamService.addTeam(team);
        if(returnCode == 1){
            m.addAttribute("errorCode", returnCode);
            m.addAttribute("team", team);
            return "teams/editTeam";
        }
        return "redirect:/listTeams";
    }


    @GetMapping("/createTeam")
    public String createTeam(Model m) {
        m.addAttribute("team", new Team());
        return "teams/editTeam";
    }



    @GetMapping("/teamDetails")
    public String teamDetails(@RequestParam(name="id", required=true) long id, Model m){
        Optional<Team> op = this.teamService.getTeam(id);
        if (op.isPresent()) {
            m.addAttribute("team", op.get());
            return "teams/teamDetails";
        }
        else {
            return "redirect:/listTeams";
        }
    }
}
