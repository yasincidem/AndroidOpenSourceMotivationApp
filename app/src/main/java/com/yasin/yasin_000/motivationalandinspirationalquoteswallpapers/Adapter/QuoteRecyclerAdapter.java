package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.QuotesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.QuoteViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.List;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class QuoteRecyclerAdapter extends RecyclerView.Adapter<QuoteViewHolder>{
    private List<QuotesData> quotesDataList;
    private Context context;

    public QuoteRecyclerAdapter(List<QuotesData> quotesDataList, Context context) {
        this.quotesDataList = quotesDataList;
        this.context = context;
    }


    @Override
    public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quote_row, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuoteViewHolder holder, int position) {
        holder.bind(quotesDataList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.i("üüüüüüüüü", String.valueOf(quotesDataList.size()));
        return quotesDataList.size();
    }
}
