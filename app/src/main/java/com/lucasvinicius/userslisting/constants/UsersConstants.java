package com.lucasvinicius.userslisting.constants;

public class UsersConstants {

    private UsersConstants() {
    }

    public static class Users {
        public static final String table_name = "Users";

        public static class Columns {
            public static final String id = "id";
            public static final String name = "name";
            public static final String email = "email";
            public static final String password = "password";
            public static final String level = "level";
        }
    }
}
