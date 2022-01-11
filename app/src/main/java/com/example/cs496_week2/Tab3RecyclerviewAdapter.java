package com.example.cs496_week2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public class Tab3RecyclerviewAdapter extends RecyclerView.Adapter<Tab3RecyclerviewAdapter.ViewHolder> {

    private ArrayList<UserInfo> mData = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tab3_name_view;
        TextView tab3_lang1_view;
        TextView tab3_lang2_view;
        TextView tab3_lang3_view;
        TextView tab3_score_view;
        ConstraintLayout tab3_item_view;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            tab3_name_view = itemView.findViewById(R.id.tab3Name);
            tab3_lang1_view = itemView.findViewById(R.id.tab3Lang1);
            tab3_lang2_view = itemView.findViewById(R.id.tab3Lang2);
            tab3_lang3_view = itemView.findViewById(R.id.tab3Lang3);
            tab3_score_view = itemView.findViewById(R.id.tab3Score);
            tab3_item_view = itemView.findViewById(R.id.tab3Item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    Tab3RecyclerviewAdapter(ArrayList<UserInfo> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public Tab3RecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.tab3_item, parent, false);
        Tab3RecyclerviewAdapter.ViewHolder vh = new Tab3RecyclerviewAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Tab3RecyclerviewAdapter.ViewHolder holder, int position) {
        String name = mData.get(position).getUname();
        holder.tab3_name_view.setText(name);

        HashSet<String> language = mData.get(position).getLanguage();
        int i = 1;
        String Lang1 = "";
        String Lang2 = "";
        String Lang3 = "";
        String temp;
        for(String lang: language) {
            if(i == 1) Lang1 = lang;
            else if(i == 2) Lang2 = lang;
            else if(i == 3) Lang3 = lang;
            i++;
        }
        if(Lang1.compareTo(Lang2) > 0) {
            temp = Lang1;
            Lang1 = Lang2;
            Lang2 = temp;
        }
        if(Lang1.compareTo(Lang3) > 0) {
            temp = Lang1;
            Lang1 = Lang3;
            Lang3 = temp;
        }
        if(Lang2.compareTo(Lang3) > 0) {
            temp = Lang2;
            Lang2 = Lang3;
            Lang3 = temp;
        }
        holder.tab3_lang1_view.setText(Lang1);
        holder.tab3_lang2_view.setText(Lang2);
        holder.tab3_lang3_view.setText(Lang3);

        int score = mData.get(position).getScore();
        holder.tab3_score_view.setText(Integer.toString(score));

        if(score == 3) holder.tab3_item_view.setBackgroundColor(Color.parseColor("#4B89DC"));
        else if(score == 2) holder.tab3_item_view.setBackgroundColor(Color.parseColor("#3CB371"));
        else if(score == 1) holder.tab3_item_view.setBackgroundColor(Color.parseColor("#ffdf3e"));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}