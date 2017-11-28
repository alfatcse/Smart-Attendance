package com.example.alfat.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class new_record extends AppCompatActivity {
    EditText etName, etPhone, etID, etParent_name;
    DatabaseHelper dbHelper;
    String course1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        etName = (EditText) findViewById(R.id.etName);
        etID = (EditText) findViewById(R.id.etID);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etParent_name = (EditText) findViewById(R.id.etParent);
        dbHelper = new DatabaseHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void save(View v) {
        Toast.makeText(getApplicationContext(), course1, Toast.LENGTH_LONG).show();
        String name = etName.getText().toString();
        String id = etID.getText().toString();
        String phone = etPhone.getText().toString();
        String  parent_name= etParent_name.getText().toString();
        Student student = new Student(name,id, phone,parent_name);
        Toast.makeText(getApplicationContext(), student.toString(), Toast.LENGTH_LONG).show();
        long inserted = dbHelper.insertEmployee(student);
        if (inserted >= 0) {
            Toast.makeText(getApplicationContext(), "data_inserted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "data not inserted", Toast.LENGTH_LONG).show();
        }
    }
    public void view(View v) {
        Intent i = new Intent(new_record.this,view.class);
        startActivity(i);
    }
}
