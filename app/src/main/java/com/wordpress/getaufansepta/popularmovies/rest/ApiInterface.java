package com.wordpress.getaufansepta.popularmovies.rest;

import com.wordpress.getaufansepta.popularmovies.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Taufan Septaufani on 31-Jan-18.
 */

public interface ApiInterface {
    @GET("popular")
    Call<MovieModel> getResult(@Query("api_key") String api_key,
                               @Query("page") String page_num);
}
