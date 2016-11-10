package com.ironyard.controler;

import com.ironyard.data.Player;
import com.ironyard.services.PlayerService;
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
    private PlayerService playerService = new PlayerService();


    @RequestMapping(value = "/service/player", method = RequestMethod.POST, produces = "application/json")
    public Player save(@RequestBody Player aPlayer) throws SQLException {
        //save to db
        playerService.save(aPlayer);
        //fetch it
        long anId = aPlayer.getId();
        return playerService.getPlayerById(anId);
    }
    @RequestMapping(value = "/service/player/{id}", method = RequestMethod.GET)
    public Player show(@PathVariable Long id) throws SQLException {
        return playerService.getPlayerById(id);
    }

    @RequestMapping(value = "/service/player/update", method = RequestMethod.PUT)
    public Player update(@RequestBody Player aPlayer) throws SQLException {
        playerService.update(aPlayer);
        return playerService.getPlayerById(aPlayer.getId());
    }
    @RequestMapping(value = "/service/player/delete/{id}", method = RequestMethod.DELETE)
    public Player delete(@PathVariable Long id)throws SQLException{
        Player deleted = playerService.getPlayerById(id);
        playerService.delete(id);
        return deleted;
    }
    @RequestMapping(value = "/service/players", method = RequestMethod.GET)
    public Iterable<Player> list() throws SQLException {

        return playerService.getAllPlayers();

    }

    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Bad Went Wrong!!!";
    }

}
