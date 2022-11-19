package com.example.data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String imagePath;

    @ManyToOne
    private Team playerTeam;

    public Player() {
    }

    public Player(String name, String position, LocalDate birth, Team playerTeam, String photo) {
        this.name = name;
        this.position = position;
        this.birth = birth;
        this.playerTeam = playerTeam;
        this.imagePath = photo;
    }

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public String toString() {
        return this.name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String playerPath) {
        this.imagePath = playerPath;
    }

}
