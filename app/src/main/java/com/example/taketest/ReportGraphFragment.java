package com.example.taketest;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taketest.db.DBHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
//fragment for showing graphical representation of scores
public class ReportGraphFragment extends Fragment {

    private GraphView graph;
    private DBHelper dBHelper;
    private ArrayList<Integer> resultArrayList;

    public ReportGraphFragment() {
        // Required empty public constructor
    }

    public static ReportGraphFragment newInstance() {
        ReportGraphFragment fragment = new ReportGraphFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dBHelper = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_graph, container, false);

        graph = view.findViewById(R.id.graph);

        resultArrayList = dBHelper.getAllResults();
        //draws graph by querying the db for results
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        for(int i = 0; i < resultArrayList.size(); i++){
            series.appendData(new DataPoint(i, resultArrayList.get(i)), false, resultArrayList.size());
        }

        series.setTitle("Score");
        series.setColor(Color.GREEN);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);


        graph.addSeries(series);

        return view;
    }
}