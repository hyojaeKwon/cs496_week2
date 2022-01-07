package com.example.cs496_week2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class UserFragment extends Fragment implements ViewPagerAdapter.UpdateableFragment {

    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "description";
    private static final String ARG_PARAM3 = "language";
    private static final String ARG_PARAM4 = "gitAddr";
    String name;
    String description;
    HashSet<String> language;
    String gitAddr;

    ScaleAnimation scaleAnimation;
    BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다
    CompoundButton button_favorite;

    public static UserFragment newInstance(String name, String description, HashSet<String> language, String gitAddr) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, description);
        args.putSerializable(ARG_PARAM3, language);
        args.putString(ARG_PARAM4, gitAddr);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            description = getArguments().getString(ARG_PARAM2);
            language = (HashSet)getArguments().getSerializable(ARG_PARAM3);
            gitAddr = getArguments().getString(ARG_PARAM4);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        TextView nameView = view.findViewById(R.id.nameView);
        TextView descriptionView = view.findViewById(R.id.descriptionView);
        TextView gitAddrView = view.findViewById(R.id.gitAddrView);

        if (getArguments() != null) {
            Bundle args = getArguments();
            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            //imageView.setImageResource(args.getInt("imgRes"));
            nameView.setText(args.getString("name"));
            descriptionView.setText(args.getString("description"));
            gitAddrView.setText(args.getString("gitAddr"));
        }

        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        button_favorite = view.findViewById(R.id.button_favorite);

        button_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                compoundButton.startAnimation(scaleAnimation);
            }
        });

        return view;
    }

    @Override
    public void updateUI(String _name, String _description, HashSet<String> _language, String _gitAddr) {
        name = _name;
        description = _description;
        language = _language;
        gitAddr = _gitAddr;

        //UI 처리
    }
}