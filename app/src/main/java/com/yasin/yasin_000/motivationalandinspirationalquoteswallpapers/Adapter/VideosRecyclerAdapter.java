package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Data;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Item;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.VideosViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.OnBottomReachedListener;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.List;

/**
 * Created by yasin_000 on 11.10.2017.
 */

public class VideosRecyclerAdapter extends RecyclerView.Adapter<VideosViewHolder> {

    List<Item> items;
    Context context;


    public VideosRecyclerAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_row, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
