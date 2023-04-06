package com.cse.hrcap.ui.loan;

import static com.cse.hrcap.MainActivity.loanSubTypeRoomDB;
import static com.cse.hrcap.MainActivity.loanTypeRoomDB;

import androidx.lifecycle.ViewModelProvider;

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

import com.cse.hrcap.RoomLoanSubType.LoanSubTypeRoomDB;
import com.cse.hrcap.RoomLoanType.LoanTypeRoomDB;
import com.cse.hrcap.databinding.LoanAdvSalaryFragmentBinding;

import java.util.List;

public class LoanAdvSalaryFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private LoanAdvSalaryViewModel mViewModel;
    private LoanAdvSalaryFragmentBinding binding;
    Spinner loantype,loansubtype;
    TextView Loantyperesponse,Loansubtyperesponse;
    public LoanTypeRoomDB roomDB;
    public LoanSubTypeRoomDB roomDBs;

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

//        loansubTypes();
//        setDatabasetwo();
        loadSpinnerDataone();
        loadSpinnerDatatwo();
        return binding.getRoot();
    }






//    private void loansubTypes() {
//        Intent intent = getActivity().getIntent();
//        String companyid = intent.getStringExtra("CompanyId");
//        Call<List<LoansubTypeResponse>> call = LoanApiClient.getUserService().loansubtype(companyid);
//
//
//
//        call.enqueue(new Callback<List<LoansubTypeResponse>>() {
//            @Override
//            public void onResponse(Call<List<LoansubTypeResponse>> call, Response<List<LoansubTypeResponse>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<LoansubTypeResponse> nlist = response.body();
//
//
//                    for (LoansubTypeResponse post : nlist) {
//                        String content = "";
////                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
//                        content += "Loan sub Type Name: " + post.getLoanSubTypeName() + "\n";
////                        content += "Company ID: " + post.getCompanyId() + "\n";
////                        content += "Short Name: " + post.getShortName() + "\n";
////                        content += "Description: " + post.getDescription() + "\n\n";
//                        LoanSubTypeInfo loanSubTypeInfo = new LoanSubTypeInfo(post.getLoanSubTypeName());
//                        roomDBs.loanSubTypeDAO().insertLoanSub(loanSubTypeInfo);
//
//                        Loansubtyperesponse.append(content);
//                    }
//                } else {
//                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<LoansubTypeResponse>> call, Throwable t) {
//                Loantyperesponse.setText(t.getMessage());
//                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void loadSpinnerDataone() {
        List<String> labels = loanTypeRoomDB.loanTypeDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        loantype.setAdapter(dataAdapter);
    }

    private void loadSpinnerDatatwo() {
        List<String> labels = loanSubTypeRoomDB.loanSubTypeDAO().getAllName();

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

//    private void setDatabasetwo(){
//        roomDBs = Room.databaseBuilder(requireContext(), LoanSubTypeRoomDB.class,"Loansubtype.db")
//                .allowMainThreadQueries().build();
//    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoanAdvSalaryViewModel.class);
        // TODO: Use the ViewModel
    }


}