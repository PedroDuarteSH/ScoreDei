package com.example.scoredei.models;

import com.example.data.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    public Team findByName(String name);

}
