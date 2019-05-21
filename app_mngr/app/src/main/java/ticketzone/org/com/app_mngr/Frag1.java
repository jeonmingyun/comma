package ticketzone.org.com.app_mngr;

import android.database.Cursor;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.vo.StoreVO;

import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class Frag1 extends Fragment {
    private String title;
    private int page;
    private DBOpenHelper mDBHelper;
    private ActivityManager am = ActivityManager.getInstance();
    public static Frag1 newInstance(int page, String title) {
        Frag1 frag1 = new Frag1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        frag1.setArguments(args);
        return frag1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        am.addActivity(getActivity());
        mDBHelper = new DBOpenHelper(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);

        final TextView textView = (TextView) view.findViewById(R.id.test);
//        Cursor cursor = mDBHelper.selectAllStore();
//        ArrayList<StoreVO> StoreList = new ArrayList<>();
//        StoreVO storeVO;
//        while (cursor.moveToNext()) {
//            storeVO = new StoreVO();
//            storeVO.setStore_name(cursor.getString(6));
//            StoreList.add(storeVO);
//        }
        textView.setText(title);

        ImageButton imageButton = view.findViewById(R.id.imagebutton);
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
