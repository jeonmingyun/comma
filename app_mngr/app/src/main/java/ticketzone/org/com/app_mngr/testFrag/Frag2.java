package ticketzone.org.com.app_mngr.testFrag;


import android.content.Intent;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.activity.StoreActivity;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

import android.widget.EditText;


public class Frag2 extends Fragment {
    private String title;
    private int page;

    private DBOpenHelper mDBHelper;



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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag2, container, false);
        //EditText tvLabel = (EditText) view.findViewById(R.id.editText);
        //tvLabel.setText(page + " -- " + title);

        return view;
    }
}
