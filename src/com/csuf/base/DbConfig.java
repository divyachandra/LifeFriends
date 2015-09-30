package com.csuf.base;

/**
 * Created by Divya on 26-09-2015.
 */
public enum DbConfig {
    LOCAL("localhost", "root", "root", "lifefriends");

    public static final DbConfig DEFAULT = LOCAL;
    public String host;
    public String username;
    public String password;
    public String database;

    DbConfig(String host, String username, String password, String database) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
    }
}
