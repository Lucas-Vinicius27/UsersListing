package com.lucasvinicius.userslisting.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.viewmodel.AdministrationViewModel;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private AdministrationViewModel mAdministrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        this.mAdministrationViewModel = new ViewModelProvider(this).get(AdministrationViewModel.class);

        this.mViewHolder.name = findViewById(R.id.edit_text_name_edit);
        this.mViewHolder.email = findViewById(R.id.edit_text_email_edit);
        this.mViewHolder.password = findViewById(R.id.edit_text_password_edit);
        this.mViewHolder.save = findViewById(R.id.button_save);

        this.setListeners();
    }

    private void setListeners() {
        this.mViewHolder.save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_save) {
            this.handleSave();
        }
    }

    private void handleSave() {
        String name = this.mViewHolder.name.getText().toString();
        String email = this.mViewHolder.email.getText().toString();
        String password = this.mViewHolder.password.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            startActivity(new Intent(EditActivity.this, HomeActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, HomeActivity.class));
        finish();
    }

    private static class ViewHolder {
        EditText name, email, password;
        Button save;
    }
}