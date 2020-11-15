package com.lucasvinicius.userslisting.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.lucasvinicius.userslisting.constants.UsersConstants;

@Entity(tableName = UsersConstants.Users.table_name)
public class UsersModel {

    @ColumnInfo(name = UsersConstants.Users.Columns.id)
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = UsersConstants.Users.Columns.name)
    private String name;

    @ColumnInfo(name = UsersConstants.Users.Columns.password)
    private String password;

    @ColumnInfo(name = UsersConstants.Users.Columns.email)
    private String email;

    @ColumnInfo(name = UsersConstants.Users.Columns.level)
    private Integer level;

    @Ignore
    public UsersModel(Integer id, String name, String password, String email, Integer level) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setLevel(level);
    }

    public UsersModel(String name, String password, String email, Integer level) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setLevel(level);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
