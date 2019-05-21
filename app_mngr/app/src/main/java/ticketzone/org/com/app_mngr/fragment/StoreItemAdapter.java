package ticketzone.org.com.app_mngr.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import ticketzone.org.com.app_mngr.vo.StoreVO;

public class StoreItemAdapter extends FragmentStatePagerAdapter {
    private ArrayList<StoreVO> storeList; // DB의 store data

    public StoreItemAdapter(FragmentManager fm, ArrayList<StoreVO> storeList) {
        super(fm);
        this.storeList = storeList;
    }

    @Override
    public Fragment getItem(int i) {
        // store table record 하나씩 보내기
        StoreItemFragment storeItem = new StoreItemFragment();
        Bundle bundle = new Bundle();
//        bundle.putString("storeList", storeList.get(i).toString());
        bundle.putString("storeList", storeList.size() + "개");
        storeItem.setArguments(bundle);

        return storeItem;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
