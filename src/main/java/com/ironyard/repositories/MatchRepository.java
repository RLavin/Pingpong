package com.ironyard.repositories;

import com.ironyard.data.Match;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Raul on 11/10/16.
 */
public interface MatchRepository extends CrudRepository<Match, Long> {
}
