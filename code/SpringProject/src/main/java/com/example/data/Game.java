package com.example.data;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;


@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Team teamA;

    @OneToOne
    private Team teamB;

    private String localization;


    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime gameTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate gameDay;

    @OneToMany(mappedBy = "eventGame")
    private List<GameEvent> eventList;

    public Game() {
    }

    public Game(Team teamA, Team teamB, String localization, LocalTime gameTime, LocalDate gameDay) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.localization = localization;
        this.gameTime = gameTime;
        this.gameDay = gameDay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public LocalTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(LocalTime gameTime) {
        this.gameTime = gameTime;
    }

    public List<GameEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<GameEvent> eventList) {
        this.eventList = eventList;
    }

    public LocalDate getGameDay() {
        return gameDay;
    }

    public void setGameDay(LocalDate gameDay) {
        this.gameDay = gameDay;
    }

    public String getGameName(){
        return teamA.getName() + " vs " + teamB.getName();
    }
}
