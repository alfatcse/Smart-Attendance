package com.example.alfat.smartattendance;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {
    Button Attendance;
    Button New,bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Attendance = (Button) findViewById(R.id.Attendance);
        New = (Button) findViewById(R.id.new_button);
        bt=(Button)findViewById(R.id.button);
    }
    public void Attendance(View v) {
        Intent i = new Intent(MainActivity.this, Attendance.class);
        startActivity(i);
    }
    public void New(View v) {
        Intent j = new Intent(MainActivity.this, new_record.class);
        startActivity(j);
    }

}
