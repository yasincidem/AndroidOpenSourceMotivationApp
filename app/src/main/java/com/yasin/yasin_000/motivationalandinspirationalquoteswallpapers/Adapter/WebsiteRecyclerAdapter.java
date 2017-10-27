package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.QuotesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.WebsiteData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.WebsiteViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.List;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class WebsiteRecyclerAdapter extends RecyclerView.Adapter<WebsiteViewHolder> {
    private List<WebsiteData> websiteDataList;
    private Context context;

    public WebsiteRecyclerAdapter(List<WebsiteData> websiteDataList, Context context) {
        this.websiteDataList = websiteDataList;
        this.context = context;
    }

    @Override
    public WebsiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.website_row, parent, false);
        return new WebsiteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WebsiteViewHolder holder, int position) {
        holder.bind(websiteDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return websiteDataList.size();
    }
}
