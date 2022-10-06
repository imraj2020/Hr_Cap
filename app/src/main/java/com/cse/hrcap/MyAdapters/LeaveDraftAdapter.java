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
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaveDraftInfo data = list.get(position);

        holder.LeaveId.setText("LeaveId: "+Integer.toString(data.getLeaveid()));
        holder.CreateTime.setText("Create Time: "+data.getCreatedate());
        holder.LeaveType.setText("Leave Type: "+data.getLeavetype());
        holder.DayType.setText("Day Type: "+data.getDaytype()+"\n");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView LeaveId,CreateTime,LeaveType,DayType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LeaveId = (TextView)itemView.findViewById(R.id.leave_ids);
            CreateTime = (TextView)itemView.findViewById(R.id.create_times);
            LeaveType = (TextView)itemView.findViewById(R.id.leave_types);
            DayType = (TextView)itemView.findViewById(R.id.day_types);


        }
    }
}
