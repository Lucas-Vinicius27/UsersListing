package com.lucasvinicius.userslisting.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.model.UsersModel;

public class AdministrationViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;
    private TextView mTextLevel;

    public AdministrationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mTextName = itemView.findViewById(R.id.text_view_name);
        this.mTextLevel = itemView.findViewById(R.id.text_view_level);
    }

    public void bind(UsersModel usersModel) {
        this.mTextName.setText(usersModel.getName());
        switch (usersModel.getLevel()) {
            case 1:
                this.mTextLevel.setText(R.string.radio_button_guest);
                break;
            case 2:
                this.mTextLevel.setText(R.string.radio_button_director);
                break;
            case 3:
                this.mTextLevel.setText(R.string.radio_button_administrator);
                break;
            default:
                this.mTextLevel.setText(usersModel.getLevel());
        }
    }
}
