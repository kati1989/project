package com.example.planningpoker08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivateQuestionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activate_question, container, false);
        List<Question> questions= MainActivity.dh.getAllQuestions();
        List<Group> groups = MainActivity.dh.getAllGroups();

        Spinner questionList= v.findViewById(R.id.questionListView);
        QuestionsAdapter adapter = new QuestionsAdapter(v.getContext(), (ArrayList<Question>) questions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        questionList.setAdapter(adapter);

        Spinner spinner=(Spinner) v.findViewById(R.id.groupSelection);
        GroupAdapter groupAdapter = new GroupAdapter(this.getContext(), (ArrayList<Group>) groups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(groupAdapter);


        return v;
    }




}

