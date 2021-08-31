package com.example.taketest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.taketest.models.Question;

import java.util.ArrayList;


//fragment for displaying questions
public class QuestionFragment extends Fragment {

    private static final String QUESTION = "QUESTION";
    private static final String NUM = "NUM";

    private Question question;
    private int number;

    private TextView questionTextView;
    private LinearLayout optionsLinerLayout;
    private boolean isTrue;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(Question question, int number) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION, question);
        args.putInt(NUM, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(QUESTION);
            number = getArguments().getInt(NUM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionTextView = view.findViewById(R.id.question);
        optionsLinerLayout = view.findViewById(R.id.options);
        questionTextView.setText(number+". "+question.getQuestion());

        createRadioButton((ArrayList<String>) question.getOptions(), question.getAnswer());
        return view;
    }
        //radio buttons for MCQ type questions
    private void createRadioButton(ArrayList<String> options, int ans) {
        final RadioButton[] rb = new RadioButton[options.size()];
        RadioGroup rg = new RadioGroup(getContext());
        rg.setOrientation(RadioGroup.VERTICAL);
        for(int i=0; i<options.size(); i++){
            rb[i]  = new RadioButton(getContext());
            rb[i].setText(options.get(i));
            rb[i].setId(i + 100);
            rg.addView(rb[i]);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId - 100 == ans){
                    isTrue = true;
                }else{
                    isTrue = false;
                }
                Log.e("####",checkedId+"");
            }
        });
        optionsLinerLayout.addView(rg);

    }

    public boolean isTrue() {
        return isTrue;
    }
}