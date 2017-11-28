package com.example.alfat.smartattendance;

/**
 * Created by Alfat on 29-Aug-16.
 */
public class Student {
    private int number;
    private String name;
    private String id_;
    private String phone;
    private String parent_name;
    public Student() {
    }
    public Student(String name, String id_, String phone, String parent_name) {
        this.name = name;
        this.id_= id_;
        this.phone = phone;
        this.parent_name= parent_name;
    }
    public Student(int number,String name, String id_, String phone, String parent_name) {
        this.number =number;
        this.name = name;
        this.id_ = id_;
        this.phone = phone;
        this.parent_name = parent_name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getID() {
        return id_;
    }
    public void setID(String id_) {
        this.id_ = id_;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getParent_name() {
        return parent_name;
    }
    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }
    @Override
    public String toString() {
        return "Student["+"Number=" + number + ",Student_name="
                + name + ",Student_I.D=" + id_ + ",Parents_phone=" + phone + ",Parents_name="
                + parent_name + "]";
    }
}
