package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubePlayer;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity.VideoActivity;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Data;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Item;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

/**
 * Created by yasin_000 on 11.10.2017.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    Context context;
    CardView cardView;
    TextView textView;
    ImageButton imageButton;

    public VideosViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.videos_recyclerview_imageview);
        context = itemView.getContext();
        cardView = itemView.findViewById(R.id.videos_cardview);
        textView = itemView.findViewById(R.id.video_name);
        imageButton = itemView.findViewById(R.id.videos_imageButton);
    }

    public void bind(final Item item) {

        Glide.with(context)
                .load(item.getSnippet().getThumbnails().getMaxres() != null ?
                            item.getSnippet().getThumbnails().getMaxres().getUrl():
                        item.getSnippet().getThumbnails().getStandard() != null ?
                                item.getSnippet().getThumbnails().getStandard().getUrl():
                                        item.getSnippet().getThumbnails().getMedium().getUrl()
                    )
                .into(imageView);

        textView.setText(item.getSnippet().getTitle());

        final PopupMenu popupMenu = new PopupMenu(context, imageButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoActivity.playVideo(item.getSnippet().getResourceId().getVideoId());
            }
        });
    }
}
