package com.cse.hrcap.ui.SelfAttandanceSummary;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.cse.hrcap.MyAdapters.SelfAttendanceSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;
import com.cse.hrcap.RoomSelfSummary.SelfRoomDB;
import com.cse.hrcap.databinding.SelfAttandanceSummaryFragmentBinding;
import com.cse.hrcap.network.AttdanceSummary;
import com.cse.hrcap.network.MyApiClient;

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
    private SelfAttendanceSummaryAdapter adapter;


    public static SelfAttandanceSummaryFragment newInstance() {
        return new SelfAttandanceSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SelfAttandanceSummaryFragmentBinding.inflate(inflater);
        SelfLv = binding.selflistview;
        arrayList = new ArrayList<>();


        //test








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


//        arrayList = selfRoomDB.selfDAO().getAllSelfSummary();
//        int size = arrayList.size();
//        if(size==0){
//            binding.TvNoData.setVisibility(View.VISIBLE);
//        }
//        binding.totalresult.setText(Integer.toString(size));
//        SelfSummaryAdapter adapter = new SelfSummaryAdapter(arrayList, requireContext());
//        SelfLv.setLayoutManager(new LinearLayoutManager(requireContext()));
//        SelfLv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    //Self Attandance summary
    public void Attdancesummary() {
        Intent intent = getActivity().getIntent();
        String CompanyId = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        Call<List<AttdanceSummary>> call = MyApiClient.getUserService().attdancesummary(CompanyId, Employee);

        call.enqueue(new Callback<List<AttdanceSummary>>() {
            @Override
            public void onResponse(Call<List<AttdanceSummary>> call, Response<List<AttdanceSummary>> response) {
                if (response.isSuccessful()) {

                    List<AttdanceSummary> nlist = response.body();





                    if (!nlist.isEmpty()) {
                        binding.TvNoData.setVisibility(View.GONE);
                        SelfLv.setLayoutManager(new LinearLayoutManager(getContext()));

                        // Initialize the adapter with your list of AttdanceSummary objects
                        adapter = new SelfAttendanceSummaryAdapter(nlist);

                        // Set the adapter to the RecyclerView
                        SelfLv.setAdapter(adapter);

                                binding.totalresult.setText(Integer.toString(nlist.size()));
                    }



                    for (AttdanceSummary post : nlist) {

                        SelfInfo selfInfo = new SelfInfo(post.getCheckInDate(), post.getPunchTime(), post.getInOut()
                                , post.getEntryBy(), post.getEntryDate());
                        selfRoomDB.selfDAO().insertSelf(selfInfo);



                        //  LeaveSummary.append(content);
                    }
                    loaddatainlistview();
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttdanceSummary>> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
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