package com.example.scoredei.models;

import com.example.data.Game;
import com.example.data.GameEvent;

import com.example.data.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameEventRepository extends CrudRepository<GameEvent, Long> {

    @Query("select count(e) from GameEvent e where e.eventType=2 and e.eventGame=?1 and e.team= ?2")
    public long countGoals(Game game, Team team);

    @Query("select count(e), e.player.id from GameEvent e where e.eventType=2 group by e.player.id order by count(e) DESC")
    public List<Object[]> countList();

    @Query("select e from GameEvent e where e.eventGame=?1 order by e.eventTime DESC ")
    public List<GameEvent> getEventsOfGame(Game game);
}
