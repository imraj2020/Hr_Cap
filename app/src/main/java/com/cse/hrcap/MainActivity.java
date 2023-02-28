package com.cse.hrcap;

import static com.cse.hrcap.ui.LeaveSummary.LeaveSummaryFragment.leaveSummaryRoomDB;
import static com.cse.hrcap.ui.SelfAttandanceSummary.SelfAttandanceSummaryFragment.selfRoomDB;
import static com.cse.hrcap.ui.holiday.HolidayFragment.holidayRoomDB;
import static com.cse.hrcap.ui.leavebalance.LeaveBalanceFragment.leaveBalanceroomDB;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegRoomDB;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomHoliday.HolidayRoomDB;
import com.cse.hrcap.RoomLeave.LeaveInfo;
import com.cse.hrcap.RoomLeave.MyRoomDB;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceInfo;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceRoomDB;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryRoomDB;
import com.cse.hrcap.RoomLoanSubType.LoanSubTypeInfo;
import com.cse.hrcap.RoomLoanSubType.LoanSubTypeRoomDB;
import com.cse.hrcap.RoomLoanType.LoanTypeInfo;
import com.cse.hrcap.RoomLoanType.LoanTypeRoomDB;
import com.cse.hrcap.RoomRegReason.RegReasonInfo;
import com.cse.hrcap.RoomRegReason.RegReasonRoomDB;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;
import com.cse.hrcap.RoomSelfSummary.SelfRoomDB;
import com.cse.hrcap.network.AttdanceRegularizationSummary;
import com.cse.hrcap.network.AttdanceSummary;
import com.cse.hrcap.network.HolidayResponse;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveBalanceResponse;
import com.cse.hrcap.network.LeaveSummary;
import com.cse.hrcap.network.LeaveTypeResponse;
import com.cse.hrcap.network.LoanApiClient;
import com.cse.hrcap.network.LoanTypeResponse;
import com.cse.hrcap.network.LoansubTypeResponse;
import com.cse.hrcap.network.RegReasonRequest;
import com.cse.hrcap.ui.holiday.HolidayFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.cse.hrcap.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    LocationManager locationManager;
    HolidayFragment holidayFragment;
    public static MyRoomDB leaveroomDB;
    //    public static LeaveBalanceRoomDB leaveBalanceroomDB;
    public static LoanTypeRoomDB loanTypeRoomDB;
    public static LoanSubTypeRoomDB loanSubTypeRoomDB;
    public static LeaveDraftRoomDB leavedraftRoomDB;
    //    public static HolidayRoomDB holidayRoomDB;
//    public static LeaveSummaryRoomDB leaveSummaryRoomDB;
//    public static SelfRoomDB selfRoomDB;
    public static AtdRegRoomDB atdRegRoomDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //testing

        setleaveTypesDatabase();
        setLoanTypeDatabase();
        setLoanSubTypeDatabase();


        boolean leavetype = leaveroomDB.leaveDAO().isExists();
        if (leavetype == false) {
            leaveTypes();
        }

        boolean loantypedata = loanTypeRoomDB.loanTypeDAO().isExists();
        if (loantypedata == false) {
            loanTypes();
        }
        boolean loansubtype = loanSubTypeRoomDB.loanSubTypeDAO().isExists();
        if (loansubtype == false) {
            loansubTypes();
        }

        //db is the roomdb
        RegReasonRoomDB db = RegReasonRoomDB.getDbInstances(getApplicationContext());

        boolean regreason = db.regReasonDAO().isExists();
       if(regreason==false) {
            AttdanceRegReason();
        }

        // checking fragment for data sync
        // holidayFragment=getFragmentManager().findFragmentByTag("holidayFragment");
//          holidayFragment.holidayTypes();
//          holidayFragment.setDatabase();


        //App Toolbar

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        //

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_leave, R.id.nav_attadancereg, R.id.nav_loan, R.id.nav_selfattandance,
                R.id.nav_logout, R.id.nav_chengepassword, R.id.nav_holiday, R.id.nav_leavebalance, R.id.nav_leavesummary,
                R.id.nav_selfattandancesummary, R.id.nav_attadanceregsummary, R.id.nav_leavedraft, R.id.nav_atdregaprsummary
                , R.id.nav_leaveaprsummary,R.id.nav_regentrydraft)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        // menu item select
        binding.navView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menu -> {
            Intent newIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(newIntent);
            MainActivity.this.finish();
            return true;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void leaveTypes() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");


        Call<List<LeaveTypeResponse>> call = LeaveApiClient.getUserService().leavetype(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveTypeResponse>> call, Response<List<LeaveTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LeaveTypeResponse> nlist = response.body();

                    //  Toast.makeText(getContext(), "Retrive Successfull", Toast.LENGTH_SHORT).show();
                    Log.d("LeaveResponse", nlist.get(0).getLeaveTypeName().toString());
//                    StudentInfo studentInfo = new StudentInfo();
//                    studentInfo.setLeavetypename("Test");
//                    Log.d("LeaveResponse",studentInfo.getLeavetypename() );
//                     roomDB.studentDAO().insertStudent(studentInfo);

                    for (LeaveTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name: " + post.getLeaveTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";

                        LeaveInfo leaveInfo = new LeaveInfo(post.getLeaveTypeName());
                        leaveroomDB.leaveDAO().insertLeave(leaveInfo);

                        // Leavetyperesponse.append(content);
                    }
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Leave Type Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveTypeResponse>> call, Throwable t) {
                // Leavetyperesponse.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setleaveTypesDatabase() {
        leaveroomDB = Room.databaseBuilder(getApplicationContext(), MyRoomDB.class, "Leavetype.db")
                .allowMainThreadQueries().build();
    }


    private void leaveBalance() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<LeaveBalanceResponse>> call = LeaveApiClient.getUserService().leavebalance(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveBalanceResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveBalanceResponse>> call, Response<List<LeaveBalanceResponse>> response) {

                if (response.isSuccessful()) {

                    List<LeaveBalanceResponse> nlist = response.body();


                    for (LeaveBalanceResponse post : nlist) {
                        String content = "";
                        content += "Company ID: " + post.getCompanyId() + "\n";
                        content += "Employee ID: " + post.getEmployeeId() + "\n";
                        content += "Leave Type Id :" + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name:" + post.getLeaveTypeName() + "\n";
                        content += "Taken Leave: " + post.getTakenLeave() + "\n";
                        content += "Total Leave :" + post.getTotalLeave() + "\n";
                        content += "Available Leave: " + post.getAvailableLeave() + "\n\n";

                        LeaveBalanceInfo leaveBalanceInfo = new LeaveBalanceInfo(post.getCompanyId(), post.getEmployeeId(),
                                post.getLeaveTypeId(), post.getLeaveTypeName(), post.getTakenLeave(), post.getTotalLeave(),
                                post.getAvailableLeave());
                        leaveBalanceroomDB.leaveBalanceDAO().insertLeaveBalance(leaveBalanceInfo);


                        //  LeaveBalance.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveBalanceResponse>> call, Throwable t) {
//                LeaveBalance.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLeaveBalanceDatabase() {
        leaveBalanceroomDB = Room.databaseBuilder(getApplicationContext(), LeaveBalanceRoomDB.class, "Leavebalance.db")
                .allowMainThreadQueries().build();
    }

    //Loan Type
    private void loanTypes() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LoanTypeResponse>> call = LoanApiClient.getUserService().loantype(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LoanTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LoanTypeResponse>> call, Response<List<LoanTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LoanTypeResponse> nlist = response.body();


                    for (LoanTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name: " + post.getLoanTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";
                        LoanTypeInfo loanTypeInfo = new LoanTypeInfo(post.getLoanTypeName());
                        loanTypeRoomDB.loanTypeDAO().insertLoan(loanTypeInfo);
                        //Loantyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoanTypeResponse>> call, Throwable t) {
                // Loantyperesponse.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoanTypeDatabase() {
        loanTypeRoomDB = Room.databaseBuilder(getApplicationContext(), LoanTypeRoomDB.class, "Loantype.db")
                .allowMainThreadQueries().build();
    }


    private void loansubTypes() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LoansubTypeResponse>> call = LoanApiClient.getUserService().loansubtype(companyid);


        call.enqueue(new Callback<List<LoansubTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LoansubTypeResponse>> call, Response<List<LoansubTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LoansubTypeResponse> nlist = response.body();


                    for (LoansubTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Loan sub Type Name: " + post.getLoanSubTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";
                        LoanSubTypeInfo loanSubTypeInfo = new LoanSubTypeInfo(post.getLoanSubTypeName());
                        loanSubTypeRoomDB.loanSubTypeDAO().insertLoanSub(loanSubTypeInfo);
                        // Loansubtyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoansubTypeResponse>> call, Throwable t) {
                //  Loantyperesponse.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLoanSubTypeDatabase() {
        loanSubTypeRoomDB = Room.databaseBuilder(getApplicationContext(), LoanSubTypeRoomDB.class, "Loansubtype.db")
                .allowMainThreadQueries().build();
    }


    public void holidayTypes() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<HolidayResponse>> call = LeaveApiClient.getUserService().holiday(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<HolidayResponse>>() {
            @Override
            public void onResponse(Call<List<HolidayResponse>> call, Response<List<HolidayResponse>> response) {

                if (response.isSuccessful()) {

                    List<HolidayResponse> nlist = response.body();


                    for (HolidayResponse post : nlist) {
                        String content = "";
                        content += "Holiday ID: " + post.getHolidayId() + "\n";
                        content += "Company ID: " + post.getCompanyId() + "\n";
                        content += "Holiday name: " + post.getHolidayName() + "\n";
                        content += "Short Name: " + post.getShortName() + "\n";
                        content += "ReligionSpecific : " + post.getReligionSpecific() + "\n";
                        content += "Religion ID: " + post.getReligionId() + "\n";
                        content += "Religion Name: " + post.getReligionName() + "\n";
                        content += "Type ID: " + post.getTypeId() + "\n";
                        content += "Type Name: " + post.getTypeName() + "\n";
                        content += "Description: " + post.getDescription() + "\n";
                        content += "Active: " + post.getActive() + "\n";
                        content += "EveryYearSameMonthDay : " + post.getEveryYearSameMonthDay() + "\n\n";

                        HolidayInfo holidayInfo = new HolidayInfo(post.getHolidayId(), post.getCompanyId(), post.getHolidayName(),
                                post.getShortName(), post.getReligionSpecific(), post.getReligionId(), post.getReligionName(),
                                post.getTypeId(), post.getTypeName(), post.getDescription(), post.getActive(), post.getEveryYearSameMonthDay());
                        holidayRoomDB.holidayDAO().insertHoliday(holidayInfo);

                        // Holidayres.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Holiday Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HolidayResponse>> call, Throwable t) {
                //Holidayres.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setHolidayDatabase() {
        holidayRoomDB = Room.databaseBuilder(getApplicationContext(), HolidayRoomDB.class, "Holidayinfo.db")
                .allowMainThreadQueries().build();
    }


    //Self Attandance summary
    public void SelfAttdancesummary() {
        Intent intent = getIntent();
        String CompanyId = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        Call<List<AttdanceSummary>> call = LeaveApiClient.getUserService().attdancesummary(CompanyId, Employee);

        call.enqueue(new Callback<List<AttdanceSummary>>() {
            @Override
            public void onResponse(Call<List<AttdanceSummary>> call, Response<List<AttdanceSummary>> response) {
                if (response.isSuccessful()) {

                    List<AttdanceSummary> nlist = response.body();


                    for (AttdanceSummary post : nlist) {

                        SelfInfo selfInfo = new SelfInfo(post.getCheckInDate(), post.getPunchTime(), post.getInOut()
                                , post.getEntryBy(), post.getEntryDate());
                        selfRoomDB.selfDAO().insertSelf(selfInfo);
                        //  LeaveSummary.append(content);
                    }
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttdanceSummary>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
                // Log.d("response",t);
            }
        });


    }

    public void setSelfAttandanceSummaryDatabase() {
        selfRoomDB = Room.databaseBuilder(getApplicationContext(), SelfRoomDB.class, "AttandanceSummary.db")
                .allowMainThreadQueries().build();
    }


    //Attandance Regularization Summary

    public void Attdanceregsummary() {
        Intent intent = getIntent();
        String CompanyId = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        Call<List<AttdanceRegularizationSummary>> call = LeaveApiClient.getUserService().attdanceregsummary(CompanyId, Employee);

        call.enqueue(new Callback<List<AttdanceRegularizationSummary>>() {
            @Override
            public void onResponse(Call<List<AttdanceRegularizationSummary>> call, Response<List<AttdanceRegularizationSummary>> response) {
                if (response.isSuccessful()) {

                    List<AttdanceRegularizationSummary> nlist = response.body();


                    for (AttdanceRegularizationSummary post : nlist) {

                        AtdRegInfo atdRegInfo = new AtdRegInfo(post.getMovementId(), post.getCompanyId(), post.getEmpId(),
                                post.getEmpCode(), post.getFullName(), post.getStartDate(), post.getEndDate(),
                                post.getReason(), post.getNote(), post.getFromTime(), post.getToTime(), post.getStatus(),
                                post.getEntryBy(), post.getEntryDate());
                        atdRegRoomDB.atdRegDAO().insertAtdRegSummary(atdRegInfo);

                        //  LeaveSummary.append(content);
                    }
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttdanceRegularizationSummary>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t, Toast.LENGTH_SHORT).show();
                // Log.d("response",t);
            }
        });


    }

    public void setAttandanceregSummaryDatabase() {
        atdRegRoomDB = Room.databaseBuilder(getApplicationContext(), AtdRegRoomDB.class, "AtdregSummary.db")
                .allowMainThreadQueries().build();
    }

    //leave summary
    private void leavesummary() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<LeaveSummary>> call = LeaveApiClient.getUserService().leavesummary(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveSummary>> call, Response<List<LeaveSummary>> response) {

                if (response.isSuccessful()) {

                    List<LeaveSummary> nlist = response.body();


                    for (LeaveSummary post : nlist) {
//                        String content = "";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Employee ID: " + post.getEmployeeId() + "\n";
//                        content += "Leave Type Id :" + post.getLeaveTypeId() + "\n";
//                        content += "Leave Type Name:" + post.getLeaveTypeName() + "\n";
//                        content += "Taken Leave: " + post.getTakenLeave() + "\n";
//                        content += "Total Leave :" + post.getTotalLeave() + "\n";
//                        content += "Available Leave: " + post.getAvailableLeave() + "\n\n";

                        LeaveSummaryInfo leaveSummaryInfo = new LeaveSummaryInfo(post.getLeaveId(), post.getLeaveTypeName(),
                                post.getFromDate(), post.getToDate(), post.getTotalDay(), post.getTotalHours(),
                                post.getEntryBy(), post.getEntryDateTime(), post.getLeaveStatusId(), post.getLeaveStatusName());
                        leaveSummaryRoomDB.leaveSummaryDAO().insertLeaveSummary(leaveSummaryInfo);


                        //  LeaveBalance.append(content);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveSummary>> call, Throwable t) {
//                LeaveBalance.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setLeaveSummaryDatabase() {
        leaveSummaryRoomDB = Room.databaseBuilder(getApplicationContext(), LeaveSummaryRoomDB.class, "LeaveSummary.db")
                .allowMainThreadQueries().build();
    }


    private void AttdanceRegReason() {
        Intent intent = getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employee = intent.getStringExtra("Employee");

        Call<List<RegReasonRequest>> call = LoanApiClient.getUserService().myreason(companyid,employee);


        call.enqueue(new Callback<List<RegReasonRequest>>() {
            @Override
            public void onResponse(Call<List<RegReasonRequest>> call, Response<List<RegReasonRequest>> response) {

                if (response.isSuccessful()) {

                    List<RegReasonRequest> nlist = response.body();


                    for (RegReasonRequest post : nlist) {

                        RegReasonRoomDB db = RegReasonRoomDB.getDbInstances(getApplicationContext());
                        RegReasonInfo regReasonInfo = new RegReasonInfo(post.getId(),post.getReason());
                        db.regReasonDAO().insertReason(regReasonInfo);

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Reg Reason Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RegReasonRequest>> call, Throwable t) {
                // Leavetyperesponse.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            //  Delete all data from table
            leaveroomDB.leaveDAO().deleteAll();
            loanTypeRoomDB.loanTypeDAO().deleteAll();
            loanSubTypeRoomDB.loanSubTypeDAO().deleteAll();

            selfRoomDB.selfDAO().deleteAll();
            atdRegRoomDB.atdRegDAO().deleteAll();
            holidayRoomDB.holidayDAO().deleteAll();
            leaveBalanceroomDB.leaveBalanceDAO().deleteAll();
            leaveSummaryRoomDB.leaveSummaryDAO().deleteAll();


            // inserting data

            // leave types
            setleaveTypesDatabase();
            leaveTypes();

            //  Loan type
            setLoanTypeDatabase();
            loanTypes();

            //Loan Sub Type
            setLoanSubTypeDatabase();
            loansubTypes();

            //Self Attandance Summary
            SelfAttdancesummary();
            setSelfAttandanceSummaryDatabase();


            //Attandance regularization summary
            Attdanceregsummary();
            setAttandanceregSummaryDatabase();

            //Holiday types
            setHolidayDatabase();
            holidayTypes();

            // leave balance
            setLeaveBalanceDatabase();
            leaveBalance();

            //Leave Summary
            leavesummary();
            setLeaveSummaryDatabase();


            Toast.makeText(getApplicationContext(), "Sync Success", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}