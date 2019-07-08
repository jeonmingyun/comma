package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import ticketzone.org.com.app_mngr.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MenuTitleViewHolder extends GroupViewHolder {

    private TextView title;
    private ImageView arrow;
    private ImageButton menu_insert;

    public MenuTitleViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        arrow = itemView.findViewById(R.id.arrow);
        menu_insert = itemView.findViewById(R.id.menu_insert);
    }

    public void bind(StoreMenuTitle menuTitle) {
        title.setText(menuTitle.getTitle());
    }
    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
