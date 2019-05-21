package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.vo.StoreVO;

public class Frag2 extends Fragment {
    private String title;
    private int page;
    private DBOpenHelper mDBHelper;
    private ActivityManager am = ActivityManager.getInstance();
    public static Frag2 newInstance(int page, String title) {
        Frag2 frag2 = new Frag2();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        frag2.setArguments(args);
        return frag2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
//        mDBHelper = new DBOpenHelper(getActivity());
        am.addActivity(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag2, container, false);
        //EditText tvLabel = (EditText) view.findViewById(R.id.editText);
        //tvLabel.setText(page + " -- " + title);
        final TextView textView = (TextView) view.findViewById(R.id.test2);
//        Cursor cursor = mDBHelper.selectAllStore();
//        ArrayList<StoreVO> StoreList = new ArrayList<>();
//        StoreVO storeVO;
//        while (cursor.moveToNext()) {
//            storeVO = new StoreVO();
//            storeVO.setStore_name(cursor.getString(6));
//            StoreList.add(storeVO);
//        }
//        textView.setText(StoreList.get(1).getStore_name());
        ImageButton imageButton = view.findViewById(R.id.imagebutton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreActivity.class);
//                intent.putExtra("store_name", textView.getText());

                startActivity(intent);
            }
        });
        return view;
    }
}
