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
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.List;

public class LeaveBalanceAdapter extends RecyclerView.Adapter<LeaveBalanceAdapter.ViewHolder> {

    private final List<LeaveBalanceInfo> list;
    private Context context;

    public LeaveBalanceAdapter(List<LeaveBalanceInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leavebalancecustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaveBalanceInfo data = list.get(position);

        holder.CompanyId.setText("CompanyId: "+data.getCompanyid());
        holder.EmployeeId.setText("EmployeeId: "+Integer.toString(data.getEmployeeid()));
        holder.LeaveTypeId.setText("LeaveTypeId: "+Integer.toString(data.getLeavetypeid()));
        holder.LeaveTypeName.setText("LeaveTypeName: "+data.getLeavetypename());
        holder.TakenLeave.setText("TakenLeave: "+Float.toString(data.getTakenleave()));
        holder.TotalLeave.setText("TotalLeave: "+Float.toString(data.getTotalleave()));
        holder.AvailableLeave.setText("AvailableLeave: "+Float.toString(data.getAvailableleave()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView CompanyId,EmployeeId,LeaveTypeId,LeaveTypeName,TakenLeave,TotalLeave,AvailableLeave;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CompanyId = (TextView)itemView.findViewById(R.id.tvCompanyId);
            EmployeeId = (TextView)itemView.findViewById(R.id.tvEmployeeId);
            LeaveTypeId = (TextView)itemView.findViewById(R.id.tvLeaveTypeId);
            LeaveTypeName = (TextView)itemView.findViewById(R.id.tvLeaveTypeName);
            TakenLeave = (TextView)itemView.findViewById(R.id.tvTakenLeave);
            TotalLeave = (TextView)itemView.findViewById(R.id.tvTotalLeave);
            AvailableLeave = (TextView)itemView.findViewById(R.id.tvAvailableLeave);;

        }
    }
}
