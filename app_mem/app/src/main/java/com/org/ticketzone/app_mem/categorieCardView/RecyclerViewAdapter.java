package com.org.ticketzone.app_mem.categorieCardView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.activity.MainActivity;
import com.org.ticketzone.app_mem.db.DBOpenHelper;
import com.org.ticketzone.app_mem.vo.CategorieVO;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<CateItemViewHolder> {

    private List<CategorieVO> cateList;
    private Context context;
    private DBOpenHelper mDBHelper;

    public RecyclerViewAdapter( Context context,  List<CategorieVO> cateList) {
        this.context = context;
        this.cateList = cateList;

    }

    @NonNull
    @Override
    public CateItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categorie_list_item, viewGroup, false);



        return new CateItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CateItemViewHolder categorieViewHolder, final int i) {
//        categorieViewHolder.cate_img .setImageResource(R.drawable.ic_launcher_background);
        // cate_img resourse id 얻기
        Resources res = context.getResources();
        String mDrawableName = cateList.get(i).getCate_icon();
        int resID = res.getIdentifier(mDrawableName , "drawable", context.getPackageName());

        categorieViewHolder.cate_img .setImageResource(resID);
        categorieViewHolder.cate_title.setText(cateList.get(i).getCate_name());
        categorieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Log.e("context", i+"");
//                Toast.makeText(context,i +"",Toast.LENGTH_SHORT).show();
                mDBHelper = new DBOpenHelper(context);
                Cursor cursor = mDBHelper.selectCategorie();
                CategorieVO categorieVO;
                cateList = new ArrayList<>();

                while(cursor.moveToNext()) {
                    categorieVO = new CategorieVO();
                    categorieVO.setCate_code(cursor.getString(0));

                    cateList.add(categorieVO);
                }
                Log.e("ddd",cateList.get(0)+"");
                switch (i){
                    case 0:
                        cate(i);
                        break;
                    case 1:
                        cate(i);
                        break;
                    case 2:
                        cate(i);
                        break;
                    case 3:
                        cate(i);
                    case 4:
                        cate(i);
                    case 5:
                        cate(i);
                    case 6:
                        cate(i);
                    case 7:
                        cate(i);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }

    private void cate(int i){
        Log.e("dddd",cateList.get(i).getCate_code()+"");
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("cate",cateList.get(i).getCate_code());
        context.startActivity(intent);
    }


}
