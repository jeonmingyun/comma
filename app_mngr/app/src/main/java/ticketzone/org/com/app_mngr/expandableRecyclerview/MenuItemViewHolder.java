package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import ticketzone.org.com.app_mngr.R;

public class MenuItemViewHolder extends ChildViewHolder {

    private TextView menu_name;
    private TextView menu_price;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        menu_name = itemView.findViewById(R.id.menu_name);
        menu_price = itemView.findViewById(R.id.menu_price);
    }

    public void bind(StoreMenuItem item) {
        menu_name.setText(item.menu_name);
        menu_price.setText(item.menu_price);
    }
}
