package com.cse.hrcap.ui.LeaveRequestDraft;
import static com.cse.hrcap.MainActivity.leavedraftRoomDB;

import androidx.annotation.GravityInt;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveDraftAdapter;
import com.cse.hrcap.MyAdapters.SelfSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.databinding.LeaveRequestDraftFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDraftFragment extends Fragment {

    private LeaveRequestDraftViewModel mViewModel;
    LeaveRequestDraftFragmentBinding binding;
    List<LeaveDraftInfo> arrayList;
    RecyclerView recyclerView;
    SwipeRefreshLayout ldSwipeRefreshLayout;

    public static LeaveRequestDraftFragment newInstance() {
        return new LeaveRequestDraftFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = LeaveRequestDraftFragmentBinding.inflate(inflater);
        recyclerView = binding.draftsummary;

       // arrayList = new ArrayList<>();

        // SwipeRefreshLayout
        ldSwipeRefreshLayout = binding.myswipe;
        ldSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        ldSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ldSwipeRefreshLayout.post(new Runnable() {
                                              @Override
                                              public void run() {
                                                  try {
                                                      loaddatainlistview();
                                                  } catch (Exception e) {
                                                      Toast.makeText(requireContext(), "Something wrong"+e, Toast.LENGTH_SHORT).show();
                                                  }
                                                  Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                  ldSwipeRefreshLayout.setEnabled(false);
                                              }
                                          }
                );

            }

        });






            loaddatainlistview();


        return binding.getRoot();
    }

    private void loaddatainlistview() {

        LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(requireContext());
        arrayList = db.leaveDraftDAO().getAllLeaveDraft();
        int size= arrayList.size();
        if(size==0){
            binding.TvNoData.setVisibility(View.VISIBLE);
        }
        binding.totalItem1.setText(Integer.toString(size));
        LeaveDraftAdapter adapter = new LeaveDraftAdapter(arrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveRequestDraftViewModel.class);
        // TODO: Use the ViewModel
    }

}