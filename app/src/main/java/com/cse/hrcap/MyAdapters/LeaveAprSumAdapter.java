package com.cse.hrcap.MyAdapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.LeaveApproval;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.List;

public class LeaveAprSumAdapter extends RecyclerView.Adapter<LeaveAprSumAdapter.ViewHolder> {

    private final List<LeaveAprSumInfo> list;
    private Context context;

    public LeaveAprSumAdapter(List<LeaveAprSumInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.leaveaprsumcustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LeaveAprSumInfo data = list.get(position);

        holder.LeaveId.setText("LeaveId: "+data.getLeaveid());
        holder.CompanyId.setText("CompanyId: "+data.getCompanyid());
        holder.EmpId.setText("EmpId: "+Integer.toString(data.getEmpid()));
        holder.EmpCode.setText("EmpCode: "+data.getEmpcode());
        holder.LeaveTypeId.setText("LeaveTypeId: "+Integer.toString(data.getLeavetypeid()));
        holder.LeaveTypeName.setText("LeaveTypeName: "+data.getLeavetypename());
        holder.FromDate.setText("FromDate: "+data.getFromdate());
        //every data is string here also the integer
        holder.ToDate.setText("ToDate: "+data.getTodate());
        holder.TotalDay.setText("TotalDay: "+data.getTotalday());
        holder.FromTime.setText("FromTime: "+data.getFromtime());
        holder.ToTime.setText("ToTime: "+data.getTotime());
        holder.TotalHours.setText("TotalHours: "+data.getTotalhours());
        holder.Leavestatusid.setText("Leavestatusid: "+Integer.toString(data.getLeavestatusid()));
        holder.LeaveStatusName.setText("LeaveStatusName: "+data.getLeavestatusname());
        holder.FullName.setText("FullName: "+data.getFullname());
        holder.IndivRequestStatus.setText("IndivRequestStatus: "+Integer.toString(data.getIndivrequeststatus()));
        holder.IndivRequestStatusName.setText("IndivRequestStatusName: "+data.getIndivrequeststatusname());
        holder.EntryBy.setText("EntryBy: "+data.getEntryby());
        holder.EntryDate.setText("EntryDate: "+data.getEntrydate()+"\n");

        //Button Checking
        holder.BtnApproval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveAprSumInfo data = list.get(position);

                String CompanyId = data.getCompanyid();
                String Employee = data.getEntryby();
                String LeaveId = data.getLeaveid();

                Intent intent = new Intent(context, LeaveApproval.class);
                intent.putExtra("MCompanyId",CompanyId);
                intent.putExtra("MEmployee",Employee);
                intent.putExtra("MLeaveId",LeaveId);
                context.startActivity(intent);




                // remove your item from data base
               // Toast.makeText(context, " "+CompanyId+" "+Employee+" "+LeaveId, Toast.LENGTH_SHORT).show();


            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView LeaveId,CompanyId,EmpId,EmpCode,LeaveTypeId,LeaveTypeName,FromDate,ToDate,TotalDay,FromTime,ToTime,
                TotalHours,Leavestatusid,LeaveStatusName,FullName,IndivRequestStatus,IndivRequestStatusName,EntryBy,
                EntryDate;
        Button BtnApproval;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            LeaveId = (TextView)itemView.findViewById(R.id.tv_lvid);
            CompanyId = (TextView)itemView.findViewById(R.id.tv_companyid);
            EmpId = (TextView)itemView.findViewById(R.id.tv_empid);
            EmpCode = (TextView)itemView.findViewById(R.id.tv_empcode);
            LeaveTypeId = (TextView)itemView.findViewById(R.id.tv_leavetypeid);
            LeaveTypeName = (TextView)itemView.findViewById(R.id.tv_leavetypename);
            FromDate = (TextView)itemView.findViewById(R.id.tv_fromdate);
            ToDate = (TextView)itemView.findViewById(R.id.tv_todate);
            TotalDay = (TextView)itemView.findViewById(R.id.tv_totalday);
            FromTime = (TextView)itemView.findViewById(R.id.tv_fromtime);
            ToTime = (TextView)itemView.findViewById(R.id.tv_totime);
            TotalHours = (TextView)itemView.findViewById(R.id.tv_totalhours);
            Leavestatusid = (TextView)itemView.findViewById(R.id.tv_leavestatusid);
            LeaveStatusName = (TextView)itemView.findViewById(R.id.tv_leavestatusname);
            FullName = (TextView)itemView.findViewById(R.id.tv_fullname);
            IndivRequestStatus = (TextView)itemView.findViewById(R.id.tv_indivrequeststatus);
            IndivRequestStatusName = (TextView)itemView.findViewById(R.id.tv_indivrequeststatusname);
            EntryBy = (TextView)itemView.findViewById(R.id.tv_entryby);
            EntryDate = (TextView)itemView.findViewById(R.id.tv_entrydate);
            BtnApproval = (Button)itemView.findViewById(R.id.btnaproval);

        }
    }
}
