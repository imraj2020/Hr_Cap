package com.cse.hrcap.MyAdapters;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;

import java.util.Collections;
import java.util.List;

public class SelfSummaryAdapter extends RecyclerView.Adapter<SelfSummaryAdapter.ViewHolder> {

    private List<SelfInfo> list;
    private Context context;

    public SelfSummaryAdapter(List<SelfInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setData(List<SelfInfo> data) {
        // sort the list in reverse order before setting it
        Collections.reverse(data);
        list = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.selfsummarycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // SelfInfo data = list.get(position);

        SelfInfo data = list.get(list.size() - position - 1);

//        if (holder.getLayoutPosition() % 2 == 0) {
//            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
//        } else {
//            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
//        }

        String inout = data.getInout();

// Get the reference to the MyCardView
        CardView cardView = holder.MyCardView;

        // Set the color of the CardView based on the value of the inout string
        if (inout.equalsIgnoreCase("In")) {
            cardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else if (inout.equalsIgnoreCase("Out")) {
            cardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }



        holder.CheckInDate.setText(""+data.getCheckindate());
        holder.PunchTime.setText(""+data.getPunchtime());
        holder.InOut.setText(""+data.getInout());
        holder.EntryBy.setText(""+data.getEntryby());
        holder.EntryDate.setText(""+data.getEntrydate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView CheckInDate,PunchTime,InOut,EntryBy,EntryDate;
        CardView MyCardView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CheckInDate = (TextView)itemView.findViewById(R.id.tv_CheckInDate);
            PunchTime = (TextView)itemView.findViewById(R.id.tv_PunchTime);
            InOut = (TextView)itemView.findViewById(R.id.tv_InOut);
            EntryBy = (TextView)itemView.findViewById(R.id.tv_EntryBy);
            EntryDate = (TextView)itemView.findViewById(R.id.tv_EntryDate);
            MyCardView = itemView.findViewById(R.id.selfsummarycard);

        }
    }
}
