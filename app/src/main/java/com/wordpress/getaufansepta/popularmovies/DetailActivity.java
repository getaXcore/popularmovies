package com.wordpress.getaufansepta.popularmovies;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpress.getaufansepta.popularmovies.database.MovieDB;
import com.wordpress.getaufansepta.popularmovies.database.MovieHelper;

public class DetailActivity extends AppCompatActivity {
    TextView _movieTitle,_movieReleaseDate,_movieVoteAverage,_movieOverview;
    ImageView _moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //display home~back button
        //setting parent activity on manifest file
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_detail);

        //init item from layout
        init();

        //set content from Intent
        int id = getIntent().getIntExtra("id",0);

        MovieHelper movieHelper = new MovieHelper(DetailActivity.this);
        MovieDB list = movieHelper.getMovieDetail(id);

        _movieTitle.setText(list.getTitle());
        _movieOverview.setText(list.getOverview());
        _movieReleaseDate.setText(list.getReleaseDate().substring(0,4));
        _movieVoteAverage.setText(list.getVoteAverage()+"/10");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+list.getPosterPath()).into(_moviePoster);

        //Toast.makeText(DetailActivity.this,"id "+String.valueOf(id),Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.homeAsUp:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        _movieTitle = (TextView)findViewById(R.id.movieTitle);
        _movieReleaseDate = (TextView)findViewById(R.id.movieReleaseDate);
        _movieVoteAverage = (TextView)findViewById(R.id.movieVoteAverage);
        _movieOverview = (TextView)findViewById(R.id.movieOverview);
        _moviePoster = (ImageView)findViewById(R.id.moviePoster);
    }

}
