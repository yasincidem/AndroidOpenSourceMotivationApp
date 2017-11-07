package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Api;

import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Data;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yasin_000 on 10.10.2017.
 */

public interface YoutubeService {
    @GET("youtube/v3/playlistItems?part=snippet&key=YOUR_API_KEY")
    Call<Data> getCallWithKey(
            @Query("maxResults") int number,
            @Query("playlistId") String playlistId);

    @GET("youtube/v3/playlistItems?part=snippet&maxResults=5&key=YOUR_API_KEY")
    Call<Data> getCallWithNextPageToken(
            @Query("playlistId") String playlistId,
            @Query("pageToken") String pageToken);
}
