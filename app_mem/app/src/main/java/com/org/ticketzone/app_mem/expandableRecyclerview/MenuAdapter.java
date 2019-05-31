package com.org.ticketzone.app_mem.expandableRecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.ticketzone.app_mem.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MenuAdapter extends ExpandableRecyclerViewAdapter<MenuTitleViewHolder, MenuItemViewHolder> {
    public MenuAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public MenuTitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_title, parent, false);
        return new MenuTitleViewHolder(v);
    }

    @Override
    public MenuItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_item, parent, false);
        return new MenuItemViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(MenuItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final MenuItem menuItem = (MenuItem) group.getItems().get(childIndex);
        holder.bind(menuItem);
    }

    @Override
    public void onBindGroupViewHolder(MenuTitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        final MenuTitle menuTitle = (MenuTitle) group;
        holder.bind(menuTitle);
    }


}
