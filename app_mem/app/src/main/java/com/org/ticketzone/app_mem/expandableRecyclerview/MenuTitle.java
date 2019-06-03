package com.org.ticketzone.app_mem.expandableRecyclerview;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MenuTitle extends ExpandableGroup<MenuItem> {
    public MenuTitle(String title, List<MenuItem> items) {
        super(title, items);
    }
}
