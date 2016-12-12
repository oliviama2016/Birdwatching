package com.app.oliviama.birdwatching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Birdsearching extends Activity implements View.OnClickListener{
    private EditText editTextZipcode;
    private TextView textViewZipcode;
    private Button buttonSearch;
    private TextView textViewReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birdsearching);

        buttonSearch=(Button)findViewById(R.id.buttonSearch);
        editTextZipcode=(EditText)findViewById(R.id.editTextZipcode);
        textViewReport=(TextView)findViewById(R.id.textViewReport);
        textViewZipcode=(TextView)findViewById(R.id.textViewZipcode);

        buttonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String zip = editTextZipcode.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child(zip).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Bird bird = dataSnapshot.getValue(Bird.class);
                String val = textViewReport.getText().toString();
                val = val + "\n \n Birdname: " + bird.birdname + "\n Personname: " + bird.peoplename + "\n Zipcode: " + bird.zip + "\n Date:"+ bird.date + "\n";
                textViewReport.setText(val);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intentReport = new Intent(Birdsearching.this, Birdwatching.class);
        if (item.getItemId()==R.id.menuReport){
            startActivity(intentReport);
        }
        if (item.getItemId()==R.id.menuSearch){
            Toast.makeText(this, "You are already in the last bird sighting search page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}