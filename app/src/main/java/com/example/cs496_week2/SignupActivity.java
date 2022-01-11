package com.example.cs496_week2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.*;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Button buttonType = (Button) findViewById(R.id.join_Type);
        final Button buttonLang1 = (Button) findViewById(R.id.Lang1);
        final Button buttonLang2 = (Button) findViewById(R.id.Lang2);
        final Button buttonLang3 = (Button) findViewById(R.id.Lang3);

        //type선택하는 dialog생성
        buttonType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlterDialog.Builder dig = new Alterdialog.Builder(this);
            }

            public
        });
    }
}