package com.cse.hrcap.ui.SelfAttandanceSummary;

import static com.cse.hrcap.MainActivity.leaveSummaryRoomDB;
import static com.cse.hrcap.MainActivity.selfRoomDB;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.MyAdapters.LeaveSummaryAdapter;
import com.cse.hrcap.MyAdapters.SelfSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;
import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;
import com.cse.hrcap.databinding.SelfAttandanceSummaryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class SelfAttandanceSummaryFragment extends Fragment {

    private SelfAttandanceSummaryViewModel mViewModel;
    SelfAttandanceSummaryFragmentBinding binding;
    RecyclerView SelfLv;
    List<SelfInfo> arrayList;

    public static SelfAttandanceSummaryFragment newInstance() {
        return new SelfAttandanceSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SelfAttandanceSummaryFragmentBinding.inflate(inflater);
        SelfLv = binding.selflistview;
        arrayList = new ArrayList<>();

        loaddatainlistview();

        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = selfRoomDB.selfDAO().getAllSelfSummary();
        SelfSummaryAdapter adapter = new SelfSummaryAdapter(arrayList, requireContext());
        SelfLv.setLayoutManager(new LinearLayoutManager(requireContext()));
        SelfLv.setAdapter(adapter);
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelfAttandanceSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}