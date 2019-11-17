package com.example.planningpoker08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

        if (remember.isChecked()) { // hogyha ki van pipalva elmentjuk az adatokat a shared preferences-el
            SharedPreferences sharedPref = getSharedPreferences("loginPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            EditText nameEdit = (EditText) findViewById(R.id.nameText);
            editor.putString("name", nameEdit.getText().toString());

            EditText emailEdit = (EditText) findViewById(R.id.emailText);
            editor.putString("email", emailEdit.getText().toString());

            EditText passwordEdit = (EditText) findViewById(R.id.password);
            editor.putString("password", passwordEdit.getText().toString());

            TextView birthEdit = (TextView) findViewById(R.id.dateText);
            editor.putString("birthDate", birthEdit.getText().toString());

            editor.commit();
        }

        FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
        frag_trans.replace(R.id.activity_fragment, new AddGroupFragment());
        frag_trans.addToBackStack("Group");
        frag_trans.commit();
    }

    public void addGroup(){
        TextView groupName = (TextView) findViewById(R.id.dateText);
        dh.insertGroup(groupName.getText().toString());
    }


}
