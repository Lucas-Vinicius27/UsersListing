package com.lucasvinicius.userslisting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.repository.UsersRepository;

public class RegisterViewModel extends AndroidViewModel {

    private UsersRepository mUsersRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.mUsersRepository = new UsersRepository(application.getApplicationContext());
    }

    public UsersModel save(UsersModel user) {
        return this.mUsersRepository.insert(user);
    }
}
