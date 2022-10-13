package com.cse.hrcap.ui.AttandanceRegularizationSummary;

import static com.cse.hrcap.MainActivity.atdRegRoomDB;

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

import com.cse.hrcap.MainActivity;
import com.cse.hrcap.MyAdapters.AtdRegSummaryAdapter;
import com.cse.hrcap.MyAdapters.SelfSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegRoomDB;
import com.cse.hrcap.RoomSelfSummary.SelfRoomDB;
import com.cse.hrcap.databinding.AttandanceRegularizationSummaryFragmentBinding;
import com.cse.hrcap.databinding.LeaveSummaryFragmentBinding;
import com.cse.hrcap.network.AttdanceRegularizationSummary;
import com.cse.hrcap.network.LeaveApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttandanceRegularizationSummaryFragment extends Fragment {

    private AttandanceRegularizationSummaryViewModel mViewModel;
    AttandanceRegularizationSummaryFragmentBinding binding;
    List<AtdRegInfo> arrayList;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static AttandanceRegularizationSummaryFragment newInstance() {
        return new AttandanceRegularizationSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AttandanceRegularizationSummaryFragmentBinding.inflate(inflater);

        recyclerView = binding.atdregsummary;

        arrayList = new ArrayList<>();


        // SwipeRefreshLayout
        mSwipeRefreshLayout = binding.swipeContainer;
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mSwipeRefreshLayout.post(new Runnable() {
                                             @Override
                                             public void run() {
                                                 Attdanceregsummary();
                                                 setAttandanceregSummaryDatabase();
                                                 Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                 mSwipeRefreshLayout.setEnabled(false);
                                             }
                                         }
                );

            }

        });
        Attdanceregsummary();
        setAttandanceregSummaryDatabase();
        loaddatainlistview();
        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = atdRegRoomDB.atdRegDAO().getAllRegSummary();
        AtdRegSummaryAdapter adapter = new AtdRegSummaryAdapter(arrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        AtdRegSummaryAdapter.notifyDataSetChanged();

    }

    public void Attdanceregsummary() {
        Intent intent = getActivity().getIntent();
        String CompanyId = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        Call<List<AttdanceRegularizationSummary>> call = LeaveApiClient.getUserService().attdanceregsummary(CompanyId, Employee);

        call.enqueue(new Callback<List<AttdanceRegularizationSummary>>() {
            @Override
            public void onResponse(Call<List<AttdanceRegularizationSummary>> call, Response<List<AttdanceRegularizationSummary>> response) {
                if (response.isSuccessful()) {

                    List<AttdanceRegularizationSummary> nlist = response.body();


                    for (AttdanceRegularizationSummary post : nlist) {

                        AtdRegInfo atdRegInfo = new AtdRegInfo(post.getMovementId(), post.getCompanyId(), post.getEmpId(),
                                post.getEmpCode(), post.getFullName(),post.getStartDate(), post.getEndDate(),
                                post.getReason(), post.getNote(), post.getFromTime(), post.getToTime(), post.getStatus(),
                                post.getEntryBy(), post.getEntryDate());
                        atdRegRoomDB.atdRegDAO().insertAtdRegSummary(atdRegInfo);

                        //  LeaveSummary.append(content);
                    }
                    loaddatainlistview();
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AttdanceRegularizationSummary>> call, Throwable t) {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                // Log.d("response",t);
            }
        });


    }

    public void setAttandanceregSummaryDatabase() {
        atdRegRoomDB = Room.databaseBuilder(requireContext(), AtdRegRoomDB.class, "AtdregSummary.db")
                .allowMainThreadQueries().build();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttandanceRegularizationSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}