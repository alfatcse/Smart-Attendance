package com.example.alfat.smartattendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class att extends AppCompatActivity {
    TextView tt;
    Database_attendance atten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att);
        tt=(TextView)findViewById(R.id.textView);
        atten=new Database_attendance(this);
        ArrayList<attendance_input> alu = atten.getAllattendance_input();
        tt.setText("");
        if (alu != null && alu.size() > 0)
        {
            for (attendance_input k : alu) {
                tt.append(k.getNumber() + "----" + k.getNum() + "---" +"\n");
            }
        }
    }
}
