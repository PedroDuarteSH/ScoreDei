package com.example.data;


import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance
public class GameEvent {
    @Id
    @GeneratedValue
    private Long id;


    private String eventDescription;

    @NonNull
    private int eventTime;

    @ManyToOne
    private Game eventGame;

    @OneToOne(optional = true)
    private Player player;


    @OneToOne(optional = true)
    private Team team;


    public static final List<String> eventTypes = List.of("Start", "End", "Goal", "Yellow Card", "Red Card", "Stopped Game", "Resumed Game");
    public static final List<String> eventIcons = List.of(
            "https://cdn-icons-png.flaticon.com/512/5199/5199939.png",
            "https://cdn-icons-png.flaticon.com/512/1865/1865377.png",
            "https://cdn-icons-png.flaticon.com/512/33/33736.png",
            "https://cdn-icons-png.flaticon.com/512/4768/4768720.png",
            "https://cdn-icons-png.flaticon.com/512/4768/4768714.png",
            "https://cdn-icons-png.flaticon.com/512/3100/3100252.png",
            "https://cdn-icons-png.flaticon.com/512/0/375.png"
            );


    /**
     * Event Types
     * 0 - Start
     * 1 - End
     * 2 - Goal
     * 3 - Yellow Card
     * 4 - Red Card
     * 5 - Game Stopped
     * 6 - Game Resumed
     */
    @NonNull
    private int eventType;


    public GameEvent(int eventType, String eventDescription, int eventTime, Game event_game) {
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventTime = eventTime;

        this.eventGame = event_game;
    }

    public GameEvent(int eventType, String eventDescription, int eventTime, Game eventGame, Player player) throws Exception {
        this(eventType, eventDescription, eventTime, eventGame);
        this.player = player;
        this.team = player.getPlayerTeam();
        if(this.team != this.getEventGame().getTeamA() && this.team != this.getEventGame().getTeamB()){
            throw new Exception();
        }
    }


    public GameEvent() {

    }




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getEventTime() {
        return eventTime;
    }

    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }

    public Game getEventGame() {
        return eventGame;
    }

    public void setEventGame(Game eventGame) {
        this.eventGame = eventGame;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
