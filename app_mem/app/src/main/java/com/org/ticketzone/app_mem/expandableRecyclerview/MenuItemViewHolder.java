package com.org.ticketzone.app_mem.expandableRecyclerview;

import android.view.View;
import android.widget.TextView;

import com.org.ticketzone.app_mem.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class MenuItemViewHolder extends ChildViewHolder {

    private TextView menu_name;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        menu_name = itemView.findViewById(R.id.menu_name);
        menu_name = itemView.findViewById(R.id.menu_price);
    }

    public void bind(MenuItem item) {
        menu_name.setText(item.name);
    }
}
