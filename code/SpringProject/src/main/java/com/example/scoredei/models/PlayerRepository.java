package com.example.scoredei.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import com.example.data.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}    