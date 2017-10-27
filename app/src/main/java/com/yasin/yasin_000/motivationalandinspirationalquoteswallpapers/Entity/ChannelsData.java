package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity;

import java.io.Serializable;

/**
 * Created by yasin_000 on 10.10.2017.
 */

public class ChannelsData implements Serializable{
    private String id;
    private String name;
    private String thumbnail;

    public ChannelsData() {
    }

    public ChannelsData(String id, String name, String thumbnail) {

        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
