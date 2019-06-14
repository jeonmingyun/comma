package ticketzone.org.com.app_mngr.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.vo.StoreVO;

public class StoreItemAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> storeList; // DB의 store data




    public StoreItemAdapter(FragmentManager fm, ArrayList<String> storeList) {
        super(fm);
        this.storeList = storeList;
    }

    @Override
    public Fragment getItem(int i) {
        // store table record 하나씩 보내기
        StoreItemFragment storeItem = new StoreItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("storeList", storeList.get(i));
        storeItem.setArguments(bundle);

        return storeItem;
    }

    @Override
    public int getCount() {
        return storeList.size();
    }
}
