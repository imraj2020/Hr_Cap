package com.cse.hrcap.ui.holiday;

import static com.cse.hrcap.MainActivity.holidayRoomDB;

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

import com.cse.hrcap.MyAdapters.HolidayAdapter;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.databinding.HolidayFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class HolidayFragment extends Fragment {

    private HolidayViewModel mViewModel;
    private HolidayFragmentBinding binding;
    RecyclerView Holidayrec;
    List<HolidayInfo> arrayList;



    public static HolidayFragment newInstance() {
        return new HolidayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HolidayFragmentBinding.inflate(inflater);
        Holidayrec = binding.mylistview;
        arrayList = new ArrayList<>();




//        holidayTypes();
//        setDatabase();
        loaddatainlistview();
        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = holidayRoomDB.holidayDAO().getAllHoliday();
        HolidayAdapter adapter = new HolidayAdapter(arrayList, requireContext());
        Holidayrec.setLayoutManager(new LinearLayoutManager(requireContext()));
        Holidayrec.setAdapter(adapter);
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

//    public void holidayTypes() {
//        Intent intent = getActivity().getIntent();
//        String companyid = intent.getStringExtra("CompanyId");
//        Call<List<HolidayResponse>> call = LeaveApiClient.getUserService().holiday(companyid);
//        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);
//
//
//        call.enqueue(new Callback<List<HolidayResponse>>() {
//            @Override
//            public void onResponse(Call<List<HolidayResponse>> call, Response<List<HolidayResponse>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<HolidayResponse> nlist = response.body();
//
//
//                    for (HolidayResponse post : nlist) {
//                        String content = "";
//                        content += "Holiday ID: " + post.getHolidayId() + "\n";
//                        content += "Company ID: " + post.getCompanyId()+ "\n";
//                        content += "Holiday name: " + post.getHolidayName()+ "\n";
//                        content += "Short Name: " + post.getShortName()+ "\n";
//                        content += "ReligionSpecific : " + post.getReligionSpecific() + "\n";
//                        content += "Religion ID: " + post.getReligionId()+ "\n";
//                        content += "Religion Name: " + post.getReligionName()+ "\n";
//                        content += "Type ID: " + post.getTypeId()+ "\n";
//                        content += "Type Name: " + post.getTypeName()+ "\n";
//                        content += "Description: " + post.getDescription() + "\n";
//                        content += "Active: " + post.getActive()+ "\n";
//                        content += "EveryYearSameMonthDay : " + post.getEveryYearSameMonthDay()+ "\n\n";
//
//                        HolidayInfo holidayInfo = new HolidayInfo(post.getHolidayId(),post.getCompanyId(),post.getHolidayName(),
//                                post.getShortName(),post.getReligionSpecific(),post.getReligionId(),post.getReligionName(),
//                                post.getTypeId(),post.getTypeName(),post.getDescription(),post.getActive(),post.getEveryYearSameMonthDay());
//                        roomDB.holidayDAO().insertHoliday(holidayInfo);
//
//                       // Holidayres.append(content);
//                    }
//                } else {
//                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<HolidayResponse>> call, Throwable t) {
//                Holidayres.setText(t.getMessage());
//                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HolidayViewModel.class);
        // TODO: Use the ViewModel
    }

//    public void setDatabase(){
//        roomDB = Room.databaseBuilder(requireContext(), HolidayRoomDB.class,"Holidayinfo.db")
//                .allowMainThreadQueries().build();
//    }

}