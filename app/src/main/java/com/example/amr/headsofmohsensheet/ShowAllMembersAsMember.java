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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowAllMembersAsMember extends AppCompatActivity {

    private static final String TAG = Home.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    ListView lv;
    TextView textempty;
    ArrayAdapter<String> adapter;
    String Value;
    ArrayList<String> specimens_name, specimens_email, specimens_phone, specimens_street, specimens_desc, specimens_id;
    int size_arraylist = 0;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_members_as_member);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        Value = extras.getString("keey");

        //  Toast.makeText(ShowAllMembersAsMember.this, Value, Toast.LENGTH_SHORT).show();

        specimens_name = new ArrayList<>();
        specimens_email = new ArrayList<>();
        specimens_phone = new ArrayList<>();
        specimens_street = new ArrayList<>();
        specimens_desc = new ArrayList<>();
        specimens_id = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //  Toast.makeText(MainActivity.this, user.getEmail().substring(0, user.getEmail().length() - 10), Toast.LENGTH_SHORT).show();

        lv = (ListView) findViewById(R.id.listView1);
        textempty = (TextView) findViewById(R.id.textempty);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        databaseReference.child(Value).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                specimens_name.clear();
                specimens_email.clear();
                specimens_phone.clear();
                specimens_street.clear();
                specimens_desc.clear();
                specimens_id.clear();
                for (DataSnapshot child : children) {

                    String uid = child.getKey();
                    String name = child.getValue(Member.class).getName();
                    String email = child.getValue(Member.class).getNumberoftasks();
                    String phone = child.getValue(Member.class).getNumberofmeetings();
                    String street = child.getValue(Member.class).getTask_mo7sens();
                    String desc = child.getValue(Member.class).getMeetings_mo7sens();
                    specimens_name.add(name);
                    specimens_email.add(email);
                    specimens_phone.add(phone);
                    specimens_street.add(street);
                    specimens_desc.add(desc);
                    specimens_id.add(uid);
                }
                adapter = new ArrayAdapter<String>(ShowAllMembersAsMember.this, android.R.layout.simple_list_item_1, specimens_name);
                lv.setAdapter(adapter);
                size_arraylist = specimens_name.size();
                if (size_arraylist == 0) {
                    textempty.setVisibility(View.VISIBLE);
                    textempty.setText("(Empty)");
                } else {
                    textempty.setVisibility(View.INVISIBLE);
                    textempty.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Bundle dataBundle = new Bundle();
                dataBundle.putString("nameuser1", specimens_name.get(arg2));
                dataBundle.putString("emailuser1", specimens_email.get(arg2)); // NofTasks
                dataBundle.putString("phoneuser1", specimens_phone.get(arg2));
                dataBundle.putString("addressuser1", specimens_street.get(arg2));
                dataBundle.putString("descuser1", specimens_desc.get(arg2));

                //   Toast.makeText(ShowAllMembersAsMember.this, specimens_id.get(arg2) + specimens_name.get(arg2) + specimens_email.get(arg2) + specimens_phone.get(arg2) + specimens_street.get(arg2) + specimens_desc.get(arg2), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ShowAllMembersAsMember.this, DetailsMember.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            public boolean onItemLongClick(AdapterView<?> arg0, View v,
//                                           int index, long arg3) {
//
//                if (!specimens_phone.get(index).isEmpty()) {
//                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", specimens_phone.get(index), null)));
//                } else {
//                    Toast.makeText(MainActivity.this, "There is not phone of this contact", Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });

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
