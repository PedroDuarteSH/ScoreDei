package com.example.scoredei.service;

import com.example.data.Team;
import com.example.scoredei.models.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;


    public List<Team> getAllTeams() {
        List<Team> userRecords = new ArrayList<>();
        teamRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    public int addTeam(Team team)
    {
        Team search = teamRepository.findByName(team.getName());

        if(search != null){
            if(search == team){
                return 1;
            }
        }

        teamRepository.save(team);
        return 0;
    }

    public Optional<Team> getTeam(Long id) {
        return teamRepository.findById(id);
    }


    public Team findByName(String name){
        return teamRepository.findByName(name);
    }
}
