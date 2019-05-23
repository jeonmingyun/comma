package ticketzone.org.com.app_mngr.testFrag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ticketzone.org.com.app_mngr.R;

public class Frag3 extends Fragment {
    private String title;
    private int page;




    public static Frag3 newInstance(int page, String title) {
        Frag3 frag3 = new Frag3();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        frag3.setArguments(args);
        return frag3;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag3, container, false);
        //EditText tvLabel = (EditText) view.findViewById(R.id.editText);
        //tvLabel.setText(page + " -- " + title);

        return view;
    }
}
