package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.ChannelRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.VideosRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Api.VideoApi;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Api.YoutubeService;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Data;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Item;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.OnBottomReachedListener;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String playlistId;
    YoutubeService youtubeService;
    Call<Data> listCall;
    List<Item> items;
    static YouTubePlayer mYoutubePlayer;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayerFragment youTubePlayerFragment;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    boolean isFullscreen;
    String nextPageToken;
    VideosRecyclerAdapter adapter;
    ProgressBar progressBar;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE && mYoutubePlayer != null){
            recyclerView.setVisibility(View.INVISIBLE);
            mYoutubePlayer.setFullscreen(true);
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT ) {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);

        items = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            playlistId = bundle.getString("playlistId");
        }
        progressBar = findViewById(R.id.video_recyclerview_progressbar);
        youTubePlayerView = findViewById(R.id.youtube_player);
        recyclerView = findViewById(R.id.videos_activity_recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
         Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com/")
                .build();

        youtubeService = retrofit.create(YoutubeService.class);
        listCall = youtubeService.getCallWithKey(12, playlistId);

            listCall.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
                    items = response.body().getItems();

                    nextPageToken = response.body().getNextPageToken();
                    Log.i("itemsSize", String.valueOf(items.size()));
                    for (int i = 0; i < items.size(); i++) {
                        Log.i("sorunnedir", items.get(i).getSnippet().getResourceId().getVideoId());
                    }

                    adapter = new VideosRecyclerAdapter(items, VideoActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player);
                    youTubePlayerFragment.initialize("AIzaSyBgM7tXI016kvmQjouX8D2jVHO7imdJDxY", VideoActivity.this);

                }

                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Toast.makeText(VideoActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                }
            });



    }

//    private void getMoreData(final VideosRecyclerAdapter adapter) {
//        adapter.setOnBottomReachedListener(new OnBottomReachedListener() {
//            @Override
//            public void onBottomReached(int position) {
//                Toast.makeText(VideoActivity.this, "On the end", Toast.LENGTH_SHORT).show();
//                Call<Data> listCall;
//                listCall = youtubeService.getCallWithNextPageToken(playlistId ,nextPageToken);
//                listCall.enqueue(new Callback<Data>() {
//                    @Override
//                    public void onResponse(Call<Data> call, Response<Data> response) {
//                        List<Item> responseList = response.body().getItems();
//                        for (int i = 0; i < responseList.size() ; i++) {
//                            items.add(responseList.get(i));
//                        }
//                        adapter.notifyDataSetChanged();
//                        recyclerView.setAdapter(adapter);
//
//
//                        doItOnce = false;
//                    }
//
//                    @Override
//                    public void onFailure(Call<Data> call, Throwable t) {
//                        Toast.makeText(VideoActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.loadVideo(items.get(0).getSnippet().getResourceId().getVideoId());
            mYoutubePlayer = youTubePlayer;
        }
    }



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.i("erroreroro", youTubeInitializationResult.toString());
    }

    public static void playVideo(String videoId) {
        mYoutubePlayer.loadVideo(videoId);
    }

    @Override
    public void onBackPressed() {
        if (isFullscreen){
            mYoutubePlayer.setFullscreen(false);
        } else{
            super.onBackPressed();
        }
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(5, linearLayoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                // load your items here
                // logic of loading items will be different depending on your specific use case
                Toast.makeText(VideoActivity.this, "On the end", Toast.LENGTH_SHORT).show();
                Call<Data> listCall;
                listCall = youtubeService.getCallWithNextPageToken(playlistId ,nextPageToken);
                listCall.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        List<Item> responseList = response.body().getItems();
                        for (int i = 0; i < responseList.size() ; i++) {
                            items.add(responseList.get(i));
                        }
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(VideoActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
                    }
                });

                // when new items are loaded, combine old and new items, pass them to your adapter
                // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView
                refreshView(recyclerView, new VideosRecyclerAdapter(items, VideoActivity.this), firstVisibleItemPosition);
            }
        };
    }
    //****************************************************************
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1){
//            getYoutubePlayerProvider().initialize("AIzaSyBgM7tXI016kvmQjouX8D2jVHO7imdJDxY", this);
//        }
//    }
//
//    private YouTubePlayer.Provider getYoutubePlayerProvider() {
//        return findViewById(R.id.youtube_player);
//    }



}
