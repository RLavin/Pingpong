package com.ironyard.controler;

import com.ironyard.data.Match;
import com.ironyard.repositories.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Raul on 11/9/16.
 */
@RestController
@RequestMapping(path = "/rest/match")
public class MatchControler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MatchRepository matchRepository ;




    @RequestMapping(value = "save", method = RequestMethod.POST, produces = "application/json")
    public Match save(@RequestBody Match aMatch){
        matchRepository.save(aMatch);
        return matchRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Match update(@RequestBody Match aMatch){
        matchRepository.save(aMatch);
        return matchRepository.findOne(aMatch.getId());
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Match show(@PathVariable Long id){
        return matchRepository.findOne(id);
    }



    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Match delete(@PathVariable Long id){
        Match deleted = matchRepository.findOne(id);
        matchRepository.delete(id);
        return deleted;
    }
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Match> list (@RequestParam(value ="page", required = false)Integer page,
                                 @RequestParam(value = "size", required = false)Integer size,
                                 @RequestParam(value = "sortby", required = false) String sortby,
                                 @RequestParam(value = "dir", required = false) Sort.Direction direction){

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):",page,size,sortby,direction));

        // DEFAULT Sort property
        if (sortby == null) {
            sortby = "playerOne";
        }

        // DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<Match> found =  matchRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;

    }



    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        e.printStackTrace();
        return "Something Bad Went Wrong!!!";
    }
}
