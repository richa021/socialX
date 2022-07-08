package com.example.socialx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title, text_details, text_time, text_channel;
    ImageView image_headline;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_channel = itemView.findViewById(R.id.headline_channel);
        text_time = itemView.findViewById(R.id.headline_time);
        text_details = itemView.findViewById(R.id.detail_headlines);
        text_title = itemView.findViewById(R.id.title_headlines);
        image_headline = itemView.findViewById(R.id.headline_image);
        cardView = itemView.findViewById(R.id.headline_container);

    }
}
