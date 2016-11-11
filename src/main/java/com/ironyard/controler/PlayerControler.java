package com.ironyard.controler;

import com.ironyard.data.Player;
import com.ironyard.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Raul on 11/9/16.
 */
@RestController
public class PlayerControler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerRepository playerRepository;


    @RequestMapping(value = "/service/player", method = RequestMethod.POST, produces = "application/json")
    public Player save(@RequestBody Player aMatch){
        playerRepository.save(aMatch);
        return playerRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "/service/player/update", method = RequestMethod.PUT)
    public Player update(@RequestBody Player aMatch){
        playerRepository.save(aMatch);
        return playerRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "/service/player/{id}", method = RequestMethod.GET)
    public Player show(@PathVariable Long id){
        return playerRepository.findOne(id);
    }



    @RequestMapping(value = "/service/player/delete/{id}", method = RequestMethod.DELETE)
    public Player delete(@PathVariable Long id){
        Player deleted = playerRepository.findOne(id);
        playerRepository.delete(id);
        return deleted;
    }
    @RequestMapping(value = "/service/players", method = RequestMethod.GET)
    public Iterable<Player> list (){
        return playerRepository.findAll();

    }




    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Bad Went Wrong!!!";
    }

}
