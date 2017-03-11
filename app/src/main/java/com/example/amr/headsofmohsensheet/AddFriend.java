package com.example.amr.headsofmohsensheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final String TAG = Login.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    int id_To_Update = 0;
    TextView name, phone, email, street, descrip;
    String usu = "";
    String userr_id = "";
    private String userId;
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

        mFirebaseDatabase = mFirebaseInstance.getReference(user.getUid());

        name = (TextView) findViewById(R.id.editTextName);
        phone = (TextView) findViewById(R.id.editTextPhone);
        email = (TextView) findViewById(R.id.editTextEmail);
        street = (TextView) findViewById(R.id.editTextStreet);
        descrip = (TextView) findViewById(R.id.editTextCity);

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

                phone.setText(usphone);
                phone.setFocusable(false);
                phone.setClickable(false);
                phone.setCursorVisible(false);

                email.setText(usemail);
                email.setFocusable(false);
                email.setClickable(false);
                email.setCursorVisible(false);

                street.setText(usaddress);
                street.setFocusable(false);
                street.setClickable(false);
                street.setCursorVisible(false);

                descrip.setText(usdesc);
                descrip.setFocusable(false);
                descrip.setClickable(false);
                descrip.setCursorVisible(false);
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
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);
                name.setCursorVisible(true);

                phone.setEnabled(true);
                phone.setFocusableInTouchMode(true);
                phone.setClickable(true);
                phone.setCursorVisible(true);

                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);
                email.setCursorVisible(true);

                street.setEnabled(true);
                street.setFocusableInTouchMode(true);
                street.setClickable(true);
                street.setCursorVisible(true);

                descrip.setEnabled(true);
                descrip.setFocusableInTouchMode(true);
                descrip.setClickable(true);
                descrip.setCursorVisible(true);

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

        Contact contact = new Contact(name, email, phone, address, description);

        mFirebaseDatabase.child(userId).setValue(contact);

        addUserChangeListener();
    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Contact contact = dataSnapshot.getValue(Contact.class);

                if (contact == null) {
                    Log.e(TAG, "Contact data is null!");
                    return;
                }
                Log.e(TAG, "Contact data is changed!" + contact.name + ", " + contact.email + ", " + contact.phone + ", " + contact.address + ", " + contact.description);

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

        mFirebaseDatabase.child(id).child("email").setValue(email);

        mFirebaseDatabase.child(id).child("phone").setValue(phone);

        mFirebaseDatabase.child(id).child("address").setValue(address);

        mFirebaseDatabase.child(id).child("description").setValue(description);

    }

    private void RemoveContact(String id) {

        mFirebaseDatabase.child(id).child("name").removeValue();

        mFirebaseDatabase.child(id).child("email").removeValue();

        mFirebaseDatabase.child(id).child("phone").removeValue();

        mFirebaseDatabase.child(id).child("address").removeValue();

        mFirebaseDatabase.child(id).child("description").removeValue();

    }

    public void run(View view) {

        String nname = name.getText().toString();
        String nemail = email.getText().toString();
        String nphone = phone.getText().toString();
        String nstreet = street.getText().toString();
        String ndescrip = descrip.getText().toString();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Try Again ... ", Toast.LENGTH_SHORT).show();
                } else {
                    updateUser(nname, nemail, nphone, nstreet, ndescrip, userr_id);
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Try Again ... ", Toast.LENGTH_SHORT).show();
                } else {
                    createUser(nname, nemail, nphone, nstreet, ndescrip);
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

}