package com.lucasvinicius.userslisting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lucasvinicius.userslisting.constants.LevelConstants;
import com.lucasvinicius.userslisting.model.UsersModel;
import com.lucasvinicius.userslisting.repository.UsersRepository;

import java.util.List;

public class AdministrationViewModel extends AndroidViewModel {

    private UsersRepository mUsersRepository;
    private MutableLiveData<List<UsersModel>> mUsersList = new MutableLiveData<>();
    public LiveData<List<UsersModel>> listLiveDataUsers = this.mUsersList;

    public AdministrationViewModel(@NonNull Application application) {
        super(application);
        this.mUsersRepository = new UsersRepository(application.getApplicationContext());
    }

    public void getList(Integer filter) {
        if (filter.equals(LevelConstants.administrator)) {
            this.mUsersList.setValue(this.mUsersRepository.getListUsers(filter));
        } else if (filter.equals(LevelConstants.director)) {
            this.mUsersList.setValue(this.mUsersRepository.getListUsers(filter));
        } else {
            this.mUsersList.setValue(this.mUsersRepository.getListUsers(filter));
        }
    }
}