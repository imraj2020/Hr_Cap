package com.cse.hrcap.MyAdapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.LeaveDraft;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.ui.LeaveDraft.LeaveDraftFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LeaveDraftAdapter extends RecyclerView.Adapter<LeaveDraftAdapter.ViewHolder> {

    private final List<LeaveDraftInfo> list;
    private Context context;

    public LeaveDraftAdapter(List<LeaveDraftInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leavedraftcustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LeaveDraftInfo data = list.get(position);

        //test
//        holder.bindData(data);
//        holder.itemView.setOnClickListener(v -> {
//            if (onDataClickListener != null) {
//                onDataClickListener.onDataClick(data);
//            }


        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }

        String mydate = data.getCreatedate();
        String[] parts = mydate.split(",");
        String datePart = parts[1].trim();

        // System.out.println(datePart);


        holder.LeaveId.setText("Draft Id:\n" + Integer.toString(data.getLeaveid()) + "\n");
        holder.CreateTime.setText("Entry Time: " + datePart + "\n");
        holder.LeaveType.setText("Leave Type: " + data.getLeavetype() + "\n");
        holder.DayType.setText("Day Type: " + "\n" + data.getDaytype() + "\n");
        // holder.getAdapterPosition();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isNetworkAvailable()) {

                    LeaveDraftInfo data = list.get(position);
                    int nposition = data.getLeaveid();

                    LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(context.getApplicationContext());

                    db.leaveDraftDAO().deleteLeavedraftinfo(nposition);
                    // remove your item from data base
                    Toast.makeText(context, "removed" + nposition, Toast.LENGTH_SHORT).show();
                    list.remove(position);  // remove the item from list
                    notifyItemRemoved(position); // notify the adapter about the removed item
                } else {
                    Toast.makeText(context, "No internet connection available", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
        //                            i.putExtra("Employee", userid);


        holder.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveDraftInfo data = list.get(position);
                int nposition = data.getLeaveid();
                Bundle bundle = new Bundle();
                bundle.putInt("clicked_data_", nposition);
                //  Toast.makeText(context, ""+nposition, Toast.LENGTH_SHORT).show();
                // Navigation.findNavController(v).navigate(R.id.nav_leavedraftf);

                Navigation.findNavController(v).navigate(R.id.nav_leavedraftf, bundle);

            }
        });


        //Toast.makeText(context, ""+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView LeaveId, CreateTime, LeaveType, DayType, full;
        ImageButton delete, view;
        CardView MyCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
            view = (ImageButton) itemView.findViewById(R.id.view);
            LeaveId = (TextView) itemView.findViewById(R.id.leave_ids);
            CreateTime = (TextView) itemView.findViewById(R.id.create_times);
            LeaveType = (TextView) itemView.findViewById(R.id.leave_types);
            DayType = (TextView) itemView.findViewById(R.id.day_types);
            MyCardView = itemView.findViewById(R.id.leavedraftcard);

        }
    }
}
