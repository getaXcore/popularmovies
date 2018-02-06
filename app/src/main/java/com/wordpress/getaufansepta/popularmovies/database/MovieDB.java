package com.wordpress.getaufansepta.popularmovies.database;

/**
 * Created by Taufan Septaufani on 31-Jan-18.
 */

public class MovieDB {
    private int _id;
    private Double _voteAverage;
    private String _title;
    private String _poster;
    private String _overview;
    private String _releaseDate;

    public MovieDB(){

    }
    public MovieDB(int id,String title,Double voteAverage,String poster,String overview, String releasedate){
        this._id = id;
        this._title = title;
        this._voteAverage = voteAverage;
        this._poster = poster;
        this._overview = overview;
        this._releaseDate = releasedate;
    }
    public MovieDB(String title,Double voteAverage,String poster,String overview, String releasedate){
        this._title = title;
        this._voteAverage = voteAverage;
        this._poster = poster;
        this._overview = overview;
        this._releaseDate = releasedate;
    }
    public MovieDB(int id,String _poster){
        this._id = id;
        this._poster = _poster;
    }
    public void setId(int id){
        this._id = id;
    }
    public int getId(){
        return this._id;
    }
    public void setvoteAverage(Double voteAverage){
        this._voteAverage = voteAverage;
    }
    public Double getVoteAverage(){
        return this._voteAverage;
    }
    public void setTitle(String title){
        this._title = title;
    }
    public String getTitle(){
        return this._title;
    }
    public void setPosterPath(String poster){
        this._poster = poster;
    }
    public String getPosterPath(){
        return this._poster;
    }
    public void setOverview(String overview){
        this._overview = overview;
    }
    public String getOverview(){
        return this._overview;
    }
    public void setReleaseDate(String releaseDate){
        this._releaseDate = releaseDate;
    }
    public String getReleaseDate(){
        return this._releaseDate;
    }



}
