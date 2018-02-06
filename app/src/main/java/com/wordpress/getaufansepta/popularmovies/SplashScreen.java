package com.wordpress.getaufansepta.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wordpress.getaufansepta.popularmovies.database.MovieDB;
import com.wordpress.getaufansepta.popularmovies.database.MovieHelper;
import com.wordpress.getaufansepta.popularmovies.model.MovieModel;
import com.wordpress.getaufansepta.popularmovies.model.Result;
import com.wordpress.getaufansepta.popularmovies.rest.ApiClient;
import com.wordpress.getaufansepta.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    MovieHelper db;
    final static String api_key = "<api_key>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //call getMovie class for saving data to database
        getMovie(api_key);

        //init model movie helper for getMovie class
        db = new MovieHelper(SplashScreen.this);
    }

    private void getMovie(String api_key) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieModel> resultCall = apiInterface.getResult(api_key,"1");
        resultCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                try {
                    final List<Result> list = response.body().getResults(); //get result into list

                    //delete all record on the table before new add data
                    db.deteleMovieAll();

                    for (int i=0;i<list.size();i++){

                        //add all data movie to database
                        db.addMovie(new MovieDB(
                                list.get(i).getId(),
                                list.get(i).getTitle(),
                                list.get(i).getVoteAverage(),
                                list.get(i).getPosterPath(),
                                list.get(i).getOverview(),
                                list.get(i).getReleaseDate()
                        ));
                    }

                    //Start MainActivity
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

                //get data count from database
                int count = db.getMovieCount();
                Log.i("count",String.valueOf(count));

                if (count <= 0){
                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    Log.i("onFailure",t.getMessage());
                }else {
                    //Start MainActivity
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
            }
        });
    }
}
