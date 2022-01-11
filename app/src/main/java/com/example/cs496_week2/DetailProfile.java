package com.example.cs496_week2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class DetailProfile extends AppCompatActivity {

    String Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_profile);

        Button btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Intent 가져오기.
        Intent intent = getIntent();

        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("detail_profile.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //파일읽기
            String strResult = "";
            String line;
            while ((line = reader.readLine()) != null) {
                strResult = strResult.concat(line);
            }

            // 가장 큰 JSONObject를 가져옵니다.
            JSONObject jObject = new JSONObject(strResult);
            // 배열을 가져옵니다.
            JSONArray jArray = jObject.getJSONArray("detail_profile");

            // Fragment로 넘길 Image Resource
            for (int i = 0; i < jArray.length(); ++i) {
                JSONObject obj = jArray.getJSONObject(i);
                String cur_user_id = obj.getString("Uid");
                if(!cur_user_id.equals(intent.getStringExtra("Uid"))) continue;
                String Upw = obj.getString("Upw");
                Uname = obj.getString("Uname");
                String Usay = obj.getString("Usay");
                String Utype = obj.getString("Utype");
                String Uideaid = obj.getString("Uideaid");
                String Ustack = obj.getString("Ustack");
                String github = obj.getString("github");
            }
        } catch (IOException e) {

        } catch (JSONException e) {
        }

        TextView Uname_view = (TextView) findViewById(R.id.detail_Uname);
        Uname_view.setText(Uname);
    }
}
