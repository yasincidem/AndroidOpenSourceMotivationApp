
package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Snippet {

    @SerializedName("channelId")
    private String mChannelId;
    @SerializedName("channelTitle")
    private String mChannelTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("playlistId")
    private String mPlaylistId;
    @SerializedName("position")
    private Long mPosition;
    @SerializedName("publishedAt")
    private String mPublishedAt;
    @SerializedName("resourceId")
    private ResourceId mResourceId;
    @SerializedName("thumbnails")
    private Thumbnails mThumbnails;
    @SerializedName("title")
    private String mTitle;

    public String getChannelId() {
        return mChannelId;
    }

    public void setChannelId(String channelId) {
        mChannelId = channelId;
    }

    public String getChannelTitle() {
        return mChannelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        mChannelTitle = channelTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPlaylistId() {
        return mPlaylistId;
    }

    public void setPlaylistId(String playlistId) {
        mPlaylistId = playlistId;
    }

    public Long getPosition() {
        return mPosition;
    }

    public void setPosition(Long position) {
        mPosition = position;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        mPublishedAt = publishedAt;
    }

    public ResourceId getResourceId() {
        return mResourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        mResourceId = resourceId;
    }

    public Thumbnails getThumbnails() {
        return mThumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        mThumbnails = thumbnails;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
