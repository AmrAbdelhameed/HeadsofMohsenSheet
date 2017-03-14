package com.example.amr.headsofmohsensheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity {

    Button btask, bmeeting;
    int ss;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    String usname, usemail, usphone, usaddress, usdesc, usid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        firebaseAuth = FirebaseAuth.getInstance();
        // getuserId
        user = firebaseAuth.getCurrentUser();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference(user.getEmail().substring(0, user.getEmail().length() - 10));

        Bundle extras = getIntent().getExtras();

        usname = extras.getString("nameuser1");
        usemail = extras.getString("emailuser1");
        usphone = extras.getString("phoneuser1");
        usaddress = extras.getString("addressuser1");
        usdesc = extras.getString("descuser1");
        usid = extras.getString("iduser1");

        btask = (Button) findViewById(R.id.donetask);
        bmeeting = (Button) findViewById(R.id.donemeeting);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner2);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.mo7sens,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String s = ((String) parent.getItemAtPosition(position));

                if (s.equals("0 Mo7sen")) {
                    ss = 0;
                }
                if (s.equals("1 Mo7sen")) {
                    ss = 1;
                }
                if (s.equals("2 Mo7sen")) {
                    ss = 2;
                }
                if (s.equals("3 Mo7sen")) {
                    ss = 3;
                }
                if (s.equals("4 Mo7sen")) {
                    ss = 4;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numtasks = Integer.parseInt(usemail);
                numtasks = numtasks + 1;
                int newtaskmo7sen = Integer.parseInt(usaddress);
                newtaskmo7sen = newtaskmo7sen + ss;
                updateUser(usname, String.valueOf(numtasks), usphone, String.valueOf(newtaskmo7sen), usdesc, usid);
                Toast.makeText(AddTaskActivity.this, "Added Task Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        bmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nummeeting = Integer.parseInt(usphone);
                nummeeting = nummeeting + 1;
                int newmeetingmo7sen = Integer.parseInt(usdesc);
                newmeetingmo7sen = newmeetingmo7sen + ss;
                updateUser(usname, usemail, String.valueOf(nummeeting), usaddress, String.valueOf(newmeetingmo7sen), usid);
                Toast.makeText(AddTaskActivity.this, "Added Meeting Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void updateUser(String name, String email, String phone, String address, String description, String id) {

        mFirebaseDatabase.child(id).child("name").setValue(name);

        mFirebaseDatabase.child(id).child("numberoftasks").setValue(email);

        mFirebaseDatabase.child(id).child("numberofmeetings").setValue(phone);

        mFirebaseDatabase.child(id).child("task_mo7sens").setValue(address);

        mFirebaseDatabase.child(id).child("meetings_mo7sens").setValue(description);

    }
}
