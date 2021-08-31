package com.example.taketest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//A fragment class to view the instructions before test
public class InstructionFragment extends Fragment {

    public InstructionFragment() {
        // Required empty public constructor
    }
    public static InstructionFragment newInstance() {
        InstructionFragment fragment = new InstructionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_instruction, container, false);

        return view;
    }
}