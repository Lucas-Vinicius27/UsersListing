package com.lucasvinicius.userslisting.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.lucasvinicius.userslisting.constants.UsersConstants;
import com.lucasvinicius.userslisting.model.UsersModel;

import java.util.List;

@Dao
public interface UsersDAO {

    @Insert
    Long insert(UsersModel user);

    @Update
    Integer update(UsersModel user);

    @Delete
    Integer delete(UsersModel user);

    @Query("SELECT * FROM " + UsersConstants.Users.table_name +
            " WHERE " + UsersConstants.Users.Columns.id + " = :id")
    UsersModel getUser(Integer id);

    @Query("SELECT * FROM " + UsersConstants.Users.table_name +
            " WHERE " + UsersConstants.Users.Columns.level + " = :level")
    List<UsersModel> getListUsers(Integer level);

    @Query("SELECT * FROM " + UsersConstants.Users.table_name +
            " WHERE " + UsersConstants.Users.Columns.email + " = :email AND " +
            UsersConstants.Users.Columns.password + " = :password")
    UsersModel login(String email, String password);
}
