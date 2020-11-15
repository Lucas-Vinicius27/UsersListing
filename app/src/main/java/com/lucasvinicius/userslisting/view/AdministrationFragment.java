package com.lucasvinicius.userslisting.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.constants.LevelConstants;
import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.view.adapter.AdministrationAdapter;
import com.lucasvinicius.userslisting.viewmodel.AdministrationViewModel;

import java.util.List;

public class AdministrationFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private AdministrationViewModel mAdministrationViewModel;
    private AdministrationAdapter mAdministrationAdapter = new AdministrationAdapter();
    private Integer mFilter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mAdministrationViewModel = new ViewModelProvider(this).get(AdministrationViewModel.class);

        View root = inflater.inflate(R.layout.fragment_administration, container, false);

        this.mViewHolder.textView = root.findViewById(R.id.text_view_label);
        this.mViewHolder.recyclerView = root.findViewById(R.id.recycler_view_users);
        this.mViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mViewHolder.recyclerView.setAdapter(this.mAdministrationAdapter);

        this.observers();

        if (getArguments() != null) {
            this.mFilter = getArguments().getInt(LevelConstants.filter);
        }

        if (this.mFilter.equals(LevelConstants.guest)) {
            this.mViewHolder.textView.setText(R.string.text_view_guest_area);
        } else if (this.mFilter.equals(LevelConstants.director)) {
            this.mViewHolder.textView.setText(R.string.text_view_board_area);
        } else {
            this.mViewHolder.textView.setText(R.string.text_view_administrative_area);
        }

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mAdministrationViewModel.getList(this.mFilter);
    }

    private void observers() {
        this.mAdministrationViewModel.listLiveDataUsers.observe(getViewLifecycleOwner(), new Observer<List<UsersModel>>() {
            @Override
            public void onChanged(List<UsersModel> listUsersModels) {
                mAdministrationAdapter.attachList(listUsersModels);
            }
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
    }
}