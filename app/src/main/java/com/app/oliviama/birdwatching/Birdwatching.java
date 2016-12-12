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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Birdwatching extends Activity implements View.OnClickListener {
    private EditText editTextBird;
    private EditText editTextPerson;
    private EditText editTextZipcode;
    private EditText editTextDate;
    private Button buttonReport;
    private TextView textViewBird;
    private TextView textViewPerson;
    private TextView textViewZipcode;
    private TextView textViewDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birdwatching);

        buttonReport = (Button) findViewById(R.id.buttonReport);
        editTextBird = (EditText) findViewById(R.id.editTextBird);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextPerson = (EditText) findViewById(R.id.editTextPerson);
        editTextZipcode = (EditText) findViewById(R.id.editTextZipcode);

        buttonReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String birdname = editTextBird.getText().toString();
        String peoplename = editTextPerson.getText().toString();
        String zip = editTextZipcode.getText().toString();
        String date = editTextDate.getText().toString();

        Bird bird = new Bird(birdname, peoplename, zip, date);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        DatabaseReference Reference = reference.child(zip).push();
        Reference.setValue(bird);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentSearch = new Intent(Birdwatching.this, Birdsearching.class);
        if (item.getItemId() == R.id.menuSearch) {
                startActivity(intentSearch);
            }
        if (item.getItemId()== R.id.menuReport){
            Toast.makeText(this, "You are already in the bird sighting report page", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}