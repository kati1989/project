package com.example.planningpoker08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView dateText;
    public static DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.add(R.id.activity_fragment,new LoginFragment());
        frag_trans.addToBackStack("Group");
        frag_trans.commit();
        dh = new DatabaseHelper(this);
    }

    public void login(View v)
    {
        CheckBox remember = (CheckBox) findViewById(R.id.checkBox);
        EditText nameEdit = (EditText) findViewById(R.id.nameText);
        EditText emailEdit = (EditText) findViewById(R.id.emailText);
        EditText passwordEdit = (EditText) findViewById(R.id.password);
        TextView birthEdit = (TextView) findViewById(R.id.dateText);

        SharedPreferences sharedPref = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


            editor.putString("name", nameEdit.getText().toString());
            editor.putString("email", emailEdit.getText().toString());
            editor.putString("password", passwordEdit.getText().toString());
            editor.putString("birthDate", birthEdit.getText().toString());
            editor.commit();


        if (nameEdit.getText().toString().equals("admin")) {
            navigateAdminMenu();
            //initDb();
        }
        else {
            navigateUserMenu();
        }
    }

    private void initDb(){

        dh.insertGroup("A");
        dh.insertGroup("B");

        dh.insertUser("user1");
        dh.insertUser("user2");
        dh.insertUser("user3");
        dh.insertUser("admin");


        dh.insertQuestion(0,"Test Question A1","","");
        dh.insertQuestion(0,"Test Question A2","","");

        dh.insertQuestion(1,"Test Question B1","","");
        dh.insertQuestion(1,"Test Question B2","","");

    }

    private void navigateAdminMenu(){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new AdminMenuFragment());
        frag_trans.addToBackStack("AdminMenu");
        frag_trans.commit();
    }

    private void navigateUserMenu(){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new UserMenuFragment());
        frag_trans.addToBackStack("UserMenu");
        frag_trans.commit();
    }

    public void addGroup(View v){
        TextView groupName = (TextView) findViewById(R.id.groupName);
        dh.insertGroup(groupName.getText().toString());
        navigateAdminMenu();
    }

    public void addQuestion(View v){
        Spinner groupName = (Spinner) findViewById(R.id.spinner);
        Group item = (Group) groupName.getSelectedItem();
        TextView question = (TextView) findViewById(R.id.questionName);
        dh.insertQuestion(item.getId() ,question.getText().toString(), "","");
        navigateAdminMenu();
    }

    public void navigateAddGroup(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new AddGroupFragment());
        frag_trans.addToBackStack("Group");
        frag_trans.commit();
    }

    public void navigateAddQuestion(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new AddQuestionFragment());
        frag_trans.addToBackStack("addQuestion");
        frag_trans.commit();
    }

    public void navigateActivateQuestion(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new ActivateQuestionFragment());
        frag_trans.addToBackStack("activateQuestion");
        frag_trans.commit();
    }

    public void navigateViewEvaluation(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new EvaluationFragment());
        frag_trans.addToBackStack("viewEvaluation");
        frag_trans.commit();
    }

    public void navigateJoinGroup(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new JoinGroupFragment());
        frag_trans.addToBackStack("joinGroup");
        frag_trans.commit();
    }

    public void navigateAnswerQuestion(View v){
        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new AnswerQuestionFragment());
        frag_trans.addToBackStack("answerQuestion");
        frag_trans.commit();
    }

    public void activateQuestion(View view) {
        Spinner questionList= findViewById(R.id.questionListView);
        Spinner groupList= findViewById(R.id.groupSelection);

        Question selectedItem = (Question) questionList.getSelectedItem();
        Group selectedGroup = (Group) groupList.getSelectedItem();
        selectedGroup.setQuestionId(selectedItem.getId());

        MainActivity.dh.updateGroup(selectedGroup);
        navigateAdminMenu();
    }


    public void joinUserToGroup(View view) {
        Spinner groupList= findViewById(R.id.groupSelectionJoin);
        Group selectedGroup = (Group) groupList.getSelectedItem();

        SharedPreferences shared = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        String userName = (shared.getString("name", ""));

        User user = MainActivity.dh.getUser(userName);
        user.setGroupId(selectedGroup.getId());
        MainActivity.dh.updateUser(user);
        navigateUserMenu();
    }

    public void voteQuestion(View view){

        SharedPreferences shared = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        String userName = (shared.getString("name", ""));
        User user = MainActivity.dh.getUser(userName);

        Group group = MainActivity.dh.getGroup(user.getGroupId());

        MainActivity.dh.insertAnswer(user.getUserId(), group.getQuestionId(),  AnswerQuestionFragment.vote, group.getId());

       navigateUserMenu();
    }

    public void backFromViewAll(View view) {
        navigateAdminMenu();
    }
}
