package com.example.firebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.Model.QuizListModel;
import com.example.firebase.R;

import java.util.List;
import java.util.zip.Inflater;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.QuziListViewHolder> {
private List<QuizListModel>quizListModelList;
private OnItemClickedListner onItemClickedListner;


    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModelList = quizListModels;
    }

    public QuizListAdapter(OnItemClickedListner onItemClickedListner){
        this.onItemClickedListner = onItemClickedListner;
    }
    @NonNull
    @Override
    public QuziListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_quiz,parent,false);
        return new QuziListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuziListViewHolder holder, int position) {
     QuizListModel model=quizListModelList.get(position);
     holder.title.setText(model.getTitle());
        Glide.with(holder.itemView).load(model.getImage()).into(holder.quizImage);
    }

    @Override
    public int getItemCount() {
        if(quizListModelList==null)
        return 0;
        else
            return quizListModelList.size();
    }

    public class QuziListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView quizImage;
        private ConstraintLayout constraintLayout;
        public QuziListViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.quizTitleList);
            quizImage=itemView.findViewById(R.id.quizImageList);
            constraintLayout=itemView.findViewById(R.id.constraintLayout);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickedListner.onItemClick(getAdapterPosition());
        }
    }
     public  interface OnItemClickedListner{
        void onItemClick(int position);
     }
}