package com.amarpreetsinghprojects.twitterapi;

/**
 * Created by kulvi on 07/07/17.
 */

public class Statuses {

    String timestamp,screen_name,text,link,retweet_count,favourites_count;
    User user;


    public Statuses(String timestamp, String screen_name, String text, String link, User user,String retweet_count, String favourites_count) {
        this.timestamp = timestamp;
        this.screen_name = screen_name;
        this.text = text;
        this.link = link;
        this.user = user;
        this.retweet_count = retweet_count;
        this.favourites_count = favourites_count;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public User getUser() {
        return user;
    }

    public String getRetweet_count() {
        return retweet_count;
    }

    public String getFavourites_count() {
        return favourites_count;
    }
}
