package com.ironyard.controler;

import com.ironyard.data.Match;
import com.ironyard.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Raul on 11/9/16.
 */
@RestController
public class MatchControler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MatchRepository matchRepository ;




    @RequestMapping(value = "/service/match", method = RequestMethod.POST, produces = "application/json")
    public Match save(@RequestBody Match aMatch){
        matchRepository.save(aMatch);
        return matchRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "/service/match/update", method = RequestMethod.PUT)
    public Match update(@RequestBody Match aMatch){
        matchRepository.save(aMatch);
        return matchRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "/service/match/{id}", method = RequestMethod.GET)
    public Match show(@PathVariable Long id){
        return matchRepository.findOne(id);
    }



    @RequestMapping(value = "/service/match/delete/{id}", method = RequestMethod.DELETE)
    public Match delete(@PathVariable Long id){
        Match deleted = matchRepository.findOne(id);
        matchRepository.delete(id);
        return deleted;
    }
    @RequestMapping(value = "/service/matches", method = RequestMethod.GET)
    public Iterable<Match> list (){
        return matchRepository.findAll();

    }


    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Bad Went Wrong!!!";
    }
}
