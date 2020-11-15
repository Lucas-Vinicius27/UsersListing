package com.lucasvinicius.userslisting.repository;

import android.content.Context;

import com.lucasvinicius.userslisting.database.DataBase;
import com.lucasvinicius.userslisting.model.UsersModel;

import java.util.List;

public class UsersRepository {

    private UsersDAO mUsersDAO;

    public UsersRepository(Context context) {
        DataBase db = DataBase.getInstance(context);
        this.mUsersDAO = db.usersDAO();
    }

    public UsersModel insert(UsersModel user) {
        this.mUsersDAO.insert(user);
        return login(user.getEmail(), user.getPassword());
    }

    public Boolean update(UsersModel user) {
        return this.mUsersDAO.update(user) > 0;
    }

    public Boolean delete(UsersModel user) {
        return this.mUsersDAO.delete(user) > 0;
    }

    public List<UsersModel> getListUsers(Integer level) {
        return this.mUsersDAO.getListUsers(level);
    }

    public UsersModel getUser(Integer id) {
        return this.mUsersDAO.getUser(id);
    }

    public UsersModel login(String email, String password) {
        return this.mUsersDAO.login(email, password);
    }
}
