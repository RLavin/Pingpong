package com.ironyard.services;

import com.ironyard.data.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 11/10/16.
 */
public class MatchService {
    /**
     * Get all matches in the database
     * @return
     * @throws SQLException
     */
    public List<Match> getAllMatches() throws SQLException {
        List<Match> found = new ArrayList<Match>();
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = dbUtil.getConnection();
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * from pingpong.match");
        found = convertResultsToList(rs);

        return found;

    }
    private List<Match> convertResultsToList(ResultSet rs) throws SQLException {
        List<Match> found = new ArrayList<Match>();
        while(rs.next()){
            Match tmp = new Match();
            tmp.setPlayerOne(rs.getString("mat_playerOne"));
            tmp.setId(rs.getLong("mat_id"));
            tmp.setPlayerTwo(rs.getString("mat_playerTwo"));
            tmp.setPlayerOneScore(rs.getInt("mat_playerOneScore"));
            tmp.setPlayerTwoScore(rs.getInt("mat_playerTwoScore"));
            found.add(tmp);
        }
        return found;
    }

    public void save(Match myMatch) throws SQLException {
        DataBaseUtil myDba = new DataBaseUtil();
        Connection c = null;
        Match foundById = null;
        try {
            c = myDba.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO pingpong.match " +
                            "(mat_id, mat_playerOne, mat_playerTwo, mat_playerOneScore, mat_playerTwoScore) VALUES (  nextval('pingpong.MATCH_SEQ'),?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, myMatch.getPlayerOne());
            ps.setString(2, myMatch.getPlayerTwo());
            ps.setInt(3, myMatch.getPlayerOneScore());
            ps.setInt(4, myMatch.getPlayerTwoScore());
            ps.executeUpdate();
            // now lets get the id
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                myMatch.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            c.rollback();
            throw e;
        } finally {
            c.close();
        }
    }
    public Match getMatchById(long idConv) throws SQLException {
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = null;
        Match foundById = null;

        try {
            c = dbUtil.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("select * from pingpong.match WHERE mat_id = ?;");
            ps.setLong(1, idConv);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                foundById = new Match();
                foundById.setPlayerOne(rs.getString("mat_playerOne"));
                foundById.setId(rs.getLong("mat_id"));
                foundById.setPlayerTwo(rs.getString("mat_playerTwo"));
                foundById.setPlayerOneScore(rs.getInt("mat_playerOneScore"));
                foundById.setPlayerTwoScore(rs.getInt("mat_playerTwoScore"));
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

    public void update(Match myMatch) throws SQLException{
        DataBaseUtil dbUtil = new DataBaseUtil();
        Connection c = null;
        try {
            c = dbUtil.getConnection();
            // do a starts with search
            PreparedStatement ps = c.prepareStatement("UPDATE pingpong.match SET mat_playerOne=?, mat_playerTwo=?, mat_playerOneScore=?, mat_playerTwoScore=? WHERE mat_id = ?;");
            ps.setString(1, myMatch.getPlayerOne());
            ps.setString(2, myMatch.getPlayerTwo());
            ps.setInt(3,myMatch.getPlayerOneScore());
            ps.setInt(4,myMatch.getPlayerTwoScore());
            ps.setLong(5, myMatch.getId());
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
            PreparedStatement ps = c.prepareStatement("DELETE FROM pingpong.match  where mat_id=?");
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
