package com.amarpreetsinghprojects.twitterapi;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kulvi on 07/04/17.
 */

public class TwitterData {
    
    ArrayList<Statuses> statuses = new ArrayList<>();

    public TwitterData(ArrayList<Statuses> statuses) {
        this.statuses = statuses;
    }

    public ArrayList<Statuses> getStatuses() {
        return statuses;
    }
}
