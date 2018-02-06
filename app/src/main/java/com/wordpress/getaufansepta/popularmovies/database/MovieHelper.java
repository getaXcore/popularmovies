package com.wordpress.getaufansepta.popularmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taufan Septaufani on 31-Jan-18.
 */

public class MovieHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MovieManager";
    private static final String TABLE_NAME = "movie";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_VOTE_AVERAGE = "voteAverage";
    private static final String KEY_POSTER_PATH = "poster";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_RELEASE_DATE = "releaseDate";

    public MovieHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "
                +KEY_ID+" BIGINT PRIMARY KEY, "
                +KEY_TITLE+" TEXT, "
                +KEY_VOTE_AVERAGE+" TEXT, "
                +KEY_POSTER_PATH+" TEXT, "
                +KEY_OVERVIEW+" TEXT, "
                +KEY_RELEASE_DATE+" TEXT "
                +")";
        //create table
        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        //recreate table
        onCreate(db);
    }
    //insert new record
    public void addMovie(MovieDB moviedb){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,moviedb.getId());
        values.put(KEY_TITLE,moviedb.getTitle());
        values.put(KEY_VOTE_AVERAGE,moviedb.getVoteAverage());
        values.put(KEY_POSTER_PATH,moviedb.getPosterPath());
        values.put(KEY_OVERVIEW,moviedb.getOverview());
        values.put(KEY_RELEASE_DATE,moviedb.getReleaseDate());

        //insert data
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    //get single poster image
    public MovieDB getMoviePoster(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_POSTER_PATH},
                KEY_ID+" =? ",new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        MovieDB movieDB = new MovieDB(Integer.parseInt(cursor.getString(0)),cursor.getString(1));

        return movieDB;
    }
    //get all poster image
    public List<MovieDB> getAllMoviePoster(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<MovieDB> movieDBList = new ArrayList<MovieDB>();

        //select all query
        String selectQuery = "SELECT "+KEY_ID+","+KEY_POSTER_PATH+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery,null);

        //looping all rows and adding to list
        if (cursor.moveToFirst()){
            do {
                MovieDB movieDB = new MovieDB();
                movieDB.setId(Integer.parseInt(cursor.getString(0)));
                movieDB.setPosterPath(cursor.getString(1));

                movieDBList.add(movieDB);
            }while (cursor.moveToNext());
        }
        return movieDBList;

    }

    //get single movie detail
    public MovieDB getMovieDetail(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{KEY_ID,KEY_TITLE,KEY_VOTE_AVERAGE,KEY_POSTER_PATH,KEY_OVERVIEW,KEY_RELEASE_DATE},
                KEY_ID+" =? ", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        MovieDB movieDB = new MovieDB(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Double.parseDouble(cursor.getString(2)),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));
        return movieDB;
    }

    public void deteleMovieAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_ALL_RECORD = "DELETE FROM "+TABLE_NAME;

        db.execSQL(DELETE_ALL_RECORD);
    }

    public int getMovieCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectCount = "SELECT COUNT(*) FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(selectCount,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        return count;
    }
}
