package ticketzone.org.com.app_mngr.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.NetworkTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.activity.StoreActivity;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

public class StoreItemFragment extends Fragment {
    private ImageButton store_img;
    private TextView store_name;
    private DBOpenHelper mDBHelper;
    private Switch switchView;
    private String license_number;
    private String store_status;
    public StoreItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        String storeList;
        JSONObject jobj;
        final View view;
        final View current_view;

        view = inflater.inflate(R.layout.frag_store_item, container, false);
        store_img = view.findViewById(R.id.store_img);
        store_name = view.findViewById(R.id.store_name);
        switchView = view.findViewById(R.id.switchView);
        mDBHelper = new DBOpenHelper(getContext());

        switchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Cursor cursor = mDBHelper.selectStore(store_name.getText().toString());
                while (cursor.moveToNext()){
                    license_number = cursor.getString(0);
                    store_status = cursor.getString(3);
                    Log.e("test",license_number);
                }
                if(isChecked){
                    mDBHelper.updateStore_status(license_number,1+"");
                    NetworkTask networkTask = new NetworkTask("store_enable"){
                    };
                    SendDataSet sds1 = new SendDataSet("license_number", license_number);
                    SendDataSet sds2 = new SendDataSet("store_status", "1");
                    networkTask.execute(sds1,sds2);
                    Log.e("on", "on");
                }else {
                    mDBHelper.updateStore_status(license_number,0+"");
                    NetworkTask networkTask = new NetworkTask("store_enable"){
                    };
                    SendDataSet sds1 = new SendDataSet("license_number", license_number);
                    SendDataSet sds2 = new SendDataSet("store_status", "0");
                    networkTask.execute(sds1,sds2);
                    Log.e("off", "off");
                }
            }
        });


        store_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StoreActivity.class);
                intent.putExtra("store_name",store_name.getText().toString());
                startActivity(intent);

            }
        });
        storeList = getArguments().getString("storeList");

        try {
            jobj = new JSONObject(storeList);
            String imageUrl = "http://15.164.115.73:8080/resources/img/" + jobj.getString("img_uploadpath") + "/" + jobj.getString("img_uuid") + "_" + jobj.getString("img_filename");
            Log.e("path", jobj.getString("img_uploadpath"));
            store_name.setText(jobj.getString("store_name"));
            Glide.with(view).load(imageUrl).centerCrop().into(store_img);

            Cursor cursor = mDBHelper.selectStore(store_name.getText().toString());
            while (cursor.moveToNext()){
                license_number = cursor.getString(0);
                store_status = cursor.getString(3);
                Log.e("test",license_number);
                Log.e("test",store_status);
                if(store_status.equals("0")){
                    Log.e("test","까꿍");
                    switchView.setChecked(false);
                }else if(store_status.equals("1")){
                    switchView.setChecked(true);
                }
            }

            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

}
