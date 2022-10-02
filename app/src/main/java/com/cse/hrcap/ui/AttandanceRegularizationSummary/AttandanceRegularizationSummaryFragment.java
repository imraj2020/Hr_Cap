package com.cse.hrcap.ui.AttandanceRegularizationSummary;

import static com.cse.hrcap.MainActivity.atdRegRoomDB;
import static com.cse.hrcap.MainActivity.selfRoomDB;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.MainActivity;
import com.cse.hrcap.MyAdapters.AtdRegSummaryAdapter;
import com.cse.hrcap.MyAdapters.SelfSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.RoomSelfSummary.SelfRoomDB;
import com.cse.hrcap.databinding.AttandanceRegularizationSummaryFragmentBinding;
import com.cse.hrcap.databinding.LeaveSummaryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class AttandanceRegularizationSummaryFragment extends Fragment {

    private AttandanceRegularizationSummaryViewModel mViewModel;
    AttandanceRegularizationSummaryFragmentBinding binding;
    List<AtdRegInfo> arrayList;
    RecyclerView recyclerView;

    public static AttandanceRegularizationSummaryFragment newInstance() {
        return new AttandanceRegularizationSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AttandanceRegularizationSummaryFragmentBinding.inflate(inflater);

        recyclerView = binding.atdregsummary;

        arrayList = new ArrayList<>();

        
// Call An Api From Outside
//        MainActivity main = new MainActivity();
//
//        main.Attdanceregsummary();



        loaddatainlistview();
        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = atdRegRoomDB.atdRegDAO().getAllRegSummary();
        AtdRegSummaryAdapter adapter = new AtdRegSummaryAdapter(arrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttandanceRegularizationSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}