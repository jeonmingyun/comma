package ticketzone.org.com.app_mngr.expandableRecyclerview;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class StoreMenuTitle extends ExpandableGroup<StoreMenuItem> {
    public StoreMenuTitle(String title, List<StoreMenuItem> items) {
        super(title, items);
    }
}
