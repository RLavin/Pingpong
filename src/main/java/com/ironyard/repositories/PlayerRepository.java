package com.ironyard.repositories;

import com.ironyard.data.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Raul on 11/10/16.
 */
public interface PlayerRepository extends CrudRepository<Player, Long>  {
}
