package com.amarpreetsinghprojects.twitterapi;

/**
 * Created by kulvi on 07/07/17.
 */

public class User {

    String profile_image_url_https,screen_name,name,user_id;

    public User(String profile_image_url_https, String screen_name, String name, String user_id) {
        this.profile_image_url_https = profile_image_url_https;
        this.screen_name = screen_name;
        this.name = name;
        this.user_id = user_id;
    }

    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public String getUser_id() {
        return user_id;
    }
}
