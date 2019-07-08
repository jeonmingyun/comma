package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import ticketzone.org.com.app_mngr.R;

public class StoreMenuAdapter extends ExpandableRecyclerViewAdapter<MenuTitleViewHolder, MenuItemViewHolder> {
    public StoreMenuAdapter(List<? extends ExpandableGroup> groups) {
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
        final StoreMenuItem menuItem = (StoreMenuItem) group.getItems().get(childIndex);
        holder.bind(menuItem);
    }

    @Override
    public void onBindGroupViewHolder(MenuTitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        final StoreMenuTitle menuTitle = (StoreMenuTitle) group;
        holder.bind(menuTitle);
    }

}
