package com.example.scoredei.service;


import com.example.data.GameEvent;
import com.example.scoredei.models.GameEventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GameEventService {

    @Autowired
    GameEventRepository gameEventRepository;


    public void addGameEvent(GameEvent gameEvent)
    {
        if(gameEvent.getPlayer() != null){
            gameEvent.setTeam(gameEvent.getPlayer().getPlayerTeam());
        }

        gameEventRepository.save(gameEvent);
    }

    public Optional<GameEvent> getGameEvent(long id){
        return gameEventRepository.findById(id);
    }





}
