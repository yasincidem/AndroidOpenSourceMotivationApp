package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity;

/**
 * Created by yasin_000 on 9.10.2017.
 */

public class WebsiteData {
    private String url;
    private String name;
    private String imageUrl;

    public WebsiteData(String url, String name, String imageUrl) {

        this.url = url;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public WebsiteData() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
