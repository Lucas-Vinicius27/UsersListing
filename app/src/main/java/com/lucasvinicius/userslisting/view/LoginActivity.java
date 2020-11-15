package com.lucasvinicius.userslisting.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.lucasvinicius.userslisting.R;
import com.lucasvinicius.userslisting.constants.UsersConstants;
import com.lucasvinicius.userslisting.helper.BiometricHelper;
import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.viewmodel.LoginViewModel;

import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.email = findViewById(R.id.edit_text_email_main);
        this.mViewHolder.password = findViewById(R.id.edit_text_password_main);
        this.mViewHolder.login = findViewById(R.id.button_login);
        this.mViewHolder.register = findViewById(R.id.button_register_main);

        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.mViewHolder.register.setOnClickListener(this);
        this.mViewHolder.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            String email = this.mViewHolder.email.getText().toString().toLowerCase().trim();
            String password = this.mViewHolder.password.getText().toString();
            UsersModel user = this.verification(email, password);

            if (user == null) {
                Toast.makeText(LoginActivity.this, R.string.try_again, Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt(UsersConstants.Users.Columns.id, user.getId());
                bundle.putInt(UsersConstants.Users.Columns.level, user.getLevel());
                bundle.putString(UsersConstants.Users.Columns.name, user.getName());
                bundle.putString(UsersConstants.Users.Columns.email, user.getEmail());
                bundle.putString(UsersConstants.Users.Columns.password, user.getPassword());

                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtras(bundle);

                Boolean biometric = BiometricHelper.isAvailable(this);

                if (biometric.equals(true)) {
                    this.openAuthentication(intent);
                } else {
                    startActivity(intent);
                    finish();
                }
            }
        } else {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void openAuthentication(Intent intent) {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_authentication))
                .setNegativeButtonText(getString(R.string.cancel_authentication))
                .build();
        biometricPrompt.authenticate(info);
    }

    public UsersModel verification(String email, String password) {
        if ("".equals(email) || "".equals(password)) {
            Toast.makeText(LoginActivity.this, R.string.missing_data, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            return this.mLoginViewModel.login(email, password);
        }
    }

    private static class ViewHolder {
        EditText email, password;
        Button login, register;
    }
}