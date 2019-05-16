package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class Frag1 extends Fragment {
    private String title;
    private int page;

    public static Frag1 newInstance(int page, String title) {
        Frag1 frag1 = new Frag1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        frag1.setArguments(args);
        return frag1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag1, container, false);
        ImageButton imageButton = view.findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                startActivity(intent);
            }
        });
        //EditText tvLabel = view.findViewById(R.id.editText);
        //tvLabel.setText(page + " -- " + title);

        return view;
    }
}
