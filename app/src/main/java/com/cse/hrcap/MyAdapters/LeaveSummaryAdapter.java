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
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.List;

public class LeaveSummaryAdapter extends RecyclerView.Adapter<LeaveSummaryAdapter.ViewHolder> {

    private final List<LeaveSummaryInfo> list;
    private Context context;

    public LeaveSummaryAdapter(List<LeaveSummaryInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leavesummarycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaveSummaryInfo data = list.get(position);


        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }

        holder.LeaveId.setText(""+data.getLeaveid());
        holder.LeaveTypeName.setText(""+data.getLeavetypename());
        holder.FromDate.setText(""+data.getFromdate());
        holder.ToDate.setText(""+data.getTodate());
        holder.TotalDay.setText(""+data.getTotalday()+"Day");
        holder.TotalHours.setText(""+data.getTotalhours());
        holder.EntryBy.setText("EntryBy: "+data.getEntryby());

        //every data is string here also the integer
        holder.EntryDateTime.setText("EntryDateTime: "+data.getEntrydatetime());
        holder.LeaveStatusId.setText("LeaveStatusId: "+data.getLeavestatusid());
        holder.LeaveStatusName.setText(""+data.getLeavestatusname()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView LeaveId,LeaveTypeName,FromDate,ToDate,TotalDay,TotalHours,EntryBy,EntryDateTime,LeaveStatusId,LeaveStatusName;
        CardView MyCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LeaveId = (TextView)itemView.findViewById(R.id.tv_LeaveId);
            LeaveTypeName = (TextView)itemView.findViewById(R.id.tv_LeaveTypeName);
            FromDate = (TextView)itemView.findViewById(R.id.tv_FromDate);
            ToDate = (TextView)itemView.findViewById(R.id.tv_ToDate);
            TotalDay = (TextView)itemView.findViewById(R.id.tv_TotalDay);
            TotalHours = (TextView)itemView.findViewById(R.id.tv_TotalHours);
            EntryBy = (TextView)itemView.findViewById(R.id.tv_EntryBy);
            EntryDateTime = (TextView)itemView.findViewById(R.id.tv_EntryDateTime);
            LeaveStatusId = (TextView)itemView.findViewById(R.id.tv_LeaveStatusId);
            LeaveStatusName = (TextView)itemView.findViewById(R.id.tv_LeaveStatusName);
            MyCardView = itemView.findViewById(R.id.leavesummarycard);

        }
    }
}
