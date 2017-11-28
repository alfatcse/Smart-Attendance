package com.example.alfat.smartattendance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {
    DatabaseHelper dbHelper;
    TextView student_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        student_info=(TextView)findViewById(R.id.email_view);
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
        ArrayList<Student> students = dbHelper.getAllStudents();
        student_info.setText("");
        if (students != null && students.size() > 0)
        {
            for (Student employee : students) {
                student_info.append(employee.getNumber() + "----" + employee.getName() + "---" + employee.getID() + "---" + employee.getParent_name() + "----" + employee.getPhone()+"\n");
            }
        } else if (students.size() == 0) {
            Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_LONG).show();
        }
    }
}
