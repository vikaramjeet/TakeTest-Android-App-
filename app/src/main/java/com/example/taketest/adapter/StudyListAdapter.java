package com.example.taketest.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taketest.PdfViewerActivity;
import com.example.taketest.R;
import com.example.taketest.models.Study;

import java.util.ArrayList;

//Adapter to load the study links and pdf views

public class StudyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int PDF_VIEW = 1;
    public final static int LINK_VIEW = 2;

    private ArrayList<Study> studyArrayList;
    private Activity activity;

    public StudyListAdapter(ArrayList<Study> studyArrayList, Activity activity) {
        this.studyArrayList = studyArrayList;
        this.activity = activity;
    }
//Recycler view to hold study materials
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == PDF_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_item, parent, false);
            return new PdfViewHolder(view, activity);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_item, parent, false);
            return new LinkViewHolder(view, activity);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PdfViewHolder){
            PdfViewHolder pdfViewHolder = (PdfViewHolder) holder;
            pdfViewHolder.bind(studyArrayList.get(position));
        }else if(holder instanceof LinkViewHolder){
            LinkViewHolder linkViewHolder = (LinkViewHolder) holder;
            linkViewHolder.bind(studyArrayList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return studyArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(studyArrayList.get(position).getType().equals("pdf")){
            return PDF_VIEW;
        }
        return LINK_VIEW;
    }

    public static class PdfViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private Activity activity;

        public PdfViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            this.activity = activity;
            titleTextView = itemView.findViewById(R.id.title);
        }

        public void bind(Study study) {
            titleTextView.setText(study.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, PdfViewerActivity.class);
                    intent.putExtra("study",study);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public static class LinkViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private Activity activity;

        public LinkViewHolder(@NonNull View itemView, Activity activity) {
            super(itemView);
            this.activity = activity;
            titleTextView = itemView.findViewById(R.id.title);
        }

        public void bind(Study study) {
            titleTextView.setText(study.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = study.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    activity.startActivity(i);
                }
            });
        }
    }
}
