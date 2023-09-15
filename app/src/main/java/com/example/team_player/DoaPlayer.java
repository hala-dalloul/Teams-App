package com.example.team_player;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DoaPlayer {
    @Insert
    void insertPlayer(Player player);
    @Update
    void updatePlayer(Player player);
    @Delete
    void deletePlayer(Player player);
    @Query("SELECT * FROM Player")
    LiveData<List<Player >> getAllPlayers();



    @Query("select * from Player where name=:name ")
    LiveData<Player> getPlayerByName(String name);

    @Query("select * from Player where idTeam=:id")
    LiveData<List<Player>> getPlayersByTeamId(int id);
}
