package com.example.medizap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Tips extends Fragment {
    public Tips(){}
    private RecyclerView recyclerViewTips;
    private tipsAdapter tipsAdapter;

    private List<String> factList;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tips, container, false);
        recyclerViewTips=view.findViewById(R.id.tipsrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewTips.setLayoutManager(layoutManager);
        factList=new ArrayList<>();
        factList.add("Tip 1");
        factList.add("Tip 2");
        factList.add("Tip 3");
        tipsAdapter=new tipsAdapter(factList);
        recyclerViewTips.setAdapter(tipsAdapter);


        return view;
    }
}