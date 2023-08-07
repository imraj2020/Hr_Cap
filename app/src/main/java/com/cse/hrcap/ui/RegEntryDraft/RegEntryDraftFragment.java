package com.cse.hrcap.ui.RegEntryDraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cse.hrcap.MyAdapters.LeaveDraftAdapter;
import com.cse.hrcap.MyAdapters.RegEntryDraftAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;
import com.cse.hrcap.databinding.RegEntryDraftFragmentBinding;

import java.util.List;

public class RegEntryDraftFragment extends Fragment {

    private RegEntryDraftViewModel mViewModel;
    RegEntryDraftFragmentBinding binding;
    List<RegDraftInfo> arrayList;
    RecyclerView recyclerView;
    SwipeRefreshLayout ldSwipeRefreshLayout;

    public static RegEntryDraftFragment newInstance() {
        return new RegEntryDraftFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegEntryDraftFragmentBinding.inflate(inflater);
        recyclerView = binding.regdraftsummary;

        // SwipeRefreshLayout
        ldSwipeRefreshLayout = binding.mySwipe;
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
                                                      Toast.makeText(requireContext(), "Something wrong" + e, Toast.LENGTH_SHORT).show();
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

        try {
            RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(requireContext());
            arrayList = db.regDraftDAO().getAllRegDraft();
            int size = arrayList.size();
            if (size == 0) {
                binding.TvNoData.setVisibility(View.VISIBLE);
            }
            binding.totalItem2.setText(Integer.toString(size));
            RegEntryDraftAdapter adapter = new RegEntryDraftAdapter(arrayList, requireContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Crash Detected", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegEntryDraftViewModel.class);
        // TODO: Use the ViewModel
    }

}