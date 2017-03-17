package com.example.amr.headsofmohsensheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsMember extends AppCompatActivity {

    TextView name, NofMeetings, NofTasks, TaskMo7sen, MeetingsMo7sens, Total, M_over_T, M_over_M, TotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_member);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        String usname = extras.getString("nameuser1");
        String usemail = extras.getString("emailuser1");
        String usphone = extras.getString("phoneuser1");
        String usaddress = extras.getString("addressuser1");
        String usdesc = extras.getString("descuser1");

        setTitle("Details Data of "+usname);

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
