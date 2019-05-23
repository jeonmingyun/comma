package com.org.ticketzone.app_mem.listViewAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CustomAdapter<T> extends BaseAdapter {
    private ArrayList<T> listItems;

    public CustomAdapter(ArrayList<T> listItems) {
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int idx) {
        return listItems.get(idx);
    }

    @Override
    public long getItemId(int idx) {
        return 0;
    }

    @Override
    public View getView(int idx, View view, ViewGroup parent) {
//        Context context = parent.getContext();

        return view;
    }

}
