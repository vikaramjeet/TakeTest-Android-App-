package com.example.taketest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taketest.models.Question;
import com.example.taketest.models.Questions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    //initialize variables
    private TextView startExam;
    private TextView reportBtn;
    private LinearLayout button1;
    private LinearLayout button2;
    private LinearLayout button3;
    private LinearLayout button4;
    private LinearLayout studyMaterials;

    private Questions questions;

    private Questions questions1;
    private Questions questions2;
    private Questions questions3;
    private Questions questions4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startExam = findViewById(R.id.start_exam);
        reportBtn = findViewById(R.id.report_btn);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        studyMaterials = findViewById(R.id.study_materials);

        loadJSONFromAsset();
        //category 1 set of questions for test -load
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions1 != null) {
                    Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                    intent.putExtra("questions", questions1);
                    intent.putExtra("title", "General Aptitude");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Questions not loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //category 2 set of questions for test -load
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions2 != null) {
                    Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                    intent.putExtra("questions", questions2);
                    intent.putExtra("title", "Verbal and Reasoning");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Questions not loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //category 3 set of questions for test -load
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions3 != null) {
                    Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                    intent.putExtra("questions", questions3);
                    intent.putExtra("title", "Current Affairs & GK");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Questions not loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //category 4 set of questions for test -load
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions4 != null) {
                    Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                    intent.putExtra("questions", questions4);
                    intent.putExtra("title", "All");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Questions not loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questions != null) {
                    Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                    intent.putExtra("questions", questions);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Questions not loaded yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //on click of report button  it navigates to reportactivity
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
        //on click of studymaterials button  it navigates to StudyMaterialsActivity
        studyMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyMaterialsActivity.class);
                startActivity(intent);
            }
        });
    }
        //loading questions from json
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            loadToClass(json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void loadToClass(String json){
        questions = new Gson().fromJson(json, Questions.class);

        ArrayList<Question> questionsArr = (ArrayList<Question>) questions.getQuestion();
        Collections.shuffle(questionsArr);

        ArrayList<Question> questionsArr1 = new ArrayList<>();
        ArrayList<Question> questionsArr2 = new ArrayList<>();
        ArrayList<Question> questionsArr3 = new ArrayList<>();
        ArrayList<Question> questionsArr4 = new ArrayList<>();

        for(int i = 0; i < questionsArr.size(); i++){
            if(questionsArr.get(i).getType().endsWith("General Aptitude")){
                questionsArr1.add(questionsArr.get(i));
            }else if(questionsArr.get(i).getType().endsWith("Verbal and Reasoning")){
                questionsArr2.add(questionsArr.get(i));
            }else if(questionsArr.get(i).getType().endsWith("Current Affairs & GK")){
                questionsArr3.add(questionsArr.get(i));
            }
            if(i < 5){
                questionsArr4.add(questionsArr.get(i));
            }
        }

        questions1 = new Questions();
        questions1.setQuestion(questionsArr1);

        questions2 = new Questions();
        questions2.setQuestion(questionsArr2);

        questions3 = new Questions();
        questions3.setQuestion(questionsArr3);

        questions4 = new Questions();
        questions4.setQuestion(questionsArr3);

        Log.e("####",questions.toString());

    }
}