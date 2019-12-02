package com.example.planningpoker08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class EvaluationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_all_answer, container, false);

        List<Answer> groups= MainActivity.dh.getAllAnswers();

        ListView spinner=(ListView) v.findViewById(R.id.answerListView);

        AnswerAdapter adapter = new AnswerAdapter(this.getContext(), (ArrayList<Answer>) groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        return v;
    }
}

