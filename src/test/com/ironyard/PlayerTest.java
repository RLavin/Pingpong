package com.ironyard;

import com.ironyard.data.Player;
import com.ironyard.repositories.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Raul on 11/11/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PlayerTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testCreatePlayer() throws Exception{

        // create new player
        Player player =  playerRepository.save(new Player("Raul","The destroyer",10,5));

        // player should there
        Assert.assertNotNull(playerRepository.findOne(player.getId()));

    }

    @Test
    public void testDeletePlayer() throws Exception{

        // create new player
        Player player =  playerRepository.save(new Player("Raul","The destroyer",10,5));
        playerRepository.delete(player);
        // player should not be there
        Assert.assertNull(playerRepository.findOne(player.getId()));

    }


}
