package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ImagesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.WallpaperViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by yasin_000 on 8.10.2017.
 */

public class WallpaperRecyclerAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {

    private List<ImagesData> imageDataList;
    private Context context;

    public WallpaperRecyclerAdapter(List<ImagesData> imageDataList, Context context) {
        this.imageDataList = imageDataList;
        this.context = context;
    }

    @Override
    public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_row, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpaperViewHolder holder, int position) {
        holder.bind(imageDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }
}
