package com.example.team_player;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity (foreignKeys = {@ForeignKey
        (entity = Team.class,parentColumns = {"id"},childColumns = {"idTeam"})})
@TypeConverters({Switch.class})
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int shirtNumber;
    private Bitmap photo;
    private Date dateOfBirth;
    private int idTeam;



    public Player( String name, int shirtNumber, Bitmap photo, Date dateOfBirth, int idTeam) {
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.photo = photo;
        this.dateOfBirth = dateOfBirth;
        this.idTeam = idTeam;
    }

    @Ignore
    public Player(int id, String name, int shirtNumber, Bitmap photo, Date dateOfBirth, int idTeam) {
        this.id = id;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.photo = photo;
        this.dateOfBirth = dateOfBirth;
        this.idTeam = idTeam;
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

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }
}
