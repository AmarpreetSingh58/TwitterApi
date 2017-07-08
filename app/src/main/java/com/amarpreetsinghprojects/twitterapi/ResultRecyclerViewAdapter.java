package com.amarpreetsinghprojects.twitterapi;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kulvi on 07/04/17.
 */

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ResultViewHolder> {

    ArrayList<Statuses> twitterDataArrayList;
    RecyclerViewItemClickListner listner;
    Context c;
    public ResultRecyclerViewAdapter(ArrayList<Statuses> twitterDataArrayList, RecyclerViewItemClickListner listner) {
        this.twitterDataArrayList = twitterDataArrayList;
        this.listner = listner;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.search_single_item,parent,false);
        c = parent.getContext();
        return new ResultViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.Bind(twitterDataArrayList.get(position),listner);
    }

    @Override
    public int getItemCount() {
        return twitterDataArrayList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView name,screenName,userId,timeStamp,textTV,link,likeCount,retweetCount;
        ImageView profileImage;
        FloatingActionButton likeButton,retweetButton;

        public ResultViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            screenName = (TextView)itemView.findViewById(R.id.screenName);
            userId = (TextView)itemView.findViewById(R.id.userId);
            timeStamp = (TextView)itemView.findViewById(R.id.timeStamp);
            textTV = (TextView)itemView.findViewById(R.id.textTV);
            link = (TextView)itemView.findViewById(R.id.link);
            profileImage = (ImageView)itemView.findViewById(R.id.profileImage);
            likeButton = (FloatingActionButton)itemView.findViewById(R.id.likeButton);
            retweetButton = (FloatingActionButton)itemView.findViewById(R.id.retweetButton);
            likeCount = (TextView)itemView.findViewById(R.id.likeCountTV);
            retweetCount = (TextView)itemView.findViewById(R.id.retweetCountTV);
        }
        public void Bind(final Statuses user, final RecyclerViewItemClickListner listner){

            name.setText(user.getUser().name);
            screenName.setText(user.getScreen_name());
            userId.setText(user.getUser().getUser_id());
            timeStamp.setText(user.getTimestamp());
            textTV.setText(user.getText());
            link.setText(user.getLink());
            Picasso.with(c).load(user.getUser().getProfile_image_url_https()).resize(200,200).into(profileImage);
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likeCount.setText(user.getFavourites_count());
                }
            });
            retweetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retweetCount.setText(user.getRetweet_count());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnItemClick(user);
                }
            });
        }
    }
}
