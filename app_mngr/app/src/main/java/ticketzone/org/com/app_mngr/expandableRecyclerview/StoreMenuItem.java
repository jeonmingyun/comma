package ticketzone.org.com.app_mngr.expandableRecyclerview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import ticketzone.org.com.app_mngr.vo.StoreMenuVO;

public class StoreMenuItem implements Parcelable {

    protected final String menu_name;
    protected final String menu_price;
    protected final String menu_code;
//    private Context context;

    public StoreMenuItem(StoreMenuVO storeMenuVO, Context context) {
        this.menu_name = storeMenuVO.getMenu_name().split("-")[1];
        this.menu_price = " " + storeMenuVO.getMenu_price() + "원";
        this.menu_code = storeMenuVO.getMenu_code();
//        this.context = context;
    }

    protected StoreMenuItem(Parcel in) {
        menu_name = in.readString();
        menu_price = in.readString();
        menu_code = in.readString();
//        context = in.readValue();
    }

    public static final Creator<StoreMenuItem> CREATOR = new Creator<StoreMenuItem>() {
        @Override
        public StoreMenuItem createFromParcel(Parcel in) {
            return new StoreMenuItem(in);
        }

        @Override
        public StoreMenuItem[] newArray(int size) {
            return new StoreMenuItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu_name);
        dest.writeString(menu_price);
        dest.writeString(menu_code);
//        dest.writeValue(context);
    }
}
