package com.lucasvinicius.userslisting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.repository.UsersRepository;

public class LoginViewModel extends AndroidViewModel {

    private UsersRepository mUsersRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.mUsersRepository = new UsersRepository(application.getApplicationContext());
    }

    public UsersModel login(String email, String password) {
        return this.mUsersRepository.login(email, password);
    }
}
