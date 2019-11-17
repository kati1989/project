package com.example.planningpoker08;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddHobbyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddHobbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddGroupFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_group, container, false);

        return v;
    }
}
