package com.example.scoredei.service;

import com.example.data.Game;

import com.example.data.GameEvent;
import com.example.data.Player;
import com.example.data.Team;
import com.example.scoredei.models.GameEventRepository;
import com.example.scoredei.models.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameEventRepository gameEventRepository;


    public List<Game> getAllGames()
    {
        List<Game>userRecords = new ArrayList<>();
        gameRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    public void addGame(Game game)
    {
        gameRepository.save(game);
    }

    public Optional<Game> getGame(long id) {
        return gameRepository.findById(id);
    }


    public long getTeamScore(Game game, Team team){
        return gameEventRepository.countGoals(game, team);
    }

    public List<Object[]> topScorer(){
        return gameEventRepository.countList();
    }

    public List<GameEvent> gameEventsList(Game game){
        return gameEventRepository.getEventsOfGame(game);
    }




}
