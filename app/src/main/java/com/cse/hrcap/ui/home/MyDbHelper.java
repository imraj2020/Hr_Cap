package com.cse.hrcap.ui.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EmployeeDetails.db";
    private static final String TABLE_NAME = "employee_details";
    private static final String EmployeeId = "EmployeeId";
    private static final String CompanyId = "CompanyId";
    private static final String DesignationId = "DesignationId";
    private static final String Designation = "Designation";
    private static final String Fullname = "Fullname";
    private static final String Grade = "grade";
    private static final String Gradeid = "gradeid";
    private static final String EmpId = "EmpId";
    private static final String Department = "Department";
    private static final String DepartmentId = "DepartmentId";
    private static final String Position = "Position";
    private static final String PositionId = "PositionId";
    private static final String Category = "Category";
    private static final String CategoryId = "CategoryId";
    private static final String Firstname = "Firstname";
    private static final String Middlename = "Middlename";
    private static final String Lastname = "Lastname";
    private static final String Prefix = "Prefix";
    private static final String Suffix = "Suffix";
    private static final String Personalemail = "Personalemail";
    private static final String Mobileno = "Mobileno";
    private static final String Imagetitle = "Imagetitle";
    private static final String Imagepath = "Imagepath";
    private static final String Joinningdate = "Joinningdate";
    private static final String Costcenterid = "Costcenterid";
    private static final String Paycycleid = "Paycycleid";
    private static final String Locationid = "Locationid";
    private static final String Supervisorid = "Supervisorid";
    private static final String Supervisorname = "Supervisorname";

    private static final int DATABASE_VERSION_NO = 1;
    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + EmployeeId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CompanyId + " TEXT,"
            + DesignationId + " INTEGER,"
            + Designation + " TEXT,"
            + Fullname + " TEXT,"
            + Grade + " TEXT,"
            + Gradeid + " TEXT,"
            + EmpId + " TEXT,"
            + Department + " TEXT,"
            + DepartmentId + " INTEGER,"
            + Position + " TEXT,"
            + PositionId + " INTEGER,"
            + Category + " TEXT,"
            + CategoryId + " INTEGER,"
            + Firstname + " TEXT,"
            + Middlename + " TEXT,"
            + Lastname + " TEXT,"
            + Prefix + " TEXT,"
            + Suffix + " TEXT,"
            + Personalemail + " TEXT,"
            + Mobileno + " INTEGER,"
            + Imagetitle + " TEXT,"
            + Imagepath + " TEXT,"
            + Joinningdate + " TEXT,"
            + Costcenterid + " INTEGER,"
            + Paycycleid + " INTEGER,"
            + Locationid + " INTEGER,"
            + Supervisorid + " INTEGER,"
            + Supervisorname + " TEXT)";
    private Context context;

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NO);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertRecord(int employeeIds, String companyid, int designationIds, String designations,
                             String fullname, String grade, String gradeid, String empid,
                             String department, int departmentid, String position, int positionid,
                             String category, int categoryid, String firstname, String middlename,
                             String lastname, String prefix, String suffix, String personalemail,
                             int mobileno, String imagetitle, String imagepath, String joinningdate,
                             int costcenterid, int paycycleid, int locationid, int supervisorid, String supervisorname) {

        SQLiteDatabase dbs = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployeeId, employeeIds);
        values.put(CompanyId, companyid);
        values.put(DesignationId, designationIds);
        values.put(Designation, designations);
        values.put(Fullname, fullname);
        values.put(Grade, grade);
        values.put(Gradeid, gradeid);
        values.put(EmpId, empid);
        values.put(Department, department);
        values.put(DepartmentId, departmentid);
        values.put(Position, position);
        values.put(PositionId, positionid);
        values.put(Category, category);
        values.put(CategoryId, categoryid);
        values.put(Firstname, firstname);
        values.put(Middlename, middlename);
        values.put(Lastname, lastname);
        values.put(Prefix, prefix);
        values.put(Suffix, suffix);
        values.put(Personalemail, personalemail);
        values.put(Mobileno, mobileno);
        values.put(Imagetitle, imagetitle);
        values.put(Imagepath, imagepath);
        values.put(Joinningdate, joinningdate);
        values.put(Costcenterid, costcenterid);
        values.put(Paycycleid, paycycleid);
        values.put(Locationid, locationid);
        values.put(Supervisorid, supervisorid);
        values.put(Supervisorname, supervisorname);
        //values.put(Grade, grade);
        //  Log.e("Values are ", String.valueOf(values));

        dbs.insert(TABLE_NAME, null, values);
        //Toast.makeText(context, "database " + values, Toast.LENGTH_LONG).show();
        dbs.close();
    }

    public Cursor alldata(){
        SQLiteDatabase dbs = this.getWritableDatabase();
        Cursor cursor = dbs.rawQuery("select * from employee_details" ,null);
        return cursor;
    }
}