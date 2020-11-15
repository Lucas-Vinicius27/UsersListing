package com.lucasvinicius.userslisting.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.constants.LevelConstants;
import com.lucasvinicius.userslisting.constants.UsersConstants;
import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Integer mRadioId;
    private RegisterViewModel mRegisterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        this.mRegisterViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        this.mViewHolder.email = findViewById(R.id.edit_text_email);
        this.mViewHolder.password = findViewById(R.id.edit_text_password);
        this.mViewHolder.name = findViewById(R.id.edit_text_name_register);
        this.mViewHolder.radioGroup = findViewById(R.id.radio_group_acl);
        this.mViewHolder.register = findViewById(R.id.button_register);

        this.mViewHolder.register.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_register) {
            this.handleSave();
        }
    }

    private void handleSave() {
        this.mRadioId = this.mViewHolder.radioGroup.getCheckedRadioButtonId();
        this.mViewHolder.radioButton = findViewById(this.mRadioId);
        Integer level = 0;

        switch (this.mViewHolder.radioButton.getText().toString()) {
            case "Administrator":
            case "Administrador":
                level = LevelConstants.administrator;
                break;
            case "Director":
            case "Diretor":
                level = LevelConstants.director;
                break;
            case "Guest":
            case "Convidado":
                level = LevelConstants.guest;
                break;
            default:
                level = 0;
        }

        String email = this.mViewHolder.email.getText().toString().toLowerCase().trim();
        String password = this.mViewHolder.password.getText().toString();
        String name = this.mViewHolder.name.getText().toString();

        if ("".equals(email) || "".equals(password) || "".equals(name)) {
            Toast.makeText(RegisterActivity.this, R.string.missing_data, Toast.LENGTH_SHORT).show();
        } else {
            UsersModel user = new UsersModel(name, password, email, level);
            UsersModel registeredUser = this.mRegisterViewModel.save(user);

            Toast.makeText(this, R.string.successfully_registered, Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putInt(UsersConstants.Users.Columns.id, registeredUser.getId());
            bundle.putInt(UsersConstants.Users.Columns.level, registeredUser.getLevel());
            bundle.putString(UsersConstants.Users.Columns.name, registeredUser.getName());
            bundle.putString(UsersConstants.Users.Columns.email, registeredUser.getEmail());
            bundle.putString(UsersConstants.Users.Columns.password, registeredUser.getPassword());

            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtras(bundle);

            startActivity(intent);
            finish();
        }
    }

    private static class ViewHolder {
        EditText email, password, name;
        RadioGroup radioGroup;
        RadioButton radioButton;
        Button register;
    }
}