package com.example.team_player;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Repo {

    private DoaPlayer doaPlayer;
    private DoaTeam doaTeam;

    public Repo(Application application) {
        DatabaseTP db=DatabaseTP.getDatabase(application);
        this.doaPlayer=db.playerDao();
        this.doaTeam=db.teamDao();
    }

    //team
    void insertTeam(Team team) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaTeam.insertTeam(team);
        });
    }
    void deleteTeam(Team team) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaTeam.deleteTeam(team);
        });
    }
    void updateTeam(Team team) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaTeam.updateTeam(team);
        });
    }
    LiveData<List<Team>> getAllTeams(){
        return doaTeam.getAllTeams();
    }
    LiveData<Team> getTeam(int id){
        return doaTeam.getTeam(id);
    }
    LiveData<String> getTeamName(int id){
        return doaTeam.getTeamName(id);
    }
    LiveData<List<String>> getAllTeamName(){
        return doaTeam.getAllTeamName();
    }
    LiveData<List<Team>> getOrderTeamDESC(){
        return doaTeam.getOrderTeamDESC();

    }LiveData<List<Team>> getOrderTeamASC(){
        return doaTeam.getOrderTeamASC();
    }

    //player
    void insertPlayer(Player p) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaPlayer.insertPlayer(p);
        });

    }
    void updatePlayer(Player player) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaPlayer.updatePlayer(player);
        });
    }
    void deletePlayer(Player player) {
        DatabaseTP.databaseWriteExecutor.execute(() -> {
            doaPlayer.deletePlayer(player);
        });
    }
    LiveData<List<Player>> getAllPlayer(){
        return doaPlayer.getAllPlayers();
    }

    LiveData<Player> getPlayerByName(String name){
       return doaPlayer.getPlayerByName(name);
    }
    LiveData<List<Player>> getPlayersByTeamId(int id){
        return doaPlayer.getPlayersByTeamId(id);
    }

}
