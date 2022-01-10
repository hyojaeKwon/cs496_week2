package com.example.cs496_week2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailIdea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_idea);

        Button btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Intent 가져오기.
        Intent intent = getIntent() ;

        TextView detail_idea_name = (TextView) findViewById(R.id.detail_idea_name) ;
        String idea_name = intent.getStringExtra("Ititle") ;
        detail_idea_name.setText(idea_name) ;

        TextView detail_person_name = (TextView) findViewById(R.id.detail_person_name) ;
        String person_name = intent.getStringExtra("IauthorId") ;
        detail_person_name.setText(person_name) ;
    }
}
