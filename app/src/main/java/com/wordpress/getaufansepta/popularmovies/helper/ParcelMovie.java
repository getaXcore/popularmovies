package com.wordpress.getaufansepta.popularmovies.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Taufan Septaufani on 02-Feb-18.
 */

public class ParcelMovie implements Parcelable {
    private int _id;
    private Double _voteAverage;
    private String _title;
    private String _poster;
    private String _overview;
    private String _releaseDate;

    public void setId(int id){
        this._id = id;
    }
    public int getId(){
        return this._id;
    }
    public void setvoteAverage(Double voteAverage){
        this._voteAverage = voteAverage;
    }
    public Double getvoteAverage(){
        return this._voteAverage;
    }
    public void setTitle(String title){
        this._title = title;
    }
    public String getTitle(){
        return this._title;
    }
    public void setPoster(String poster){
        this._poster = poster;
    }
    public String getPoster(){
        return this._poster;
    }
    public void setOverview(String overview){
        this._overview = overview;
    }
    public String getOverview(){
        return this._overview;
    }
    public void setreleaseDate(String releaseDate){
        this._releaseDate = releaseDate;
    }
    public String getreleaseDate(){
        return this._releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this._id);
        parcel.writeDouble(this._voteAverage);
        parcel.writeString(this._title);
        parcel.writeString(this._poster);
        parcel.writeString(this._overview);
        parcel.writeString(this._releaseDate);
    }

    public ParcelMovie(){

    }

    protected ParcelMovie(Parcel in){
        this._id = in.readInt();
        this._title = in.readString();
        this._voteAverage = in.readDouble();
        this._poster = in.readString();
        this._overview = in.readString();
        this._releaseDate = in.readString();
    }

    public static final Creator<ParcelMovie> CREATOR = new Creator<ParcelMovie>() {
        @Override
        public ParcelMovie createFromParcel(Parcel parcel) {
            return new ParcelMovie(parcel);
        }

        @Override
        public ParcelMovie[] newArray(int i) {
            return new ParcelMovie[0];
        }
    };
}
