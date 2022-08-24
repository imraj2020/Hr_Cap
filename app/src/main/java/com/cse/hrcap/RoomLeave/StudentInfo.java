package com.cse.hrcap.RoomLeave;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentInfo {

    @PrimaryKey (autoGenerate = true)
    int id;
    String name;


    public StudentInfo(String name) {
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
