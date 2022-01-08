package com.example.cs496_week2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IdeaRecyclerviewAdapter extends RecyclerView.Adapter<IdeaRecyclerviewAdapter.ViewHolder> {

    private ArrayList<IdeaItem> mData = null;

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
        TextView idea_name_view;
        TextView person_name_view;
        TextView count_view;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            idea_name_view = itemView.findViewById(R.id.idea_name);
            person_name_view = itemView.findViewById(R.id.idea_person_name);
            count_view = itemView.findViewById(R.id.idea_like_count);

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
    IdeaRecyclerviewAdapter(ArrayList<IdeaItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public IdeaRecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.idea_item, parent, false);
        IdeaRecyclerviewAdapter.ViewHolder vh = new IdeaRecyclerviewAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(IdeaRecyclerviewAdapter.ViewHolder holder, int position) {
        String idea_name = mData.get(position).getIdeaName();
        holder.idea_name_view.setText(idea_name);

        String person_name = mData.get(position).getPersonName();
        holder.person_name_view.setText(person_name);

        int count = mData.get(position).getCount();
        holder.count_view.setText(Integer.toString(count));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}