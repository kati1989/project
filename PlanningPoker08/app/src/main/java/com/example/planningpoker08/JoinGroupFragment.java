package com.example.planningpoker08;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class JoinGroupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_join_group, container, false);

        List<Group> groups= MainActivity.dh.getAllGroups();

        Spinner spinner=(Spinner) v.findViewById(R.id.groupSelectionJoin);
        GroupAdapter adapter = new GroupAdapter(this.getContext(), (ArrayList<Group>) groups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        return v;
    }
}

