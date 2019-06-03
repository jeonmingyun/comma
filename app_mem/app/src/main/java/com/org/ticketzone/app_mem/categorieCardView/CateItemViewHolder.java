package com.org.ticketzone.app_mem.categorieCardView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.ticketzone.app_mem.R;

public class CateItemViewHolder extends RecyclerView.ViewHolder {
    ImageView cate_img;
    TextView cate_title;

    public CateItemViewHolder(@NonNull View itemView) {
        super(itemView);

        cate_img = itemView.findViewById(R.id.cate_img);
        cate_title = itemView.findViewById(R.id.cate_title);
    }
}
