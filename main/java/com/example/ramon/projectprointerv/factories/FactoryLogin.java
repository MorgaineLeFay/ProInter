package com.example.ramon.projectprointerv.factories;

import com.example.ramon.projectprointerv.UserEntity;

/**
 * Created by Ramon on 06/05/2017.
 */

public class FactoryLogin {

    private static UserEntity userEntity;

    public static UserEntity getSingleInstanceUser(){

        if(userEntity == null){
            userEntity = new UserEntity();

        }else{
            return userEntity;
        }

        return userEntity;
    }
}
