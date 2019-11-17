package com.example.planningpoker08;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {
    private Context context;
    private View v;
    TextView dateText;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //kiolvassuk a shared preferencebol h a felhasznalo be van jelent

        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_login, container, false);
        SharedPreferences loginSettings =  context.getSharedPreferences("loginPreferences", MODE_PRIVATE);

        if (loginSettings != null) {
            final String loginName = loginSettings.getString("name", "");

            if (loginName != "") {

                ((EditText) v.findViewById(R.id.nameText)).setText(loginName);
            }

            final String email = loginSettings.getString("email", "");

            if (email != "") {

                ((EditText) v.findViewById(R.id.emailText)).setText(email);
            }

            final String password = loginSettings.getString("password", "");

            if ( password != "") {

                ((EditText) v.findViewById(R.id.emailText)).setText(password);
            }

            final String birthDate = loginSettings.getString("birthDate", "");

            if (birthDate != "") {

                ((TextView) v.findViewById(R.id.dateText)).setText(birthDate);
            }
        }
        return  v;
    }


}
