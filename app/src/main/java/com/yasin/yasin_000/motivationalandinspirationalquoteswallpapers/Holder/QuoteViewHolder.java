package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.QuotesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class QuoteViewHolder extends RecyclerView.ViewHolder {
    private TextView quoteTextView, personTextView;
    ImageView shareImageButton;
    Context context;

    public QuoteViewHolder(View itemView) {
        super(itemView);
        quoteTextView = itemView.findViewById(R.id.textView);
        personTextView = itemView.findViewById(R.id.person_textview);
        shareImageButton = itemView.findViewById(R.id.quote_share_button);
        context = itemView.getContext();
    }

    public void bind(final QuotesData quotesData) {
        quoteTextView.setText(quotesData.getQuote());
        personTextView.setText(quotesData.getPerson());
        shareImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, quotesData.getQuote() + "\n\n" + quotesData.getPerson());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Share quote via"));
            }
        });
        Log.i("ççççç" , quotesData.getQuote());
    }
}
