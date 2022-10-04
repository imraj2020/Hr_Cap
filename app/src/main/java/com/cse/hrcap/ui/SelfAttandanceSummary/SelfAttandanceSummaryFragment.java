package com.cse.hrcap.ui.SelfAttandanceSummary;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveSummaryAdapter;
import com.cse.hrcap.MyAdapters.SelfSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;
import com.cse.hrcap.RoomSelfSummary.SelfRoomDB;
import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;
import com.cse.hrcap.databinding.SelfAttandanceSummaryFragmentBinding;
import com.cse.hrcap.network.AttdanceSummary;
import com.cse.hrcap.network.LeaveApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelfAttandanceSummaryFragment extends Fragment {

    private SelfAttandanceSummaryViewModel mViewModel;
    SelfAttandanceSummaryFragmentBinding binding;
    RecyclerView SelfLv;
    List<SelfInfo> arrayList;
    public static SelfRoomDB selfRoomDB;
    SwipeRefreshLayout slSwipeRefreshLayout;

    public static SelfAttandanceSummaryFragment newInstance() {
        return new SelfAttandanceSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SelfAttandanceSummaryFragmentBinding.inflate(inflater);
        SelfLv = binding.selflistview;
        arrayList = new ArrayList<>();
// SwipeRefreshLayout
        slSwipeRefreshLayout = binding.slswipe;
        slSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        slSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                slSwipeRefreshLayout.post(new Runnable() {
                                             @Override
                                             public void run() {
                                                 setAttandanceSummaryDatabase();
                                                 Attdancesummary();
                                                 Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                 slSwipeRefreshLayout.setEnabled(false);
                                             }
                                         }
                );

            }

        });

        setAttandanceSummaryDatabase();
        Attdancesummary();
        loaddatainlistview();

        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = selfRoomDB.selfDAO().getAllSelfSummary();
        SelfSummaryAdapter adapter = new SelfSummaryAdapter(arrayList, requireContext());
        SelfLv.setLayoutManager(new LinearLayoutManager(requireContext()));
        SelfLv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    //Self Attandance summary
    public void Attdancesummary() {
        Intent intent = getActivity().getIntent();
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
                    loaddatainlistview();
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttdanceSummary>> call, Throwable t) {
                Toast.makeText(requireContext(), "" + t, Toast.LENGTH_SHORT).show();
                // Log.d("response",t);
            }
        });


    }

    public void setAttandanceSummaryDatabase() {
        selfRoomDB = Room.databaseBuilder(requireContext(), SelfRoomDB.class, "AttandanceSummary.db")
                .allowMainThreadQueries().build();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelfAttandanceSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}