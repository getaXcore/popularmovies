package com.wordpress.getaufansepta.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wordpress.getaufansepta.popularmovies.R;
import com.wordpress.getaufansepta.popularmovies.model.Result;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Taufan Septaufani on 31-Jan-18.
 */

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.CategoryViewHolder> {
    private Context context;
    private List<Result> list;


    public void setListMovie(List<Result> listMovie){
        list = listMovie;
    }

    public AdapterMovie(Context context){
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder,int position){

        //Log.i("list",String.valueOf(getList()));
        String img_poster_url = "http://image.tmdb.org/t/p/w780/" + getList().get(position).getPosterPath();

        Picasso.with(context).load(img_poster_url)
                .placeholder(R.color.cardview_dark_background)
                .error(R.mipmap.ic_launcher)
                .into(holder.itemMoviePoster);

    }

    @Override
    public int getItemCount(){
        return getList().size();
    }

    public List<Result> getList(){
        return list;
    }
    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView itemMoviePoster;
        public CategoryViewHolder (View itemView){
            super(itemView);
            itemMoviePoster = itemView.findViewById(R.id.itemMoviePoster);
        }
    }
}
