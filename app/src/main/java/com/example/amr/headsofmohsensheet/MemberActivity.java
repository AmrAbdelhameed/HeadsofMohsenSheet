package com.example.amr.headsofmohsensheet;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {

    String txtEmailLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array, android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        staticSpinner.setAdapter(staticAdapter);

        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String s = ((String) parent.getItemAtPosition(position));

                if (s.equals("IT")) {
                    txtEmailLogin = "sara123@gmail.com";
                }
                if (s.equals("Projects")) {
                    txtEmailLogin = "yara123@gmail.com";
                }
                if (s.equals("Laravel")) {
                    txtEmailLogin = "adam123@gmail.com";
                }
                if (s.equals("PR")) {
                    txtEmailLogin = "menna123@gmail.com";
                }
                if (s.equals("HR")) {
                    txtEmailLogin = "doha123@gmail.com";
                }
                if (s.equals("FR")) {
                    txtEmailLogin = "yokka123@gmail.com";
                }
                if (s.equals("LR")) {
                    txtEmailLogin = "zyad123@gmail.com";
                }
                if (s.equals("Art")) {
                    txtEmailLogin = "dina123@gmail.com";
                }
                if (s.equals("CCC")) {
                    txtEmailLogin = "mirna123@gmail.com";
                }
                if (s.equals("Blender")) {
                    txtEmailLogin = "saadya123@gmail.com";
                }
                if (s.equals("Linux")) {
                    txtEmailLogin = "islam123@gmail.com";
                }
                if (s.equals("English Heros")) {
                    txtEmailLogin = "shrook123@gmail.com";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void btnUserLogin_Click(View v) {

        if (isNetworkAvailable()) {

            //  Toast.makeText(MemberActivity.this, txtEmailLogin.substring(0, txtEmailLogin.length() - 10), Toast.LENGTH_SHORT).show();

            Bundle dataBundle = new Bundle();
            dataBundle.putString("keey", txtEmailLogin.substring(0, txtEmailLogin.length() - 10));
            Intent intent = new Intent(MemberActivity.this, ShowAllMembersAsMember.class);
            intent.putExtras(dataBundle);
            startActivity(intent);
        } else {
            Toast.makeText(MemberActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
        }

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
