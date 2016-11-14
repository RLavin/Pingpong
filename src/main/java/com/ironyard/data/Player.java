package com.ironyard.data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Raul on 11/9/16.
 */
@Entity
public class Player {
    private String name;
    private String nickname;
    private int wins;
    private int losses;

  /**  @OneToMany(fetch = FetchType.EAGER)
    private Set<Match> matches;

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> favs) {
        this.matches = matches;
    }**/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    public Player(String raul, String s, int i, int i1) {

    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
