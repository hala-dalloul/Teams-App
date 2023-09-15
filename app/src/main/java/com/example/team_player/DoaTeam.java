package com.example.team_player;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DoaTeam {
    @Insert
    void insertTeam(Team team);
    @Update
    void updateTeam(Team team);
    @Delete
    void deleteTeam(Team team);

    @Query("SELECT * FROM Team")
    LiveData<List<Team>> getAllTeams();

    @Query("select name from Team where id=:id")
    LiveData<String> getTeamName(int id);



    @Query("select name from Team ")
    LiveData<List<String>> getAllTeamName();

    @Query("select * from Team order by numberOfCups DESC")
    LiveData<List<Team>> getOrderTeamDESC();
    @Query("select * from Team order by numberOfCups  ASC")
    LiveData<List<Team>> getOrderTeamASC();

    @Query("select * from Team where id=:id")
    LiveData<Team> getTeam(int id);



}
