package com.wordpress.getaufansepta.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wordpress.getaufansepta.popularmovies.adapter.AdapterMovie;
import com.wordpress.getaufansepta.popularmovies.adapter.MovieAdapterDB;
import com.wordpress.getaufansepta.popularmovies.database.MovieDB;
import com.wordpress.getaufansepta.popularmovies.database.MovieHelper;
import com.wordpress.getaufansepta.popularmovies.helper.ItemClickView;
import com.wordpress.getaufansepta.popularmovies.model.MovieModel;
import com.wordpress.getaufansepta.popularmovies.model.Result;
import com.wordpress.getaufansepta.popularmovies.rest.ApiClient;
import com.wordpress.getaufansepta.popularmovies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView movieList;
    MovieHelper movieHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        movieList = (RecyclerView)findViewById(R.id.movieList);
        movieHelper = new MovieHelper(MainActivity.this);

        //get movie data from database
        getMovieFromDB();

        //get movie data from server
        getMovie("<api_key>");

    }

    private void getMovieFromDB() {
        //get all movie poster
        final List<MovieDB> list = movieHelper.getAllMoviePoster();

        MovieAdapterDB adapterMovie = new MovieAdapterDB(MainActivity.this);
        adapterMovie.setListMovie(list); //add to list movie adapter

        //add movie poster to recyclerView
        movieList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        movieList.setAdapter(adapterMovie);

        ItemClickView.addTo(movieList).setOnItemClickListener(new ItemClickView.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);

                //send id to DetailActivity
                intent.putExtra("id",list.get(position).getId());

                //start DetailActivity
                startActivity(intent);

                //Toast.makeText(MainActivity.this,"klik detail"+String.valueOf(list.get(position).getId()),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getMovie(String api_key){
        //init new progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Getting data...");

        //show progress dialog
        progressDialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieModel> movieModelCall = apiInterface.getResult(api_key,"1"); //call the result

        movieModelCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                try {

                    progressDialog.dismiss(); // don't show progress dialog

                    //get Result into List
                    final List<Result> list = response.body().getResults();

                    AdapterMovie adapterMovie = new AdapterMovie(MainActivity.this);
                    adapterMovie.setListMovie(list);//add to list movie adapter

                    //add movie poster to recyclerView
                    movieList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    movieList.setAdapter(adapterMovie);

                    ItemClickView.addTo(movieList).setOnItemClickListener(new ItemClickView.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView recyclerView, int position, View v) {
                            Intent intent = new Intent(MainActivity.this,DetailActivity.class);

                            //send id to DetailActivity
                            intent.putExtra("id",list.get(position).getId());

                            //start DetailActivity
                            startActivity(intent);
                        }
                    });



                }catch (NullPointerException e){
                    Log.e("Result","onResponse",e );
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                progressDialog.dismiss();
                //Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("error",t.getMessage() );
            }
        });
    }
}
