package com.org.ticketzone.app_mem.expandableRecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import com.org.ticketzone.app_mem.vo.StoreMenuVO;

public class MenuItem implements Parcelable {

    public final String menu_name;
    public final String menu_price;

    public MenuItem(StoreMenuVO storeMenuVO) {
        this.menu_name = storeMenuVO.getMenu_name().split("-")[1];
        this.menu_price = " " + storeMenuVO.getMenu_price() + "원";
    }

    protected MenuItem(Parcel in) {
        menu_name = in.readString();
        menu_price = in.readString();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
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
    }
}
