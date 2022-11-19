package com.example.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique=true)
    private String name;

    private String imagePath;

    @OneToMany(mappedBy = "playerTeam")
    private List<Player> teamPlayers;

    @OneToMany
    private List<Game> gameList;

    public Team() {
    }

    public Team(String teamName){
        this.name = teamName;
    }

    public Team(String teamName, String imagePath){
        this.name = teamName;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String teamName) {
        this.name = teamName;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
