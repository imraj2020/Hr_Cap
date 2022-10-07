package com.cse.hrcap.MyAdapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.List;

public class AtdRegAprSummaryAdapter extends RecyclerView.Adapter<AtdRegAprSummaryAdapter.ViewHolder> {

    private final List<AtdRegAprSumInfo> list;
    private Context context;

    public AtdRegAprSummaryAdapter(List<AtdRegAprSumInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.atdregaprsummarycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtdRegAprSumInfo data = list.get(position);

        holder.RequestId.setText("RequestId: "+data.getRequestid());
        holder.StartDate.setText("StartDate: "+data.getStartdate());
        holder.EndDate.setText("EndDate: "+data.getEnddate());
        holder.Reason.setText("Reason: "+data.getReason());
        holder.Note.setText("Note: "+data.getNote());
        holder.FromTime.setText("FromTime: "+data.getFromtime());
        holder.ToTime.setText("ToTime: "+data.getTotime());
        holder.Status.setText("Status: "+data.getStatus());
        holder.EntryBy.setText("EntryBy: "+data.getEntryby());
        holder.EntryDate.setText("EntryDate: "+data.getEntrydate()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView RequestId,StartDate,EndDate,Reason,Note,FromTime,ToTime,Status,EntryBy,EntryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RequestId = (TextView)itemView.findViewById(R.id.t_RequestIds);
            StartDate = (TextView)itemView.findViewById(R.id.t_StartDates);
            EndDate = (TextView)itemView.findViewById(R.id.t_EndDates);
            Reason = (TextView)itemView.findViewById(R.id.t_Reasons);
            Note = (TextView)itemView.findViewById(R.id.t_Notes);
            FromTime = (TextView)itemView.findViewById(R.id.t_FromTimes);
            ToTime = (TextView)itemView.findViewById(R.id.t_ToTimes);
            Status = (TextView)itemView.findViewById(R.id.t_Statuss);
            EntryBy = (TextView)itemView.findViewById(R.id.t_EntryBys);
            EntryDate = (TextView)itemView.findViewById(R.id.t_EntryDates);

        }
    }
}
