package com.ironyard.services;

import com.ironyard.data.Player;
import org.springframework.context.annotation.Bean;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Raul on 11/9/16.
 */
public class PlayerService {
    /**
     * Get all players in the database
     * @return
     * @throws SQLException
     */
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> found = new ArrayList<Player>();
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = dbUtil.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * from pingpong.player");
        found = convertResultsToList(rs);

        return found;
    }
    private List<Player> convertResultsToList(ResultSet rs) throws SQLException {
        List<Player> found = new ArrayList<Player>();
        while(rs.next()){
            Player tmp = new Player();
            tmp.setName(rs.getString("pla_name"));
            tmp.setId(rs.getLong("pla_id"));
            tmp.setNickname(rs.getString("pla_nickname"));
            tmp.setWins(rs.getInt("pla_wins"));
            tmp.setLosses(rs.getInt("pla_losses"));
            found.add(tmp);
        }
        return found;
    }

    public void save(Player myPlayer) throws SQLException {
        DataBaseUtil myDba = new DataBaseUtil();
        Connection c = null;
        Player foundById = null;
        try {
            c = myDba.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO pingpong.player " +
                    "(pla_id, pla_name, pla_nickname, pla_wins, pla_losses) VALUES (  nextval('pingpong.PLAYER_SEQ'),?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, myPlayer.getName());
            ps.setString(2, myPlayer.getNickname());
            ps.setInt(3, myPlayer.getWins());
            ps.setInt(4, myPlayer.getLosses());
            ps.executeUpdate();
            // now lets get the id
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                myPlayer.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            c.rollback();
            throw e;
        } finally {
            c.close();
        }
    }
    //way to say saves the id or return id
    public Player getPlayerById(long idConv) throws SQLException {
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = null;
        Player foundById = null;

        try {
            c = dbUtil.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("select * from pingpong.player WHERE pla_id = ?;");
            ps.setLong(1, idConv);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                foundById = new Player();
                foundById.setName(rs.getString("pla_name"));
                foundById.setId(rs.getLong("pla_id"));
                foundById.setNickname(rs.getString("pla_nickname"));
                foundById.setWins(rs.getInt("pla_wins"));
                foundById.setLosses(rs.getInt("pla_losses"));
            }
        }catch(SQLException t){
            t.printStackTrace();
            c.rollback();
            throw t;
        }finally {
            c.close();

        }
        return foundById;
    }
    public void update(Player aPlayer) throws SQLException{
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = null;
        try {
            c = dbUtil.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("UPDATE pingpong.player SET pla_name=?, pla_nickname=?, pla_wins=?, pla_losses=? WHERE pla_id = ?;");
            ps.setString(1, aPlayer.getName());
            ps.setString(2, aPlayer.getNickname());
            ps.setInt(3,aPlayer.getWins());
            ps.setInt(4,aPlayer.getLosses());
            ps.setLong(5, aPlayer.getId());
            ps.executeUpdate();
        }catch(SQLException t){
            t.printStackTrace();
            c.rollback();
            throw t;
        }finally {
            c.close();

        }
    }
    public void delete(long id) throws SQLException{
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = null;
        try {
            c = dbUtil.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("DELETE FROM pingpong.player  where pla_id=?");
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException t){
            t.printStackTrace();
            c.rollback();
            throw t;
        }finally {
            c.close();

        }
    }

}
