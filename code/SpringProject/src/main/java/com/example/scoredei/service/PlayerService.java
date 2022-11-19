package com.example.scoredei.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.data.Player;

import com.example.scoredei.models.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    
public class PlayerService   
{    
    @Autowired    
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers()  
    {    
        List<Player>userRecords = new ArrayList<>();    
        playerRepository.findAll().forEach(userRecords::add);    
        return userRecords;    
    }

    public void addPlayer(Player player)
    {
        playerRepository.save(player);
    }

    public Optional<Player> getPlayer(long id) {
        return playerRepository.findById(id);
    }



    public List<String> positionsList(){
        List<String> positions = new ArrayList<>();
        positions.add("Attacker");
        positions.add("Midfielder");
        positions.add("Defender");
        positions.add("Goalkeeper");

        return positions;
    }

}    