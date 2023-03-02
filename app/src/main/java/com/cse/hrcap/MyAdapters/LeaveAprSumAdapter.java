package com.cse.hrcap.MyAdapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.LeaveApproval;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }

        // Entry Date
        String inputDateString1 = data.getEntrydate();
        SimpleDateFormat inputDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
        Date inputDate1 = null;
        try {
            inputDate1 = inputDateFormat1.parse(inputDateString1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputDateFormat1 = new SimpleDateFormat("dd/MM/yy");
        String EntryDate = outputDateFormat1.format(inputDate1);



        // From Date
        String inputDateString2 = data.getFromdate();
        SimpleDateFormat inputDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        Date inputDate2 = null;
        try {
            inputDate1 = inputDateFormat1.parse(inputDateString1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputDateFormat2 = new SimpleDateFormat("dd/MM/yy");
        String FromDate = outputDateFormat2.format(inputDate1);


        // To Date
        String inputDateString3 = data.getTodate();
        SimpleDateFormat inputDateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date inputDate3 = null;
        try {
            inputDate3 = inputDateFormat3.parse(inputDateString1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputDateFormat3 = new SimpleDateFormat("dd/MM/yy");
        String ToDate = outputDateFormat3.format(inputDate1);




        holder.FullName.setText("Name: "+data.getFullname());
        holder.EmpCode.setText("("+data.getEmpcode()+")");
        holder.EntryDate.setText("Entry Date: "+EntryDate);
        holder.LeaveTypeName.setText("Leave Type: "+data.getLeavetypename());
        holder.LeaveId.setText("Leave Id: "+data.getLeaveid());
        holder.FromDate.setText("From Date: "+FromDate);
        holder.ToDate.setText("To Date: "+ToDate);
        holder.FromTime.setText("From Time: "+data.getFromtime());
        holder.ToTime.setText("To Time: "+data.getTotime());
        holder.TotalHours.setText("Total Hours: "+data.getTotalhours());
        holder.LeaveStatusName.setText("Status: "+data.getLeavestatusname()+"\n");




        holder.CompanyId.setText("CompanyId: "+data.getCompanyid());
        holder.EmpId.setText("EmpId: "+Integer.toString(data.getEmpid()));

        holder.LeaveTypeId.setText("LeaveTypeId: "+Integer.toString(data.getLeavetypeid()));


        holder.TotalDay.setText("TotalDay: "+data.getTotalday());


        holder.Leavestatusid.setText("Leavestatusid: "+Integer.toString(data.getLeavestatusid()));


        holder.IndivRequestStatus.setText("IndivRequestStatus: "+Integer.toString(data.getIndivrequeststatus()));
        holder.IndivRequestStatusName.setText("IndivRequestStatusName: "+data.getIndivrequeststatusname());
        holder.EntryBy.setText("EntryBy: "+data.getEntryby());


        //Button Checking
        holder.BtnApproval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveAprSumInfo data = list.get(position);

                String CompanyId = data.getCompanyid();
                String LeaveId = data.getLeaveid();

                Intent intent = new Intent(context, LeaveApproval.class);
                intent.putExtra("MCompanyId",CompanyId);
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
        CardView MyCardView;


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
            MyCardView = itemView.findViewById(R.id.leaveapprovalcard);

        }
    }
}
