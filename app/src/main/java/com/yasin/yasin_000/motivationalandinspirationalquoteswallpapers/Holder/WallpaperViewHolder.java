package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity.WallpaperFullscreen;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ImagesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

/**
 * Created by yasin_000 on 7.10.2017.
 */

public class WallpaperViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    Context context;
    CardView cardView;

    public WallpaperViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewRecycler);
        cardView = itemView.findViewById(R.id.wallpaper_cardview);
        context = itemView.getContext();
    }

    public void bind(final ImagesData imagesData, final int position) {
        Glide.with(context)
                .load(imagesData.getUrl())
                .into(imageView);
        Log.i("şşşş", imagesData.getUrl());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WallpaperFullscreen.class);
                intent.putExtra("WALLPAPER", imagesData.getUrl());
                intent.putExtra("CURRENTITEM", position);
                context.startActivity(intent);
            }
        });
    }
}
