package com.example.amr.headsofmohsensheet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText txtPwd;
    String txtEmailLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        txtPwd = (EditText) findViewById(R.id.txtPasswordLogin);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioG);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);

                String s = radioButton.getText().toString();

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
        });
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnUserLogin_Click(View v) {

//        Toast.makeText(Login.this, txtEmailLogin + txtPwd.getText().toString(), Toast.LENGTH_SHORT).show();

        if (txtEmailLogin == null && txtPwd.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Forget Select Your Committe and Enter Your Password", Toast.LENGTH_SHORT).show();
        } else if (txtEmailLogin == null) {
            Toast.makeText(Login.this, "Forget Select Your Committe", Toast.LENGTH_SHORT).show();
        } else if (txtPwd.getText().toString().isEmpty()) {
            Toast.makeText(Login.this, "Forget Enter Your Password", Toast.LENGTH_SHORT).show();
        } else {
            if (isNetworkAvailable()) {
                final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wait...", "Proccessing...", true);

                (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin, txtPwd.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
//                                Log.e("ERROR", task.getException().toString());
                                    Toast.makeText(Login.this, "Invalid .. Please Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(Login.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnRegister(View v) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}