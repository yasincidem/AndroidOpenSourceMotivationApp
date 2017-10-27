package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ChannelsData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.ChannelViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.List;

/**
 * Created by yasin_000 on 10.10.2017.
 */

public class ChannelRecyclerAdapter extends RecyclerView.Adapter<ChannelViewHolder> {

    Context context;
    List<ChannelsData> channelsDataList;

    public ChannelRecyclerAdapter(List<ChannelsData> channelsDataList, Context context) {
        this.context = context;
        this.channelsDataList = channelsDataList;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.channel_row, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        holder.bind(channelsDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return channelsDataList.size();
    }
}
