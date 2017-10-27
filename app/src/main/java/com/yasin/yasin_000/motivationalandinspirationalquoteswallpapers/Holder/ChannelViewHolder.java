package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity.VideoActivity;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity.WallpaperFullscreen;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ChannelsData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

/**
 * Created by yasin_000 on 10.10.2017.
 */

public class ChannelViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
    Context context;
    CardView cardView;

    public ChannelViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.channel_textView);
        imageView = itemView.findViewById(R.id.channel_image);
        cardView = itemView.findViewById(R.id.channel_cardview);
        context = itemView.getContext();
    }

    public void bind(final ChannelsData channelsData) {
        Glide.with(context)
                .load(channelsData.getThumbnail())
                .into(imageView);

        textView.setText(channelsData.getName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("playlistId", channelsData.getId());
                context.startActivity(intent);
            }
        });
    }
}
