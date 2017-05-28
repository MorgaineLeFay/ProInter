package com.example.ramon.projectprointerv;

import android.util.Log;

/**
 * Created by Ramon on 06/05/2017.
 */

public class UserEntity {

    private String email;
    private String password;
    private String idDriveRootFolder;

    public UserEntity(String user, String password){
        this.email = user;
        this.password = password;
    }

    public UserEntity(){

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String user) {
        this.email = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdDriveRootFolder() {
        return idDriveRootFolder;
    }

    public void setIdDriveRootFolder(String idDriveRootFolder) {
        this.idDriveRootFolder = idDriveRootFolder;
    }


    @Override
    public String toString() {

        String d1 =  email + " " + password + " " + idDriveRootFolder;
        return d1;
    }
}
