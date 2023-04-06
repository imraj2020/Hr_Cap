package com.cse.hrcap.ui.CheckAttendance;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.AttandanceReportAdapter;
import com.cse.hrcap.databinding.FragmentCheckAttendanceBinding;
import com.cse.hrcap.network.AttendanceReportList;
import com.cse.hrcap.network.CheckAttendanceResponse;
import com.cse.hrcap.network.NewApiClient;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckAttendanceFragment extends Fragment {

    private CheckAttendanceViewModel mViewModel;
    FragmentCheckAttendanceBinding binding;
    ArrayList<AttendanceReportList> AttendanceReport = new ArrayList<>();

    //Receipt Preview
    AttandanceReportAdapter attandanceReportAdapter;

    public static CheckAttendanceFragment newInstance() {
        return new CheckAttendanceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCheckAttendanceBinding.inflate(inflater);

        RetriveData();
        AtdrReportRecyclerView();


        return binding.getRoot();
    }


    public void RetriveData() {
        Intent intent = getActivity().getIntent();
        String Companyid = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        //system date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // add 1 to get the correct month
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // print the date in MM/DD/YYYY format
        String Todate = String.format("%02d/%02d/%04d", month, day, year);


        Call<CheckAttendanceResponse> call = NewApiClient.getUserService().GetAtdInfo(Companyid, Employee, Todate);
        call.enqueue(new Callback<CheckAttendanceResponse>() {

            @Override
            public void onResponse(Call<CheckAttendanceResponse> call, Response<CheckAttendanceResponse> response) {

                if (response.isSuccessful()) {
//                    binding.OrderNo.setText(response.body().getOrderBaicInfo().getOrderNo());
//                    binding.tvOrderDate.setText(response.body().getOrderBaicInfo().getOrderDate());
//                    binding.DelivaryDate.setText(response.body().getOrderBaicInfo().getDeliveryDate());
//                    binding.CustomerName.setText(response.body().getOrderBaicInfo().getCustomerName());
//                    binding.CustomerAddrss.setText(response.body().getOrderBaicInfo().getCustomerAddress());
//                    binding.TotalAmount.setText("Total = "+String.format("%.2f", response.body().getOrderBaicInfo().getTotalOrderPrice()));

//                      String s = String.valueOf(response.body().getAttendanceReport());


                    setBoldText(binding.TvRunBy, "Name: ", response.body().getRunBy());
                    setBoldText(binding.TvLeaveCount, "Leave Count: ", String.valueOf(response.body().getLeaveCount())+" ");
                    setBoldText(binding.TvLateCount, "Late Count: ", String.valueOf(response.body().getLeaveCount()));
                    setBoldText(binding.TvTotalOverTime, "Total OverTime: ", response.body().getTotalOverTime()+" ");
                    setBoldText(binding.TvTotalWorkedTime, "Total Worked Time: ", response.body().getTotalWorkedTime());


//                    binding.TvRunBy.setText("Name: " + response.body().getRunBy());
//                    binding.TvLeaveCount.setText("Leave Count: " + response.body().getLeaveCount());
//                    binding.TvLateCount.setText("Late Count: " + response.body().getLeaveCount());
//                    binding.TvTotalOverTime.setText("Total OverTime: " + response.body().getTotalOverTime());
//                    binding.TvTotalWorkedTime.setText("Total Worked Time:" + response.body().getTotalWorkedTime());
                    binding.TvName.setText("" + response.body().getCompanyHeader().getName());
                    binding.TvAddress.setText("" + response.body().getCompanyHeader().getAddress());
                    binding.TvEmail.setText("" + response.body().getCompanyHeader().getEmail());


                    AttendanceReport.clear();
                    AttendanceReport.addAll(response.body().getAttendanceReport());
//                       Log.d("Reports:", s);
                    attandanceReportAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<CheckAttendanceResponse> call, Throwable t) {
                // Holidayres.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setBoldText(TextView textView, String boldText, String regularText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString boldString = new SpannableString(boldText);
        boldString.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(boldString);
        builder.append(regularText);
        textView.setText(builder);
    }

    private void AtdrReportRecyclerView() {
        try {
            attandanceReportAdapter = new AttandanceReportAdapter(AttendanceReport, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            binding.rvAtdReport.setLayoutManager(layoutManager);
            binding.rvAtdReport.setAdapter(attandanceReportAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CheckAttendanceViewModel.class);
        // TODO: Use the ViewModel
    }

}