package com.org.ticketzone.app_mem.categorieCardView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.vo.CategorieVO;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CateItemViewHolder> {

    private List<CategorieVO> cateList;

    public RecyclerViewAdapter( List<CategorieVO> cateList) {
        this.cateList = cateList;
    }

    @NonNull
    @Override
    public CateItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categorie_list_item, viewGroup, false);

        return new CateItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CateItemViewHolder categorieViewHolder, int i) {
        categorieViewHolder.cate_img .setImageResource(R.drawable.ic_launcher_background);
        categorieViewHolder.cate_title.setText(cateList.get(i).getCate_name());
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }
}
