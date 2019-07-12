package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ticketzone.org.com.app_mngr.R;

public class StoreMenuAdapter extends ExpandableRecyclerViewAdapter<MenuTitleViewHolder, MenuItemViewHolder> {
    private String title;
    private String menu_name;
    private String menu_price;
    private String menu_code;

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
    public void onBindChildViewHolder(MenuItemViewHolder holder, int flatPosition, final ExpandableGroup group, int childIndex) {
        final StoreMenuItem menuItem = (StoreMenuItem) group.getItems().get(childIndex);
        holder.bind(menuItem);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setChildItem(menuItem.menu_code, group.getTitle(), menuItem.menu_name, menuItem.menu_price);
//            }
//        });
    }

    @Override
    public void onBindGroupViewHolder(MenuTitleViewHolder holder, int flatPosition, final ExpandableGroup group) {
        final StoreMenuTitle menuTitle = (StoreMenuTitle) group;
        holder.bind(menuTitle);
    }

//    public void setChildItem(String menu_code, String title, String menu_name, String menu_price) {
//        this.menu_code = menu_code;
//        this.title = title;
//        this.menu_name = menu_name;
//        this.menu_price = menu_price.substring(0, menu_price.length()-1); // 마지막 "원" 빼고 저장
//    }
//    public Map<String, String> getChildItem() {
//        Map<String, String> childmap = new HashMap();
//        childmap.put("title", title);
//        childmap.put("menu_code", menu_code);
//        childmap.put("menu_name", menu_name);
//        childmap.put("menu_price", menu_price);
//
//        return childmap;
//    }
}
