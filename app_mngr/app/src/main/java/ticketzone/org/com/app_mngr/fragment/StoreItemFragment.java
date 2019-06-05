package ticketzone.org.com.app_mngr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.activity.StoreActivity;

public class StoreItemFragment extends Fragment {
    private ImageButton store_img;
    private TextView store_name;

    public StoreItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String storeList;
        JSONObject jobj;
        View view;

        view = inflater.inflate(R.layout.frag_store_item, container, false);
        store_img = view.findViewById(R.id.store_img);
        store_name = view.findViewById(R.id.store_name);
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
            Glide.with(view).load(imageUrl).override(800,1250).into(store_img);

            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

}
