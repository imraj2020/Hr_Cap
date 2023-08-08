package com.cse.hrcap.MyAdapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.LeaveDraft;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.RegularizationDraft;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;

import java.util.List;

public class RegEntryDraftAdapter extends RecyclerView.Adapter<RegEntryDraftAdapter.ViewHolder> {

    private final List<RegDraftInfo> list;
    private Context context;

    public RegEntryDraftAdapter(List<RegDraftInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.regdraftcustomlv, parent, false));
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RegDraftInfo data = list.get(position);

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }
        String mydate = data.getCreatedate();
        String[] parts = mydate.split(",");
        String datePart = parts[1].trim();

        holder.CreateId.setText("Draft Id:\n" + Integer.toString(data.getId()) + "\n");
        holder.CreateTime.setText("Entry Time:\n" + datePart + "\n");
        holder.Reason.setText(":Reason\n" + data.getReason() + "\n");


        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                    if (isNetworkAvailable()) {
                        RegDraftInfo data = list.get(position);
                        int nposition = data.getId();

                        RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(context.getApplicationContext());

                        db.regDraftDAO().deleteRegdraftinfo(nposition);
                        // remove your item from data base
                        Toast.makeText(context, "removed" + nposition, Toast.LENGTH_SHORT).show();
                        list.remove(position);  // remove the item from list
                        notifyItemRemoved(position); // notify the adapter about the removed item
                        notifyItemRangeChanged(position, list.size()); // update the position variable

                    } else {
                        Toast.makeText(context, "No internet connection available", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
        //                            i.putExtra("Employee", userid);


        holder.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RegDraftInfo data = list.get(position);
                int nposition = data.getId();

                Bundle bundle = new Bundle();
                bundle.putInt("clicked_data_2", nposition);
                //  Toast.makeText(context, ""+nposition, Toast.LENGTH_SHORT).show();
                // Navigation.findNavController(v).navigate(R.id.nav_leavedraftf);

                Navigation.findNavController(v).navigate(R.id.nav_regentrydraftt, bundle);

            }
        });


        //Toast.makeText(context, ""+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView CreateId, CreateTime, Reason;
        ImageButton delete, view;
        CardView MyCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = (ImageButton) itemView.findViewById(R.id.btn_delete);
            view = (ImageButton) itemView.findViewById(R.id.btn_view);
            CreateId = (TextView) itemView.findViewById(R.id.tv_Create_id);
            CreateTime = (TextView) itemView.findViewById(R.id.tv_create_times);
            Reason = (TextView) itemView.findViewById(R.id.tv_reasonsa);
            MyCardView = itemView.findViewById(R.id.regdraftcard);


        }
    }
}
