package com.example.taketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.taketest.adapter.StudyListAdapter;
import com.example.taketest.models.Study;
import com.example.taketest.models.StudyData;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
//Activity to view all the study materials
public class StudyMaterialsActivity extends AppCompatActivity {

    private RecyclerView studyList;
    private ArrayList<Study> studyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_materials);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Study Materials");

        studyArrayList = new ArrayList<>();
        studyList = findViewById(R.id.study_list);
        //study materials list adapter
        StudyListAdapter scoreAdapter = new StudyListAdapter(studyArrayList, this);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        studyList.setLayoutManager(manager);
        studyList.setAdapter(scoreAdapter);

        loadJSONFromAsset();
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
    //load materials from json
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("study.json");
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
//load study materials and push to arraylist
    public void loadToClass(String json){
        StudyData studyData = new Gson().fromJson(json, StudyData.class);
        studyArrayList.addAll(studyData.getStudy());
    }
}