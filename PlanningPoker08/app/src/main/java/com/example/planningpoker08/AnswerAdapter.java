package com.example.planningpoker08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerAdapter extends ArrayAdapter<Answer> {
    public AnswerAdapter(Context context, ArrayList<Answer> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Answer group = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_answer, parent, false);
        }
        // Lookup view for data population
        TextView textViewQuestion = (TextView) convertView.findViewById(R.id.questionText);
        TextView textViewUser = (TextView) convertView.findViewById(R.id.userText);
        TextView textViewNote = (TextView) convertView.findViewById(R.id.voteText);

        // Populate the data into the template view using the data object
        String questionText = MainActivity.dh.getQuestion(group.getQuestionId()).getDescription();
        String userName = MainActivity.dh.getUser(group.getUserId()).getName();

        textViewQuestion.setText(questionText);
        textViewUser.setText(userName);
        textViewNote.setText(group.getNote());
        // Return the completed view to render on screen
        return convertView;
    }
}
