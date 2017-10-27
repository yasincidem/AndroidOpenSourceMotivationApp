package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.WebsiteData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import org.w3c.dom.Text;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class WebsiteViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;
    private Context context;
    private CardView cardView;

    public WebsiteViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.website_image);
        textView = itemView.findViewById(R.id.website_textView);
        context = itemView.getContext();
        cardView = itemView.findViewById(R.id.websites_cardview);
    }

    public void bind(final WebsiteData websiteData) {
        Log.i("qqqiii", websiteData.getImageUrl());
        Glide.with(context)
                .load(websiteData.getImageUrl())
                .into(imageView);

        textView.setText(websiteData.getName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomTab(websiteData.getUrl());
            }
        });
    }

    public void getCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
