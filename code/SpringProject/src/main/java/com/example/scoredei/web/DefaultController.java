package com.example.scoredei.web;


import com.example.data.*;
import com.example.scoredei.service.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class DefaultController {

    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @Autowired
    UsersService usersService;

    @Autowired
    GameService gameService;

    @Autowired
    GameEventService gameEventService;



    @GetMapping("/")
    public String redirect() {
        return "index";
    }

    @GetMapping("/createData")
    public String createData() {
        return "createData";
    }

    @PostMapping("/createData")
    public String saveData(Model model) throws UnirestException {
        Users user1 = new Users("12345", "12345", "12345@12345", "12345", "12345", true);
        usersService.addUser(user1);

        Users user2 = new Users("adriana", "adriana", "adriana@gmail.com", "adriana", "adriana", true);
        usersService.addUser(user2);

        Users user3 = new Users("duarte", "duarte", "duarte@gmail.com", "duarte", "duarte", false);
        usersService.addUser(user3);

        HttpResponse<JsonNode> teamsResponse
                = Unirest.get("https://v3.football.api-sports.io/teams?league=94&season=2021")
                .header("x-apisports-key", "1308a723037ca3b6f420f4b62dc69479")
                .asJson();
        JSONArray teamList = teamsResponse.
                getBody().getObject().
                getJSONArray("response");
        for (Object element:teamList) {
            JSONObject team = (JSONObject) ((JSONObject) element).get("team");
            teamService.addTeam(new
                    Team((String) team.get("name"),
                    (String) team.get("logo")));
        }


        HttpResponse<JsonNode> playersResponse
                = Unirest.get("https://v3.football.api-sports.io/players?league=94&season=2021")
                .header("x-apisports-key", "1308a723037ca3b6f420f4b62dc69479")
                .asJson();

        JSONObject jsonObject = playersResponse.getBody().getObject();

        JSONObject paging = jsonObject.getJSONObject("paging");
        int total_pages = paging.getInt("total");

        for(int i = 2; i < total_pages; i++){
            JSONArray players = jsonObject.getJSONArray("response");
            for (Object object:players) {
                JSONObject playerObject = (JSONObject) ((JSONObject) object).get("player");
                JSONArray statsObject = (JSONArray) ((JSONObject) object).get("statistics");
                String name = (String) playerObject.get("name");
                String photo = (String) playerObject.get("photo");

                LocalDate birth = null;
                if(!((JSONObject) playerObject.get("birth")).isNull("date")){
                    String date = ((JSONObject) playerObject.get("birth")).getString("date");
                    birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                JSONObject stats = statsObject.getJSONObject(statsObject.length()-1);
                String position = stats.getJSONObject("games").getString("position");
                Team team = teamService.findByName(((JSONObject) stats.get("team")).getString("name"));
                playerService.addPlayer(new Player(name,position, birth, team, photo));
            }
            playersResponse
                    = Unirest.get("https://v3.football.api-sports.io/players?league=94&season=2021&page=" + i)
                    .header("x-apisports-key", "1308a723037ca3b6f420f4b62dc69479")
                    .asJson();
            jsonObject = playersResponse.getBody().getObject();
        }
        return "redirect:/";
    }

    @GetMapping("/stats")
    public String stats(Model m){
        List<Game> games = gameService.getAllGames();
        List<Team> teams =  teamService.getAllTeams();
        Map<Team, Integer> victories = new HashMap<Team, Integer>() ;
        Map<Team, Integer> draws = new HashMap<Team, Integer>() ;
        Map<Team, Integer> defeats = new HashMap<Team, Integer>() ;
        Long n_goals =(long) 0;
        Optional<Player> player;
        try {
            Object[] best_scorer = gameService.topScorer().get(0);
            n_goals = (Long) best_scorer[0];
            player = playerService.getPlayer((Long) best_scorer[1]);
        }catch (IndexOutOfBoundsException e){
            m.addAttribute("errorCode", 1);
            return "stats";
        }


        for (Team t : teams) {
            victories.put(t, 0);
            draws.put(t, 0);
            defeats.put(t, 0);
        }
        for (Game game: games) {
            long teamAScore = gameService.getTeamScore(game, game.getTeamA());
            long teamBScore = gameService.getTeamScore(game, game.getTeamB());
            if(teamAScore > teamBScore){
                victories.replace(game.getTeamA(), victories.get(game.getTeamA()) + 1);
                defeats.replace(game.getTeamB(), defeats.get(game.getTeamB()) + 1);
            }else if(teamAScore < teamBScore){
                victories.replace(game.getTeamB(), victories.get(game.getTeamB()) + 1);
                defeats.replace(game.getTeamA(), defeats.get(game.getTeamA()) + 1);
            }else{
                draws.replace(game.getTeamA(), draws.get(game.getTeamA()) + 1);
                draws.replace(game.getTeamB(), draws.get(game.getTeamB()) + 1);
            }
        }
        m.addAttribute("errorCode", 0);
        m.addAttribute("teams", teams);
        m.addAttribute("victories", victories);
        m.addAttribute("draws", draws);
        m.addAttribute("defeats", defeats);
        m.addAttribute("topScorer", player.get());
        m.addAttribute("topScorerGoals", n_goals);
        return "stats";
    }


}
