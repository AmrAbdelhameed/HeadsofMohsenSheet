package com.example.amr.headsofmohsensheet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText txtPassword;
    String txtEmailAddress;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioG);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);

                String s = radioButton.getText().toString();
                if (s.equals("IT")) {
                    txtEmailAddress = "sara123@gmail.com";
                }
                if (s.equals("Projects")) {
                    txtEmailAddress = "yara123@gmail.com";
                }
                if (s.equals("Laravel")) {
                    txtEmailAddress = "adam123@gmail.com";
                }
                if (s.equals("PR")) {
                    txtEmailAddress = "menna123@gmail.com";
                }
                if (s.equals("HR")) {
                    txtEmailAddress = "doha123@gmail.com";
                }
                if (s.equals("FR")) {
                    txtEmailAddress = "yokka123@gmail.com";
                }
                if (s.equals("LR")) {
                    txtEmailAddress = "zyad123@gmail.com";
                }
                if (s.equals("Art")) {
                    txtEmailAddress = "dina123@gmail.com";
                }
                if (s.equals("CCC")) {
                    txtEmailAddress = "mirna123@gmail.com";
                }
                if (s.equals("Blender")) {
                    txtEmailAddress = "saadya123@gmail.com";
                }
                if (s.equals("Linux")) {
                    txtEmailAddress = "islam123@gmail.com";
                }
                if (s.equals("English Heros")) {
                    txtEmailAddress = "shrook123@gmail.com";
                }
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnRegistrationUser_Click(View v) {
//        Toast.makeText(Register.this, txtEmailAddress + txtPassword.getText().toString(), Toast.LENGTH_SHORT).show();
        if (txtEmailAddress == null && txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(Register.this, "Forget Select Your Committe Enter Your Password", Toast.LENGTH_SHORT).show();
        } else if (txtEmailAddress == null) {
            Toast.makeText(Register.this, "Forget Select Your Committe", Toast.LENGTH_SHORT).show();
        } else if (txtPassword.getText().toString().isEmpty()) {
            Toast.makeText(Register.this, "Forget Enter Your Password", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(Register.this, "Please wait...", "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress, txtPassword.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                Intent i = new Intent(Register.this, Login.class);
                                startActivity(i);
                                finish();
                            } else {
//                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(Register.this,"InValid", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
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
}
