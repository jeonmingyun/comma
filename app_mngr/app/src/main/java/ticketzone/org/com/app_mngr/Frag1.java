package ticketzone.org.com.app_mngr;

<<<<<<< HEAD
import android.database.Cursor;
=======
import android.content.Intent;
>>>>>>> 826c1d63d93e27cfffd99414857efd8a6fcb2c18
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.TextView;

import org.w3c.dom.Text;

import ticketzone.org.com.app_mngr.db.DBOpenHelper;
=======
import android.widget.ImageButton;
>>>>>>> 826c1d63d93e27cfffd99414857efd8a6fcb2c18

public class Frag1 extends Fragment {
    private String title;
    private int page;
    private DBOpenHelper mDBHelper;
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
        mDBHelper = new DBOpenHelper(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag1, container, false);
<<<<<<< HEAD
        TextView textView = (TextView)view.findViewById(R.id.test);
        Cursor cursor = mDBHelper.selectAllStore();
        String text;
        while (cursor.moveToNext()){
            textView.setText(cursor.getString(6));
        }

=======
        ImageButton imageButton = view.findViewById(R.id.imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), StoreActivity.class);
                startActivity(intent);
            }
        });
>>>>>>> 826c1d63d93e27cfffd99414857efd8a6fcb2c18
        //EditText tvLabel = view.findViewById(R.id.editText);
        //tvLabel.setText(page + " -- " + title);
//        TextView textView = (TextView)view.findViewById(R.id.test);
//        textView.setText("나며눅");
        return view;
    }
}
