package com.example.amr.headsofmohsensheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsMember extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    TextView name, NofMeetings, NofTasks, TaskMo7sen, MeetingsMo7sens, Total, M_over_T, M_over_M, TotalScore;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_member);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        // getuserId
        user = firebaseAuth.getCurrentUser();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference(user.getEmail().substring(0, user.getEmail().length() - 10));

        name = (TextView) findViewById(R.id.editTextName);
        NofMeetings = (TextView) findViewById(R.id.editTextNofMeetings);
        NofTasks = (TextView) findViewById(R.id.editTextNofTasks);
        TaskMo7sen = (TextView) findViewById(R.id.editTextTaskMo7sen);
        MeetingsMo7sens = (TextView) findViewById(R.id.editTextMeetingsMo7sens);

        Total = (TextView) findViewById(R.id.editTextTotal);
        M_over_T = (TextView) findViewById(R.id.editTextM_over_T);
        M_over_M = (TextView) findViewById(R.id.editTextM_over_M);
        TotalScore = (TextView) findViewById(R.id.editTextTotalScore);

        Bundle extras = getIntent().getExtras();

        String usname = extras.getString("nameuser");
        String usemail = extras.getString("emailuser");
        String usphone = extras.getString("phoneuser");
        String usaddress = extras.getString("addressuser");
        String usdesc = extras.getString("descuser");
        String usid = extras.getString("iduser");

        name.setText(usname);
        name.setFocusable(false);
        name.setClickable(false);
        name.setCursorVisible(false);

        NofTasks.setText(usemail);
        NofTasks.setFocusable(false);
        NofTasks.setClickable(false);
        NofTasks.setCursorVisible(false);

        NofMeetings.setText(usphone);
        NofMeetings.setFocusable(false);
        NofMeetings.setClickable(false);
        NofMeetings.setCursorVisible(false);

        TaskMo7sen.setText(usaddress);
        TaskMo7sen.setFocusable(false);
        TaskMo7sen.setClickable(false);
        TaskMo7sen.setCursorVisible(false);

        MeetingsMo7sens.setText(usdesc);
        MeetingsMo7sens.setFocusable(false);
        MeetingsMo7sens.setClickable(false);
        MeetingsMo7sens.setCursorVisible(false);

        Double f1 = Double.parseDouble(usaddress);
        Double f2 = Double.parseDouble(usdesc);
        double res = f1 + f2;

        Total.setText(String.valueOf(res));
        Total.setFocusable(false);
        Total.setClickable(false);
        Total.setCursorVisible(false);

        Double f3 = Double.parseDouble(usaddress);
        Double f4 = Double.parseDouble(usemail);

        double res2 = f3 / f4;

        M_over_T.setText(String.valueOf(res2));
        M_over_T.setFocusable(false);
        M_over_T.setClickable(false);
        M_over_T.setCursorVisible(false);

        Double f5 = Double.parseDouble(usdesc);
        Double f6 = Double.parseDouble(usphone);
        double res3 = f5 / f6;

        M_over_M.setText(String.valueOf(res3));
        M_over_M.setFocusable(false);
        M_over_M.setClickable(false);
        M_over_M.setCursorVisible(false);

        double res4 = res3 + res2;

        TotalScore.setText(String.valueOf(res4));
        TotalScore.setFocusable(false);
        TotalScore.setClickable(false);
        TotalScore.setCursorVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
