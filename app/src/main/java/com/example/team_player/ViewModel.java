package com.example.team_player;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.team_player.Player;
import com.example.team_player.Repo;
import com.example.team_player.Team;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repo repo;

    public ViewModel(@NonNull Application application) {
        super(application);
        repo=new Repo(application);
    }
    void insertTeam(Team team) {
        repo.insertTeam(team);
    }
//    void deleteTeam(Team team) {
//        repo.deleteTeam(team);
//    }
//    void updateTeam(Team team) {
//        repo.updateTeam(team);
//    }
    LiveData<List<Team>> getAllTeams(){
        return repo.getAllTeams();
    }
    LiveData<Team> getTeam(int id){
        return repo.getTeam(id);
    }
    LiveData<String> getTeamName(int id){
        return repo.getTeamName(id);
    }
    LiveData<List<String>> getAllTeamName(){
        return repo.getAllTeamName();
    }
    LiveData<List<Team>> getOrderTeamASC(){
        return repo.getOrderTeamASC();
    }LiveData<List<Team>> getOrderTeamDESC(){
        return repo.getOrderTeamDESC();
    }

    void insertPlayer(Player player){repo.insertPlayer(player);}
//    void updatePlayer(Player player){
//        repo.updatePlayer(player);
//    }
//    void deletePlayer(Player player){
//        repo.deletePlayer(player);
//    }
    LiveData<List<Player>> getAllPlayer(){
        return repo.getAllPlayer();
    }
    LiveData<Player> getPlayerByName(String name){
        return repo.getPlayerByName(name);
    }
    LiveData<List<Player>> getPlayersByTeamId(int id){
        return repo.getPlayersByTeamId(id);
    }
}
