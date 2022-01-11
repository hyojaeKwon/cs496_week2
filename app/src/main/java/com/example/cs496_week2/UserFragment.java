package com.example.cs496_week2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements ViewPagerAdapter.UpdateableFragment {

    private static final String ARG_PARAM1 = "Uid";
    private static final String ARG_PARAM2 = "Uname";
    private static final String ARG_PARAM3 = "Usay";
    private static final String ARG_PARAM4 = "github";
    private static final String ARG_PARAM5 = "language";
    private static final String ARG_PARAM6 = "Utype";
    String Uid;
    String Uname;
    String Usay;
    String github;
    HashSet<String> language;
    int Utype;

    ScaleAnimation scaleAnimation;
    BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다
    CompoundButton button_favorite;

    public static UserFragment newInstance(String Uid, String Uname, String Usay, String github, HashSet<String> language, int Utype) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, Uid);
        args.putString(ARG_PARAM2, Uname);
        args.putString(ARG_PARAM3, Usay);
        args.putString(ARG_PARAM4, github);
        args.putSerializable(ARG_PARAM5, language);
        args.putInt(ARG_PARAM6, Utype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Uid = getArguments().getString(ARG_PARAM1);
            Uname = getArguments().getString(ARG_PARAM2);
            Usay = getArguments().getString(ARG_PARAM3);
            github = getArguments().getString(ARG_PARAM4);
            language = (HashSet) getArguments().getSerializable(ARG_PARAM5);
            Utype = getArguments().getInt(ARG_PARAM6);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_profile, container, false);

        TextView nameView = view.findViewById(R.id.nameView);
        TextView descriptionView = view.findViewById(R.id.descriptionView);
        CircleImageView lang1 = view.findViewById(R.id.lang1);
        CircleImageView lang2 = view.findViewById(R.id.lang2);
        CircleImageView lang3 = view.findViewById(R.id.lang3);
        CardView cardView = (CardView)view.findViewById(R.id.cardview_member);

        if (getArguments() != null) {
            Bundle args = getArguments();
            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            //imageView.setImageResource(args.getInt("imgRes"));
            nameView.setText(args.getString("Uname"));
            descriptionView.setText(args.getString("Usay"));
            int Utype = args.getInt("Utype");

            HashSet<String> lang = (HashSet)args.getSerializable("language");
            int i = 1;
            for(String l: lang) {
                if(i == 1) lang1.setImageResource(getImage(l));
                else if(i == 2) lang2.setImageResource(getImage(l));
                else if(i == 3) lang3.setImageResource(getImage(l));
                i++;
            }

            if(Utype == 1) cardView.setCardBackgroundColor(Color.RED);
            else if(Utype == 2) cardView.setCardBackgroundColor(Color.RED);
            else if(Utype == 3) cardView.setCardBackgroundColor(Color.RED);
            else cardView.setCardBackgroundColor(Color.RED);
        }

        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        button_favorite = view.findViewById(R.id.btn_profile_like);
        button_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                compoundButton.startAnimation(scaleAnimation);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailProfile.class);

                intent.putExtra("Uid", Uid);

                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void updateUI(String _Uid, String _Uname, String _Usay, String _github, HashSet<String> _language, int _Utype) {
        Uid = _Uid;
        Uname = _Uname;
        Usay = _Usay;
        github = _github;
        language = _language;
        Utype = _Utype;

        //UI 처리
    }

    public int getImage(String lang) {
        if(lang.equals("Python")) return R.drawable.python;
        else if(lang.equals("Java")) return R.drawable.java;
        else if(lang.equals("C")) return R.drawable.c;
        else if(lang.equals("C++")) return R.drawable.cpp;
        else if(lang.equals("Kotlin")) return R.drawable.kotlin;
        else return R.drawable.question_mark;
    }
}