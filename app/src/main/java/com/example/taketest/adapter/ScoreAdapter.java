package com.example.taketest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketest.R;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Integer> resultArrayList;

    public ScoreAdapter(ArrayList<Integer> resultArrayList) {
        this.resultArrayList = resultArrayList;
    }
//Recycler view to hold the list of attempts and score details
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_item, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ScoreViewHolder) holder).bind(position+1, resultArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView attempt;
        private TextView score;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            attempt = itemView.findViewById(R.id.attempt);
            score = itemView.findViewById(R.id.score);
        }
        //binding the values to views
        public void bind(int attemptVal, int scoreVal) {
            attempt.setText(String.valueOf(attemptVal));
            score.setText(String.valueOf(scoreVal));
        }
    }
}
