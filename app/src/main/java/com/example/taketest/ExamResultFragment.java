package com.example.taketest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taketest.models.Questions;

public class ExamResultFragment extends Fragment {

    private static final String ARG_QUESTIONS = "ARG_QUESTIONS";
    private static final String ARG_ANS_CORRECT = "ARG_ANS_CORRECT";
    private static final String ARG_TIME_USED = "ARG_TIME_USED";

    private Questions questions;
    private int correctAnsCount;
    private String timeUser;

    private TextView questionCountTextView;
    private TextView correctAnswersTextView;
    private TextView scoreTextView;
    private TextView timeTextView;

    private LinearLayout shareResult;



    public ExamResultFragment() {
        // Required empty public constructor
    }
        //Fragment which displays the the result page with number of questions, correct answers and score
    public static ExamResultFragment newInstance(Questions questions, int correctAnsCount, String timeUser) {
        ExamResultFragment fragment = new ExamResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTIONS, questions);
        args.putInt(ARG_ANS_CORRECT, correctAnsCount);
        args.putString(ARG_TIME_USED, timeUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questions = (Questions) getArguments().getSerializable(ARG_QUESTIONS);
            correctAnsCount = getArguments().getInt(ARG_ANS_CORRECT);
            timeUser = getArguments().getString(ARG_TIME_USED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam_result, container, false);
        questionCountTextView = view.findViewById(R.id.question_count);
        correctAnswersTextView = view.findViewById(R.id.correct_answers);
        scoreTextView = view.findViewById(R.id.score);
        timeTextView = view.findViewById(R.id.time);
        shareResult = view.findViewById(R.id.share_result);

        questionCountTextView.setText("Number of questions : "+questions.getQuestion().size());
        correctAnswersTextView.setText("Correct Answers : "+correctAnsCount);
        scoreTextView.setText("Score : "+correctAnsCount*5);
        timeTextView.setText("Time : "+timeUser);


        //Share the score to social media

        shareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = "Test Score \n\n";
                msg = msg + "➡️ Number of questions : "+questions.getQuestion().size()+"\n";
                msg = msg + "➡️ Correct Answers : "+correctAnsCount+"\n";
                msg = msg + "➡️ Score : "+correctAnsCount*5;

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");

                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        return view;
    }
}