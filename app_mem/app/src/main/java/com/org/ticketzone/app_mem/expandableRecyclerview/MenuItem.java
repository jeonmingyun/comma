package com.org.ticketzone.app_mem.expandableRecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {

    public final String name;

    public MenuItem(String name) {
        this.name = name;
    }

    protected MenuItem(Parcel in) {
        name = in.readString();
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
        dest.writeString(name);
    }
}
