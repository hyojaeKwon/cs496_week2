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
    String Usay;
    String Uideaid;
    String github;
    String Llang1;
    String Llang2;
    String Llang3;

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
                Usay = obj.getString("Usay");
                String Utype = obj.getString("Utype");
                Uideaid = obj.getString("Uideaid");
                String Ustack = obj.getString("Ustack");
                github = obj.getString("github");
                Llang1 = obj.getString("Llang1");
                Llang2 = obj.getString("Llang2");
                Llang3 = obj.getString("Llang3");
                break;
            }
        } catch (IOException e) {

        } catch (JSONException e) {
        }

        TextView Uname_view = (TextView) findViewById(R.id.detail_Uname);
        Uname_view.setText(Uname);

        TextView Usay_view = (TextView) findViewById(R.id.detail_Usay);
        Usay_view.setText(Usay);

        TextView idea_view = (TextView) findViewById(R.id.detail_idea);
        AssetManager assetManager2 = getResources().getAssets();
        try {
            InputStream inputStream = assetManager2.open("idea.json");
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
            JSONArray jArray = jObject.getJSONArray("idea");

            // Fragment로 넘길 Image Resource
            int i = 0;
            for (; i < jArray.length(); ++i) {
                JSONObject obj = jArray.getJSONObject(i);
                int cur_idea_id = obj.getInt("Iid");
                if(cur_idea_id != Integer.parseInt(Uideaid)) continue;
                String Ititle = obj.getString("Ititle");
                idea_view.setText(Ititle);
                break;
            }

            if(i == jArray.length()) idea_view.setText("없음");
        } catch (IOException e) {

        } catch (JSONException e) {
        }

        TextView github_view = (TextView) findViewById(R.id.detail_github);
        github_view.setText("github.com/"+github);

        TextView lang1_view = (TextView) findViewById(R.id.detail_lang1);
        lang1_view.setText(Llang1);
        lang1_view.setCompoundDrawablesWithIntrinsicBounds(0, getImage(Llang1), 0, 0);

        TextView lang2_view = (TextView) findViewById(R.id.detail_lang2);
        lang2_view.setText(Llang2);
        lang2_view.setCompoundDrawablesWithIntrinsicBounds(0, getImage(Llang2), 0, 0);

        TextView lang3_view = (TextView) findViewById(R.id.detail_lang3);
        lang3_view.setText(Llang3);
        lang3_view.setCompoundDrawablesWithIntrinsicBounds(0, getImage(Llang3), 0, 0);

        idea_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetManager assetManager = getResources().getAssets();
                try {
                    InputStream inputStream = assetManager.open("idea.json");
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
                    JSONArray jArray = jObject.getJSONArray("idea");

                    // Fragment로 넘길 Image Resource
                    for (int i = 0; i < jArray.length(); ++i) {
                        JSONObject obj = jArray.getJSONObject(i);
                        int cur_idea_id = obj.getInt("Iid");
                        if(cur_idea_id != Integer.parseInt(Uideaid)) continue;
                        String Ititle = obj.getString("Ititle");
                        String Idescription = obj.getString("Idescription");
                        String IauthorId = obj.getString("IauthorId");

                        Intent intent = new Intent(DetailProfile.this, DetailIdea.class);
                        intent.putExtra("Ititle", Ititle);
                        intent.putExtra("IauthorId", IauthorId);
                        startActivity(intent);
                        break;
                    }
                } catch (IOException e) {

                } catch (JSONException e) {
                }
            }
        });

        github_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int getImage(String lang) {
        if(lang.equals("Python")) return R.drawable.drawable_top_python;
        else if(lang.equals("Java")) return R.drawable.drawable_top_java;
        else if(lang.equals("C")) return R.drawable.drawable_top_c;
        else if(lang.equals("C++")) return R.drawable.drawable_top_cpp;
        else if(lang.equals("Kotlin")) return R.drawable.drawable_top_kotlin;
        else return R.drawable.question_mark;
    }
}
