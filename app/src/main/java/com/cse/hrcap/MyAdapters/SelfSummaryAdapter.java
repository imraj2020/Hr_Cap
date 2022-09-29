package com.cse.hrcap.MyAdapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomSelfSummary.SelfInfo;

import java.util.List;

public class SelfSummaryAdapter extends RecyclerView.Adapter<SelfSummaryAdapter.ViewHolder> {

    private final List<SelfInfo> list;
    private Context context;

    public SelfSummaryAdapter(List<SelfInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.selfsummarycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelfInfo data = list.get(position);

        holder.CheckInDate.setText("CheckInDate: "+data.getCheckindate());
        holder.PunchTime.setText("PunchTime: "+data.getPunchtime());
        holder.InOut.setText("InOut: "+data.getInout());
        holder.EntryBy.setText("EntryBy: "+data.getEntryby());
        holder.EntryDate.setText("EntryDate: "+data.getEntrydate()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView CheckInDate,PunchTime,InOut,EntryBy,EntryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CheckInDate = (TextView)itemView.findViewById(R.id.tv_CheckInDate);
            PunchTime = (TextView)itemView.findViewById(R.id.tv_PunchTime);
            InOut = (TextView)itemView.findViewById(R.id.tv_InOut);
            EntryBy = (TextView)itemView.findViewById(R.id.tv_EntryBy);
            EntryDate = (TextView)itemView.findViewById(R.id.tv_EntryDate);


        }
    }
}
