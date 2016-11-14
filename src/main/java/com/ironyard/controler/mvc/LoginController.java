package com.ironyard.controler.mvc;

import com.ironyard.data.PongUser;
import com.ironyard.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Raul on 11/14/16.
 */
@Controller
@RequestMapping(path = "login")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "username", required = false) String username,
                        HttpServletRequest request) {
        log.info("Login attempt by:"+username);
        String destination = "/login";
        PongUser found = userRepository.findByUsernameAndPassword(username, password);
        if(found != null){
            request.getSession().setAttribute("user",found);
            // on success we send them to the favs controller to fetch fav movies
            destination = "redirect:/home";
            log.info("found user:"+found.getId());
        }
        log.info("Login attempt result:"+destination);
        return destination;
    }

}
