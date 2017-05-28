package com.example.ramon.projectprointerv.activities.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.ramon.projectprointerv.R;

/**
 * Created by Ramon on 10/05/2017.
 */

public class CadastroActivity extends Activity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cadastro_layout);


    }



    public void onClickCreateUser(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
