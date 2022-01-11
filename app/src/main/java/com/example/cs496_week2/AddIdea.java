package com.example.cs496_week2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddIdea extends AppCompatActivity {

    private String idea_name;
    private String idea_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_idea);

        EditText set_idea_name = findViewById(R.id.set_idea_name);
        set_idea_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (set_idea_name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "아이디어 명을 입력해주세요", Toast.LENGTH_LONG)
                            .show();
                } else {
                    idea_name = set_idea_name.getText().toString();
                }
            }
        });

        EditText set_idea_detail = findViewById(R.id.set_idea_detail);
        set_idea_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (set_idea_detail.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "아이디어 명을 입력해주세요", Toast.LENGTH_LONG)
                            .show();
                } else {
                    idea_detail = set_idea_detail.getText().toString();
                }
            }
        });

        Button select_skill = findViewById(R.id.select_skill);
        select_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<String> ListItems = new ArrayList<>();
                ListItems.add("Python");
                ListItems.add("Java");
                ListItems.add("C++");
                ListItems.add("Node.JS");
                final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

                final List SelectedItems = new ArrayList();


                AlertDialog.Builder builder = new AlertDialog.Builder(AddIdea.this);
                builder.setTitle("필요한 기술을 선택하세요");
                builder.setMultiChoiceItems(items, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    //사용자가 체크한 경우 리스트에 추가
                                    SelectedItems.add(which);
                                } else if (SelectedItems.contains(which)) {
                                    //이미 리스트에 들어있던 아이템이면 제거
                                    SelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        });
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String msg = "";
                                for (int i = 0; i < SelectedItems.size(); i++) {
                                    int index = (int) SelectedItems.get(i);

                                    msg = msg + "\n" + (i + 1) + " : " + ListItems.get(index);
                                }
                                Toast.makeText(getApplicationContext(),
                                        "Total " + SelectedItems.size() + " Items Selected.\n" + msg, Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });

        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
