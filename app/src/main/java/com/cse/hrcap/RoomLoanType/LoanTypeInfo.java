package com.cse.hrcap.RoomLoanType;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoanTypeInfo {

    //@PrimaryKey (autoGenerate = true)
    int id;
    @PrimaryKey @NonNull
    String name;

    public LoanTypeInfo(String name) {

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
