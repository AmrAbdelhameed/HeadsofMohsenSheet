package com.example.amr.headsofmohsensheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddFriend extends AppCompatActivity {

    private static final String TAG = Home.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    int id_To_Update = 0;
    TextView name, NofMeetings, NofTasks, TaskMo7sen, MeetingsMo7sens, Total, M_over_T, M_over_M, TotalScore;
    String usu = "";
    String userr_id = "";
    private String userId;
    TextView T, MoT, MoM, TS;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        // Toast.makeText(getApplication(), FirebaseAuth.getInstance().getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();

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

        T = (TextView) findViewById(R.id.textTotal);
        MoT = (TextView) findViewById(R.id.textM_over_T);
        MoM = (TextView) findViewById(R.id.textM_over_M);
        TS = (TextView) findViewById(R.id.textTotalScore);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            String usname = extras.getString("nameuser");
            String usemail = extras.getString("emailuser");
            String usphone = extras.getString("phoneuser");
            String usaddress = extras.getString("addressuser");
            String usdesc = extras.getString("descuser");
            String usid = extras.getString("iduser");

            // Toast.makeText(AddFriend.this, us , Toast.LENGTH_SHORT).show();

            if (Value > 0) {

                id_To_Update = Value;
                usu = usname;
                userr_id = usid;

                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

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

                /////////////////////////////////////////////////////////

                T.setVisibility(View.VISIBLE);
                MoT.setVisibility(View.VISIBLE);
                MoM.setVisibility(View.VISIBLE);
                TS.setVisibility(View.VISIBLE);

                Double f1 = Double.parseDouble(usaddress);
                Double f2 = Double.parseDouble(usdesc);
                double res = f1 + f2;

                Total.setVisibility(View.VISIBLE);
                Total.setText(String.valueOf(res));
                Total.setFocusable(false);
                Total.setClickable(false);
                Total.setCursorVisible(false);

                Double f3 = Double.parseDouble(usaddress);
                Double f4 = Double.parseDouble(usemail);
                double res2 = f3 / f4;

                M_over_T.setVisibility(View.VISIBLE);
                M_over_T.setText(String.valueOf(res2));
                M_over_T.setFocusable(false);
                M_over_T.setClickable(false);
                M_over_T.setCursorVisible(false);

                Double f5 = Double.parseDouble(usdesc);
                Double f6 = Double.parseDouble(usphone);
                double res3 = f5 / f6;

                M_over_M.setVisibility(View.VISIBLE);
                M_over_M.setText(String.valueOf(res3));
                M_over_M.setFocusable(false);
                M_over_M.setClickable(false);
                M_over_M.setCursorVisible(false);

                double res4 = res3 + res2;

                TotalScore.setVisibility(View.VISIBLE);
                TotalScore.setText(String.valueOf(res4));
                TotalScore.setFocusable(false);
                TotalScore.setClickable(false);
                TotalScore.setCursorVisible(false);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                setTitle(usu);
                getMenuInflater().inflate(R.menu.display_contact, menu);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Contact:

                T.setVisibility(View.INVISIBLE);
                MoT.setVisibility(View.INVISIBLE);
                MoM.setVisibility(View.INVISIBLE);
                TS.setVisibility(View.INVISIBLE);
                Total.setVisibility(View.INVISIBLE);
                M_over_T.setVisibility(View.INVISIBLE);
                M_over_M.setVisibility(View.INVISIBLE);
                TotalScore.setVisibility(View.INVISIBLE);

                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);
                name.setCursorVisible(true);

                NofMeetings.setEnabled(true);
                NofMeetings.setFocusableInTouchMode(true);
                NofMeetings.setClickable(true);
                NofMeetings.setCursorVisible(true);

                NofTasks.setEnabled(true);
                NofTasks.setFocusableInTouchMode(true);
                NofTasks.setClickable(true);
                NofTasks.setCursorVisible(true);

                TaskMo7sen.setEnabled(true);
                TaskMo7sen.setFocusableInTouchMode(true);
                TaskMo7sen.setClickable(true);
                TaskMo7sen.setCursorVisible(true);

                MeetingsMo7sens.setEnabled(true);
                MeetingsMo7sens.setFocusableInTouchMode(true);
                MeetingsMo7sens.setClickable(true);
                MeetingsMo7sens.setCursorVisible(true);

                return true;

            case R.id.Delete_Contact:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to delete it ?!")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                RemoveContact(userr_id);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Nothing
                    }
                });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();
                return true;

            case R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keycode, event);
    }

    private void createUser(String name, String email, String phone, String address, String description) {

        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        Member contact = new Member(name, email, phone, address, description);

        mFirebaseDatabase.child(userId).setValue(contact);

        addUserChangeListener();
    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Member contact = dataSnapshot.getValue(Member.class);

                if (contact == null) {
                    Log.e(TAG, "Member data is null!");
                    return;
                }
                Log.e(TAG, "Member data is changed!" + contact.name + ", " + contact.numberoftasks + ", " + contact.numberofmeetings + ", " + contact.task_mo7sens + ", " + contact.meetings_mo7sens);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String email, String phone, String address, String description, String id) {

        if (!TextUtils.isEmpty(name)) {
            mFirebaseDatabase.child(id).child("name").setValue(name);
        }

        mFirebaseDatabase.child(id).child("numberoftasks").setValue(email);

        mFirebaseDatabase.child(id).child("numberofmeetings").setValue(phone);

        mFirebaseDatabase.child(id).child("task_mo7sens").setValue(address);

        mFirebaseDatabase.child(id).child("meetings_mo7sens").setValue(description);

    }

    private void RemoveContact(String id) {

        mFirebaseDatabase.child(id).child("name").removeValue();

        mFirebaseDatabase.child(id).child("numberoftasks").removeValue();

        mFirebaseDatabase.child(id).child("numberofmeetings").removeValue();

        mFirebaseDatabase.child(id).child("task_mo7sens").removeValue();

        mFirebaseDatabase.child(id).child("meetings_mo7sens").removeValue();

    }

    public void run(View view) {

        String nname = name.getText().toString();
        String nemail = NofTasks.getText().toString();
        String nphone = NofMeetings.getText().toString();
        String nstreet = TaskMo7sen.getText().toString();
        String ndescrip = MeetingsMo7sens.getText().toString();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (name.getText().toString().isEmpty() || NofTasks.getText().toString().isEmpty() || NofMeetings.getText().toString().isEmpty() || TaskMo7sen.getText().toString().isEmpty() || MeetingsMo7sens.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Inputs ... ", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable()) {
                        updateUser(nname, nemail, nphone, nstreet, ndescrip, userr_id);
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddFriend.this, "No Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                if (name.getText().toString().isEmpty() || NofTasks.getText().toString().isEmpty() || NofMeetings.getText().toString().isEmpty() || TaskMo7sen.getText().toString().isEmpty() || MeetingsMo7sens.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Inputs ... ", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable()) {
                        createUser(nname, nemail, nphone, nstreet, ndescrip);
                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddFriend.this, "No Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}