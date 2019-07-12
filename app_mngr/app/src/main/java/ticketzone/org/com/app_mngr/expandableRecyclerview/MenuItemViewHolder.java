package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.activity.MainActivity;

public class MenuItemViewHolder extends ChildViewHolder {

    private TextView menu_name;
    private TextView menu_price;
//    private ImageButton menu_update;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        menu_name = itemView.findViewById(R.id.menu_name);
        menu_price = itemView.findViewById(R.id.menu_price);
//        menu_update = itemView.findViewById(R.id.menu_update);
    }

    public void bind(StoreMenuItem item) {
        menu_name.setText(item.menu_name);
        menu_price.setText(item.menu_price);
    }

}
