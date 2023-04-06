package com.cse.hrcap.ui.holiday;


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

import com.cse.hrcap.MyAdapters.HolidayAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomHoliday.HolidayRoomDB;
import com.cse.hrcap.databinding.HolidayFragmentBinding;
import com.cse.hrcap.network.HolidayResponse;
import com.cse.hrcap.network.MyApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayFragment extends Fragment {

    private HolidayViewModel mViewModel;
    private HolidayFragmentBinding binding;
    RecyclerView Holidayrec;
    List<HolidayInfo> arrayList;
    public static HolidayRoomDB holidayRoomDB;
    SwipeRefreshLayout hSwipeRefreshLayout;



    public static HolidayFragment newInstance() {
        return new HolidayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HolidayFragmentBinding.inflate(inflater);
        Holidayrec = binding.mylistview;
        arrayList = new ArrayList<>();

// SwipeRefreshLayout
        hSwipeRefreshLayout = binding.hswipe;
        hSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        hSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                hSwipeRefreshLayout.post(new Runnable() {
                                             @Override
                                             public void run() {
                                                 setholidayDatabase();
                                                 holidayTypes();
                                                 Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                 hSwipeRefreshLayout.setEnabled(false);
                                             }
                                         }
                );

            }

        });


        // holiday api will call once if database table is empty
        setholidayDatabase();
        holidayTypes();
        loaddatainlistview();

        return binding.getRoot();
    }

    public void loaddatainlistview() {
        arrayList = holidayRoomDB.holidayDAO().getAllHoliday();
        int size = arrayList.size();
        binding.totalresult3.setText(Integer.toString(size));
        HolidayAdapter adapter = new HolidayAdapter(arrayList, requireContext());
        Holidayrec.setLayoutManager(new LinearLayoutManager(requireContext()));
        Holidayrec.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    public void holidayTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<HolidayResponse>> call = MyApiClient.getUserService().holiday(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<HolidayResponse>>() {
            @Override
            public void onResponse(Call<List<HolidayResponse>> call, Response<List<HolidayResponse>> response) {

                if (response.isSuccessful()) {

                    List<HolidayResponse> nlist = response.body();


                    for (HolidayResponse post : nlist) {
                        String content = "";
                        content += "Holiday ID: " + post.getHolidayId() + "\n";
                        content += "Company ID: " + post.getCompanyId()+ "\n";
                        content += "Holiday name: " + post.getHolidayName()+ "\n";
                        content += "Short Name: " + post.getShortName()+ "\n";
                        content += "ReligionSpecific : " + post.getReligionSpecific() + "\n";
                        content += "Religion ID: " + post.getReligionId()+ "\n";
                        content += "Religion Name: " + post.getReligionName()+ "\n";
                        content += "Type ID: " + post.getTypeId()+ "\n";
                        content += "Type Name: " + post.getTypeName()+ "\n";
                        content += "Description: " + post.getDescription() + "\n";
                        content += "Active: " + post.getActive()+ "\n";
                        content += "EveryYearSameMonthDay : " + post.getEveryYearSameMonthDay()+ "\n\n";

                        HolidayInfo holidayInfo = new HolidayInfo(post.getHolidayId(),post.getCompanyId(),post.getHolidayName(),
                                post.getShortName(),post.getReligionSpecific(),post.getReligionId(),post.getReligionName(),
                                post.getTypeId(),post.getTypeName(),post.getDescription(),post.getActive(),post.getEveryYearSameMonthDay());
                        holidayRoomDB.holidayDAO().insertHoliday(holidayInfo);

                       // Holidayres.append(content);
                    }
                    loaddatainlistview();
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HolidayResponse>> call, Throwable t) {
               // Holidayres.setText(t.getMessage());
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HolidayViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setholidayDatabase(){
        holidayRoomDB = Room.databaseBuilder(requireContext(), HolidayRoomDB.class,"Holidayinfo.db")
                .allowMainThreadQueries().build();
    }

}