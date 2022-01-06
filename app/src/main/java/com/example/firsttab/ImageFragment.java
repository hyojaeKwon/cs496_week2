package com.example.firsttab;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.firsttab.R;
import com.example.firsttab.ViewPagerAdapter;

public class ImageFragment extends Fragment implements ViewPagerAdapter.UpdateableFragment {

    private static final String ARG_PARAM1 = "status";
    private static final String ARG_PARAM2 = "technology";
    int status;
    String technology;

    public static ImageFragment newInstance(int status, String technology) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, status);
        args.putString(ARG_PARAM2, technology);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            status = getArguments().getInt(ARG_PARAM1);
            technology = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            Bundle args = getArguments();
            // MainActivity에서 받아온 Resource를 ImageView에 셋팅
            imageView.setImageResource(args.getInt("imgRes"));
        }

        return view;
    }

    @Override
    public void updateUI(int _status, String _technology) {
        status = _status;
        technology = _technology;

        //UI 처리
    }
}