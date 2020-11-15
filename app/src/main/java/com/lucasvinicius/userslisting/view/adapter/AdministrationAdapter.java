package com.lucasvinicius.userslisting.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.view.viewholder.AdministrationViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AdministrationAdapter extends RecyclerView.Adapter<AdministrationViewHolder> {

    private List<UsersModel> mList = new ArrayList<>();

    @NonNull
    @Override
    public AdministrationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_users_row, parent, false);

        return new AdministrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdministrationViewHolder holder, int position) {
        holder.bind(this.mList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public void attachList(List<UsersModel> list) {
        this.mList = list;
        notifyDataSetChanged();
    }
}
