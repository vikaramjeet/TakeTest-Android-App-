package com.example.taketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.taketest.models.Study;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;
    private Study study;
    //Activity class to display study materials and pdf links
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfView);

        if(getIntent() != null && getIntent().hasExtra("study")){
            study = (Study) getIntent().getSerializableExtra("study");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(study.getTitle());

            pdfView.fromAsset(study.getLink())
                    .load();
        }

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
}