package com.google.firebase.quickstart.auth;

/**
 * Created by Alex on 13-03-2018.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Observation implements Serializable {
    @SerializedName("Id") // Name of JSON attribute. Used for GSON de-serialization
    private int id;
    @SerializedName("BirdId")
    private int birdId;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("Created")
    private String created;
    @SerializedName("Latitude")
    private double latitude;
    @SerializedName("Longtitude")
    private double longtitude;
    @SerializedName("Placename")
    private String placename;
    @SerializedName("Population")
    private int population;
    @SerializedName("Comment")
    private String comment;
    @SerializedName("NameEnglish")
    private String nameEnglish;
    @SerializedName("NameDanish")
    private String nameDanish;

    public Observation() {
    }

    public Observation(int birdId, String comment, String created, int id, double latitude, double longtitude, String placename, int population, String userId, String nameEnglish, String nameDanish){
        this.birdId = birdId;
        this.comment = comment;
        this.created = created;
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.placename = placename;
        this.population = population;
        this.userId = userId;
        this.nameEnglish = nameEnglish;
        this.nameDanish = nameDanish;
    }


    // Properties (SET)
    public void setBirdId(int birdId) {this.birdId = birdId;}
    public void setComment(String comment) {this.comment =comment;}
    public void setCreated(String created) {this.created = created;}
    public void setId(int id) {this.id = id;}
    public void setLatitude(double latitude) {this.latitude = latitude;}
    public void setLongtitude(double longtitude) {this.longtitude = longtitude;}
    public void setPlacename(String placename) {this.placename = placename;}
    public void setPopulation(int population) {this.population = population;}
    public void setUserId(String userId) {this.userId = userId;}
    public void setNameEnglish(String nameEnglish) {this.nameEnglish = nameEnglish;}
    public void setNameDanish(String nameDanish) {this.nameDanish = nameDanish;}

    // Properties (GET)
    public int getBirdId(){return birdId;}
    public String getComment(){return comment;}
    public String getCreated(){return created;}
    public int getId(){return id;}
    public double getLatitude(){return latitude;}
    public double getLongtitude(){return longtitude;}
    public String getPlacename(){return placename;}
    public int getPopulation(){return population;}
    public String getUserId(){return userId;}
    public String getNameEnglish(){return nameEnglish;}
    public String getNameDanish(){return nameDanish;}

    @Override
    public String toString() {
        return nameEnglish;
    }
}
