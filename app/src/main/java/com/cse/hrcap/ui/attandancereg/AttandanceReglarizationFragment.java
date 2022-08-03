package com.cse.hrcap.ui.attandancereg;

import static com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding.*;

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
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding;
import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;

public class AttandanceReglarizationFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AttandanceReglarizationViewModel mViewModel;
    private Spinner spinner_reason;
    String[] Reason;
    AttandanceReglarizationFragmentBinding binding;
    public static AttandanceReglarizationFragment newInstance() {
        return new AttandanceReglarizationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding =AttandanceReglarizationFragmentBinding.inflate(inflater);
        spinner_reason = binding.spinnerReason;


        Reason = getResources().getStringArray(R.array.Reason);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.Reason, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reason.setAdapter(adapter);
        spinner_reason.setOnItemSelectedListener(this);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttandanceReglarizationViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}