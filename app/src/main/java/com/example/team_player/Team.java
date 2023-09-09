package com.example.team_player;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Team {
    @PrimaryKey(autoGenerate = true)
  private int id;
  private String name;
  private int numberOfCups;

    public Team(String name, int numberOfCups) {
        this.name = name;
        this.numberOfCups = numberOfCups;
    }
    @Ignore
    public Team(int id, String name, int numberOfCups) {
        this.id = id;
        this.name = name;
        this.numberOfCups = numberOfCups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCups() {
        return numberOfCups;
    }

    public void setNumberOfCups(int numberOfCups) {
        this.numberOfCups = numberOfCups;
    }
}
