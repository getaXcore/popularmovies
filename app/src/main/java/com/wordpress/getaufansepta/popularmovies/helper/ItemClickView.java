package com.wordpress.getaufansepta.popularmovies.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wordpress.getaufansepta.popularmovies.R;

/**
 * Created by Taufan Septaufani on 02-Feb-18.
 */

public class ItemClickView {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private ItemClickView(RecyclerView recyclerView){
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_view,this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null){
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClick(mRecyclerView,viewHolder.getAdapterPosition(),v);
            }
        }
    };

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClick(mRecyclerView,holder.getAdapterPosition(),v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {

        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };
    public static ItemClickView addTo(RecyclerView view) {
        ItemClickView clickView = (ItemClickView) view.getTag(R.id.item_click_view);
        if (clickView == null) {
            clickView = new ItemClickView(view);
        }
        return clickView;
    }
    public static ItemClickView removeFrom(RecyclerView view) {
        ItemClickView clickView = (ItemClickView) view.getTag(R.id.item_click_view);
        if (clickView != null) {
            clickView.detach(view);
        }
        return clickView;
    }
    public ItemClickView setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }
    public ItemClickView setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }
    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_view, null);
    }
    public interface OnItemClickListener {
        //void onItemClicked(RecyclerView recyclerView, int position, View v);
        void onItemClick(RecyclerView recyclerView, int position, View v);
    }
    public interface OnItemLongClickListener {
        //boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
        boolean onItemLongClick(RecyclerView recyclerView, int position, View v);
    }
}
