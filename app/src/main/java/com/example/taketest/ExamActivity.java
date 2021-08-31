package com.example.taketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taketest.db.DBHelper;
import com.example.taketest.models.Question;
import com.example.taketest.models.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private long mLastClickTime = 0;

    private Questions questions;
    private ArrayList<Question> questionArrayList;
    private TextView timerTextView;
    private TextView totalQuestionsTextView;
    private FrameLayout container;
    private Button nextBtn;
    private LinearLayout timerLayout;
    private int questionIndex;
    private CountDownTimer cT;

    private int correctAnsCount;
    private String timeUser = "";

    private DBHelper dBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Exam");
        dBHelper = new DBHelper(this);


        if(getIntent() != null && getIntent().hasExtra("title")){
            setTitle(getIntent().getStringExtra("title"));
        }
        if(getIntent() != null && getIntent().hasExtra("questions")){
            questions = (Questions) getIntent().getSerializableExtra("questions");
            questionArrayList = (ArrayList<Question>) questions.getQuestion();
        }
        //set timer layout with timer view
        timerLayout = findViewById(R.id.timer_layout);
        timerTextView = findViewById(R.id.timer);
        container = findViewById(R.id.container);
        nextBtn = findViewById(R.id.next_btn);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        //shuffle questions
        Collections.shuffle(questionArrayList);
        //adding instruction fragment
        InstructionFragment instructionFragment = InstructionFragment.newInstance();
        addFragment(instructionFragment);


        //Fragment section for populating questions for practice test

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Fragment currentFragment = getTopFragment();
                if(currentFragment instanceof ExamResultFragment) {
                    finish();
                    return;
                }
                if(currentFragment instanceof QuestionFragment) {
                    QuestionFragment questionFragmentTop = (QuestionFragment) currentFragment;
                    if(questionFragmentTop.isTrue()){
                        correctAnsCount++;
                    }
                    Log.e("####", questionFragmentTop.isTrue() + "");
                }

                if(questionIndex < questionArrayList.size()){
                    if(questionIndex == 0) {
                        startTimer();
                    }
                    QuestionFragment questionFragment = QuestionFragment.newInstance(questions.getQuestion().get(questionIndex), questionIndex+1);
                    addFragment(questionFragment);
                    questionIndex++;
                }else{
                    dBHelper.insertResult(correctAnsCount*5);
                    cT.cancel();
                    timerTextView.setVisibility(View.GONE);
                    totalQuestionsTextView.setVisibility(View.GONE);
                    nextBtn.setText("Finish");

                    ExamResultFragment examResultFragment = ExamResultFragment.newInstance(questions, correctAnsCount, timeUser);
                    addFragment(examResultFragment);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Fragment getTopFragment() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        Fragment top = null;
        for (int i = fragmentList.size() -1; i>=0 ; i--) {
            top = (Fragment) fragmentList.get(i);
            if (top != null) {
                return top;
            }
        }
        return top;
    }

    private void addFragment(Fragment fragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }


    //starttimer method provides a valid timer setup for test
    private void startTimer(){

        nextBtn.setText("Next");
        timerLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        totalQuestionsTextView.setText("Total Questions: "+String.valueOf(questionArrayList.size()));
        totalQuestionsTextView.setVisibility(View.VISIBLE);
        cT =  new CountDownTimer(60*1000, 1000) {

            public void onTick(long millisUntilFinished) {

                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                timeUser = v+":"+String.format("%02d",va);
                timerTextView.setText("Time: " +v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                Toast.makeText(ExamActivity.this,"Time up", Toast.LENGTH_SHORT).show();
                timerTextView.setVisibility(View.GONE);
                totalQuestionsTextView.setVisibility(View.GONE);
                nextBtn.setText("Finish");

                ExamResultFragment examResultFragment = ExamResultFragment.newInstance(questions, correctAnsCount, timeUser);
                addFragment(examResultFragment);
            }
        };
        cT.start();
    }
}