package com.cse.hrcap.ui.loan;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.databinding.LoanAdvSalaryFragmentBinding;
import com.cse.hrcap.network.LoanApiClient;
import com.cse.hrcap.network.LoanTypeResponse;
import com.cse.hrcap.network.LoansubTypeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanAdvSalaryFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private LoanAdvSalaryViewModel mViewModel;
    private LoanAdvSalaryFragmentBinding binding;
    Spinner loantype,loansubtype;
    TextView Loantyperesponse,Loansubtyperesponse;
    LoansubTypeDbHelper dbs;
    LoanTypeDbHelper dbc;

    public static LoanAdvSalaryFragment newInstance() {
        return new LoanAdvSalaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = LoanAdvSalaryFragmentBinding.inflate(inflater);
        




        loantype = binding.loantype;
        loansubtype = binding.loansubtype;
        Loantyperesponse = binding.tvloantype;
        Loansubtyperesponse = binding.tvloansubtype;
        loantype.setOnItemSelectedListener(this);
        loansubtype.setOnItemSelectedListener(this);
        loanTypes();
        loansubTypes();
        loadSpinnerDataone();
        loadSpinnerDatatwo();
        return binding.getRoot();
    }



    private void loanTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LoanTypeResponse>> call = LoanApiClient.getUserService().loantype(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LoanTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LoanTypeResponse>> call, Response<List<LoanTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LoanTypeResponse> nlist = response.body();

                   // Toast.makeText(getContext(), "List is"+nlist, Toast.LENGTH_SHORT).show();

                    final String  loantype1, loantype2, loantype3;



                    loantype1 = nlist.get(0).getLoanTypeName();
                    loantype2 = nlist.get(1).getLoanTypeName();
                    loantype3 = nlist.get(2).getLoanTypeName();



                    final List<String> list = new ArrayList<String>();
                    list.add(loantype1);
                    list.add(loantype2);
                    list.add(loantype3);




                    dbc = new LoanTypeDbHelper(requireContext());
                    Cursor cursor = dbc.alldata();
                    if (cursor.getCount() == 0) {
                        //Toast.makeText(getContext(), "Showing array list"+list, Toast.LENGTH_SHORT).show();
                        LoanTypeDbHelper LoanTypeDbHelper = new LoanTypeDbHelper(requireContext());
                        LoanTypeDbHelper.insertRecord(loantype1, loantype2, loantype3);
                    }
                    else {
                       // Toast.makeText(requireContext(), "Data Already Exist", Toast.LENGTH_SHORT).show();
                    }






                    // nlist.get(0);

//                    Log.d("myTag", "This is my message"+nlist);
//                    Toast.makeText(requireContext(), "this is list:"+nlist.get(9).getLeaveTypeName(), Toast.LENGTH_LONG).show();

                    for (LoanTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name: " + post.getLoanTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";

                        Loantyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoanTypeResponse>> call, Throwable t) {
                Loantyperesponse.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loansubTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LoansubTypeResponse>> call = LoanApiClient.getUserService().loansubtype(companyid);



        call.enqueue(new Callback<List<LoansubTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LoansubTypeResponse>> call, Response<List<LoansubTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LoansubTypeResponse> nlist = response.body();

                   // Toast.makeText(getContext(), "List is"+nlist, Toast.LENGTH_SHORT).show();

                    final String  loantype1, loantype2, loantype3,loantype4,loantype5,loantype6,loantype7;



                    loantype1 = nlist.get(0).getLoanSubTypeName();
                    loantype2 = nlist.get(1).getLoanSubTypeName();
                    loantype3 = nlist.get(2).getLoanSubTypeName();
                    loantype4 = nlist.get(3).getLoanSubTypeName();
                    loantype5 = nlist.get(4).getLoanSubTypeName();
                    loantype6 = nlist.get(5).getLoanSubTypeName();
                    loantype7 = nlist.get(6).getLoanSubTypeName();



                    final List<String> list = new ArrayList<String>();
                    list.add(loantype1);
                    list.add(loantype2);
                    list.add(loantype3);
                    list.add(loantype4);
                    list.add(loantype5);
                    list.add(loantype6);
                    list.add(loantype7);



                    dbs = new LoansubTypeDbHelper(requireContext());
                    Cursor cursor = dbs.alldata();
                    if (cursor.getCount() == 0) {
                        //  Toast.makeText(getContext(), "Showing array list"+list, Toast.LENGTH_SHORT).show();
                        LoansubTypeDbHelper LoansubTypeDbHelper = new LoansubTypeDbHelper(requireContext());
                        LoansubTypeDbHelper.insertRecord(loantype1, loantype2, loantype3,loantype4,loantype5,loantype6,loantype7);
                    }
                    else {
                      //  Toast.makeText(requireContext(), "Data Already Exist", Toast.LENGTH_SHORT).show();
                    }







                    // nlist.get(0);

//                    Log.d("myTag", "This is my message"+nlist);
//                    Toast.makeText(requireContext(), "this is list:"+nlist.get(9).getLeaveTypeName(), Toast.LENGTH_LONG).show();

                    for (LoansubTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Loan sub Type Name: " + post.getLoanSubTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";

                        Loansubtyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoansubTypeResponse>> call, Throwable t) {
                Loantyperesponse.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadSpinnerDataone() {
        LoanTypeDbHelper db = new LoanTypeDbHelper(requireContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        loantype.setAdapter(dataAdapter);
    }

    private void loadSpinnerDatatwo() {
        LoansubTypeDbHelper db = new LoansubTypeDbHelper(requireContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        loansubtype.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoanAdvSalaryViewModel.class);
        // TODO: Use the ViewModel
    }


}