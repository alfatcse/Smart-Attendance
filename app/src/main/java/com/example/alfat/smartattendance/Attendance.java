package com.example.alfat.smartattendance;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {
    DatabaseHelper dbHelper;
    String[] f;
    String[] p;
    String[] l;
    int y=0;
    int i = 0;
    int q=0;
    MyCustomAdapter dataAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        dbHelper = new DatabaseHelper(this);

        displayListView();
        checkButtonClick();
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
    private void displayListView() {
        ArrayList<Student> employees = dbHelper.getAllStudents();
        f = new String[employees.size()];
        if (employees != null && employees.size() > 0) {
            for (Student employee : employees) {
                f[i++] = employee.getName()+"  "+employee.getID();
            }
        }
        ArrayList<Country> countryList = new ArrayList<Country>();
        for(int r=0;r<i;r++)
        { Country country = new Country( f[r], false);
            countryList.add(country);}
        dataAdapter = new MyCustomAdapter(this,R.layout.country_info, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Country country = (Country) parent.getItemAtPosition(position);

            }
        });
    }
    private class MyCustomAdapter extends ArrayAdapter<Country> {
        private ArrayList<Country> countryList;
        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Country> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
        }
        private class ViewHolder {
            TextView code;
            CheckBox name;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));
            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.country_info, null);
                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Country country = (Country) cb.getTag();
                        country.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Country country = countryList.get(position);
            // holder.code.setText(" (" + country.getCode() + ")");
            holder.name.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);
            return convertView;
        }
    }
    private void checkButtonClick() {
        ArrayList<Student> employees = dbHelper.getAllStudents();
        p = new String[employees.size()];
        l=new String[employees.size()];
        if (employees != null && employees.size() > 0) {
            for (Student employee : employees) {
                p[q++] = employee.getPhone();
            }
        }
        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();
                ArrayList<Country> countryList = dataAdapter.countryList;
                for (int i = 0; i < countryList.size(); i++) {
                    Country country = countryList.get(i);
                    if (country.isSelected()) {
                      l[i]="one";
                    }
                    else
                    {
                        l[i]="nooo";
                        try {
                            SmsManager smsManager= SmsManager.getDefault();
                            smsManager.sendTextMessage(p[i],null,"Dear Sir,hope you are well.It is a matter of regret that your child is not present at class today.Hope you like to take care of it.",null,null);
                        }catch (Exception e){}
                    }
                }
            }
        });

    }
}
