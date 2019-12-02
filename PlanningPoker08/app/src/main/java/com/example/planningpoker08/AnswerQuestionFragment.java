package com.example.planningpoker08;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AnswerQuestionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_answer_question, container, false);

        TextView textView = v.findViewById(R.id.textViewActiveQuestion);


        SharedPreferences shared = v.getContext().getSharedPreferences("loginPreferences", MODE_PRIVATE);
        String userName = (shared.getString("name", ""));
        User user = MainActivity.dh.getUser(userName);

        List<Group> groupList = MainActivity.dh.getAllGroups();


        Group group = groupList.stream().filter(item -> item.getId() == user.getGroupId()).findFirst().get();
        List<Question> questionList = MainActivity.dh.getAllQuestions();

        Question question = questionList.stream().filter(item -> item.getId() == group.getQuestionId()).findFirst().get();

        textView.setText(question.getDescription());

        v.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(1);
            }
        });
        v.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(2);
            }
        });
        v.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(3);
            }
        });
        v.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(5);
            }
        });
        v.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(8);
            }
        });
        v.findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(13);
            }
        });
        v.findViewById(R.id.button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(21);
            }
        });
        v.findViewById(R.id.button34).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(34);
            }
        });
        v.findViewById(R.id.buttonCoffe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(-1);
            }
        });

        return v;
    }

    static int vote=0;

    public void voteWhat(int note){
        vote=note;
    }
}
