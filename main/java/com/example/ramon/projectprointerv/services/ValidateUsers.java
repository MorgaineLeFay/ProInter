package com.example.ramon.projectprointerv.services;

import com.example.ramon.projectprointerv.UserEntity;
import com.example.ramon.projectprointerv.factories.FactoryLogin;

/**
 * Created by Ramon on 08/05/2017.
 */

public class ValidateUsers {

    UserEntity userEntity = FactoryLogin.getSingleInstanceUser();
    private String email;
    private String password;

    public ValidateUsers(String login, String password){
        this.email = login;
        this.password = password;
    }

    public boolean validateUsers(){
        if(userEntity.getEmail().equals(this.email)
                && userEntity.getPassword().equals(this.password)){
            return true;
        }

        return false;
    }
}
