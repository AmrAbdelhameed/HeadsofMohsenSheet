package com.example.amr.headsofmohsensheet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText txtPwd;
    SharedPreferences.Editor editor;
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

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
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

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnUserLogin_Click(View v) {

//        Toast.makeText(Login.this, txtEmailLogin + txtPwd.getText().toString(), Toast.LENGTH_SHORT).show();
        if (txtPwd.getText().toString().isEmpty()) {
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

                                    // Toast.makeText(Login.this, txtEmailLogin.substring(0, txtEmailLogin.length() - 10), Toast.LENGTH_SHORT).show();
                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("Options", MODE_PRIVATE);
                                    editor = pref.edit();
                                    editor.putString("head", txtEmailLogin.substring(0, txtEmailLogin.length() - 10));
                                    editor.commit();
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}