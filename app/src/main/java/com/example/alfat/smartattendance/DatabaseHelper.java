package com.example.alfat.smartattendance;

/**
 * Created by Alfat on 29-Aug-16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="student_management";
    public static final int DB_VERSION=1;
    public static final String STUDENT_TABLE="employee";
    public static final String NUMBER_FIELD="_id";
    public static final String NAME_FIELD="name";
    public static final String ID_FIELD="email";
    public static final String PHONE_FIELD="phone";
    public static final String PARENT_NAME="designation";
    public static final String STUDENT_TABLE_SQL="CREATE TABLE "+STUDENT_TABLE+"("+NUMBER_FIELD+
            " INTEGER PRIMARY KEY,"+NAME_FIELD+" TEXT, "+ID_FIELD+" TEXT, "+
            PHONE_FIELD+" TEXT, "+PARENT_NAME+" TEXT)";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT_TABLE_SQL);
        Log.e("TABLE CREATE", STUDENT_TABLE_SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public long insertEmployee(Student emp)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME_FIELD,emp.getName());
        values.put(ID_FIELD,emp.getID());
        values.put(PHONE_FIELD,emp.getPhone());
        values.put(PARENT_NAME,emp.getParent_name());
        long inserted= db.insert(STUDENT_TABLE,null,values);
        db.close();
        return  inserted;
    }
    public ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> allStudents=new ArrayList<Student>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(STUDENT_TABLE,null,null,null,null,null,null);
        if(cursor!=null&&cursor.getCount()>0)
        {
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++)
            {
                int id=cursor.getInt(cursor.getColumnIndex(NUMBER_FIELD));
                String name=cursor.getString(cursor.getColumnIndex(NAME_FIELD));
                String email=cursor.getString(cursor.getColumnIndex(ID_FIELD));
                String phone=cursor.getString(cursor.getColumnIndex(PHONE_FIELD));
                String designation=cursor.getString(cursor.getColumnIndex(PARENT_NAME));
                Student e=new Student(id,name,email,phone,designation);
                allStudents.add(e);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return  allStudents;
    }

}
