package com.example.taketest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taketest.adapter.ScoreAdapter;
import com.example.taketest.db.DBHelper;

import java.util.ArrayList;
//fragment for showing list view of scores
public class ReportListFragment extends Fragment {

    private RecyclerView recyclerView;
    private DBHelper dBHelper;
    private ArrayList<Integer> resultArrayList;

    public ReportListFragment() {
        // Required empty public constructor
    }

    public static ReportListFragment newInstance() {
        ReportListFragment fragment = new ReportListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        dBHelper = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
                    //fetch scoes form db and fill in view
        resultArrayList = dBHelper.getAllResults();
        ScoreAdapter scoreAdapter = new ScoreAdapter(resultArrayList);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(scoreAdapter);

        return view;
    }
}