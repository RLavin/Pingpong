package com.ironyard.controler;

import com.ironyard.data.Match;
import com.ironyard.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Raul on 11/9/16.
 */
@RestController
public class MatchControler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MatchService matchService = new MatchService();


    @RequestMapping(value = "/service/match", method = RequestMethod.POST, produces = "application/json")
    public Match save(@RequestBody Match aPlayer) throws SQLException {
        //save to db
        matchService.save(aPlayer);
        //fetch it
        long anId = aPlayer.getId();
        return matchService.getMatchById(anId);
    }
    @RequestMapping(value = "/service/match/{id}", method = RequestMethod.GET)
    public Match show(@PathVariable Long id) throws SQLException {
        return matchService.getMatchById(id);
    }

    @RequestMapping(value = "/service/match/update", method = RequestMethod.PUT)
    public Match update(@RequestBody Match aPlayer) throws SQLException {
        matchService.update(aPlayer);
        return matchService.getMatchById(aPlayer.getId());
    }
    @RequestMapping(value = "/service/match/delete/{id}", method = RequestMethod.DELETE)
    public Match delete(@PathVariable Long id)throws SQLException{
        Match deleted = matchService.getMatchById(id);
        matchService.delete(id);
        return deleted;
    }

    @RequestMapping(value = "/service/matches", method = RequestMethod.GET)
    public Iterable<Match> list() throws SQLException {

        return matchService.getAllMatches();

    }

    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Bad Went Wrong!!!";
    }
}
