package com.cse.hrcap.MyAdapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
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
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LeaveAprSumAdapter extends RecyclerView.Adapter<LeaveAprSumAdapter.ViewHolder> {

    private List<LeaveAprSumInfo> list;
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

    public void setData(List<LeaveAprSumInfo> data) {
        // sort the list in reverse order before setting it
        Collections.reverse(data);
        list = data;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       // LeaveAprSumInfo data = list.get(position);
        LeaveAprSumInfo data = list.get(list.size() - position - 1);

//        if (data.getLeavestatusname().equals("Pending")) {
//            holder.MyCardView.setVisibility(View.VISIBLE);
//
//            ViewGroup.LayoutParams params = holder.MyCardView.getLayoutParams();
//            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//            holder.MyCardView.setLayoutParams(params);
//
//        } else {
//            holder.MyCardView.setVisibility(View.GONE);
//            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.MyCardView.getLayoutParams();
//            lp.height = 0;
//            lp.width = 0;
//            lp.setMargins(0, 0, 0, 0);
//            holder.MyCardView.setLayoutParams(lp);
//            holder.MyCardView.setPadding(0, 0, 0, 0);
//        }


        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }



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


        setBoldText(holder.FullName, "Full Name: ", data.getFullname());
        setBoldText(holder.EmpCode, "(", data.getEmpcode()+")");
        setBoldText(holder.EntryDate, "Entry Date: ", data.getEntrydate());
        setBoldText(holder.LeaveTypeName, "Leave Type: ", data.getLeavetypename());
        setBoldText(holder.LeaveId, "Leave Id: ", data.getLeaveid());
        setBoldText(holder.FromDate, "From Date: ", data.getFromdate());
        setBoldText(holder.ToDate, "To Date: ", data.getTodate());
        setBoldText(holder.FromTime, "From Time: ", data.getFromtime()+"");
        setBoldText(holder.ToTime, "To Time: ", data.getTotime()+"");
        setBoldText(holder.TotalHours, "Total Hours: ", data.getTotalhours());
        setBoldText(holder.LeaveStatusName, "Status: ", data.getLeavestatusname());







//        holder.FullName.setText("Name: "+data.getFullname());
//        holder.EmpCode.setText("("+data.getEmpcode()+")");
//        holder.EntryDate.setText("Entry Date: "+EntryDate);
//        holder.LeaveTypeName.setText("Leave Type: "+data.getLeavetypename());
//        holder.LeaveId.setText("Leave Id: "+data.getLeaveid());
//        holder.FromDate.setText("From Date: "+FromDate);
//        holder.ToDate.setText("To Date: "+ToDate);
//        holder.FromTime.setText("From Time: "+data.getFromtime());
//        holder.ToTime.setText("To Time: "+data.getTotime());
//        holder.TotalHours.setText("Total Hours: "+data.getTotalhours());
//        holder.LeaveStatusName.setText("Status: "+data.getLeavestatusname()+"\n");
//        holder.CompanyId.setText("CompanyId: "+data.getCompanyid());
//        holder.EmpId.setText("EmpId: "+Integer.toString(data.getEmpid()));
//        holder.LeaveTypeId.setText("LeaveTypeId: "+Integer.toString(data.getLeavetypeid()));
//        holder.TotalDay.setText("TotalDay: "+data.getTotalday());
//        holder.Leavestatusid.setText("Leavestatusid: "+Integer.toString(data.getLeavestatusid()));
//        holder.IndivRequestStatus.setText("IndivRequestStatus: "+Integer.toString(data.getIndivrequeststatus()));
//        holder.IndivRequestStatusName.setText("IndivRequestStatusName: "+data.getIndivrequeststatusname());
//        holder.EntryBy.setText("EntryBy: "+data.getEntryby());


        //Button Checking
        holder.BtnApproval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveAprSumInfo data = list.get(list.size() - position - 1);

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



    private void setBoldText(TextView textView, String boldText, String regularText) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableString boldString = new SpannableString(boldText);
        boldString.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(boldString);
        builder.append(regularText);
        textView.setText(builder);
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
