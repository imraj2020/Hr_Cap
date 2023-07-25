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
import com.cse.hrcap.RegularizationApproval;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtdRegAprSummaryAdapter extends RecyclerView.Adapter<AtdRegAprSummaryAdapter.ViewHolder> {

    private List<AtdRegAprSumInfo> list;
    private Context context;


    public AtdRegAprSummaryAdapter(List<AtdRegAprSumInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }


//    public AtdRegAprSummaryAdapter(List<AtdRegAprSumInfo> arrayList ,Context context) {
//        pendingList.clear();
//        for (AtdRegAprSumInfo data : arrayList) {
//            if (data.getStatus().equals("Pending")) {
//                pendingList.add(data);
//                this.context = context;
//            }
//        }
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.atdregaprsummarycustomlv, parent, false));
    }

    public void setData(List<AtdRegAprSumInfo> data) {
        // sort the list in reverse order before setting it
        Collections.reverse(data);
        list = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // AtdRegAprSumInfo data = list.get(position);
        AtdRegAprSumInfo data = list.get(list.size() - position - 1);

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }

        String MyNote = data.getNote();
        if(MyNote==null){
            MyNote = "";
        }

        setBoldText(holder.FullName, "Full Name: ", data.getFullname());
//        setBoldText(holder.EmpCode, "(", data.getEmpcode()+")");
        holder.EmpCode.setText("(" + data.getEmpcode() + ")");
        setBoldText(holder.MovementId, "Request Id: ", data.getMovementid());
        setBoldText(holder.StartDate, "Start Date: ", data.getStartdate());
        setBoldText(holder.EndDate, "End Date: ", data.getEnddate());
        setBoldText(holder.FromTime, "From Time: ", data.getFromtime());
        setBoldText(holder.ToTime, "To Time: ", data.getTotime());
        setBoldText(holder.Reason, "Reason: ", data.getReason());
        setBoldText(holder.Status, "Status: ", data.getStatus());
        setBoldText(holder.EntryBy, "Entry By: ", data.getEntryby());
        setBoldText(holder.EntryDate, "Entry Date: ", data.getEntrydate());
        setBoldText(holder.Status, "Status: ", data.getStatus());
        setBoldText(holder.Note, "Note: ", MyNote + "");


        //Button Checking
        holder.BtnApproval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AtdRegAprSumInfo data = list.get(list.size() - position - 1);

                String CompanyId, FromTime, ToTime,fullname,empcode,requestid,startdate,enddate,
                reason,status,entryby,entrydate,note;

                CompanyId = data.getCompanyid();
                empcode = data.getEmpcode();
                requestid = data.getMovementid();
                FromTime = data.getFromtime();
                ToTime = data.getTotime();
                fullname = data.getFullname();
                startdate = data.getStartdate();
                enddate = data.getEnddate();
                reason = data.getReason();
                status = data.getStatus();
                entryby = data.getEntryby();
                entrydate = data.getEntrydate();
                note = data.getNote();




                Intent intent = new Intent(context, RegularizationApproval.class);
                intent.putExtra("ICompanyId", CompanyId);
                intent.putExtra("IMovementId", requestid);
                intent.putExtra("IFromTime", FromTime);
                intent.putExtra("IToTime", ToTime);

                intent.putExtra("Iempcode", empcode);
                intent.putExtra("Ifullname", fullname);
                intent.putExtra("Istartdate", startdate);
                intent.putExtra("Ienddate", enddate);
                intent.putExtra("Ireason", reason);
                intent.putExtra("Istatus", status);
                intent.putExtra("Ientryby", entryby);
                intent.putExtra("Ientrydate", entrydate);
                intent.putExtra("Inote", note);

                context.startActivity(intent);


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

        TextView MovementId, CompanyId, EmpId, EmpCode, FullName, StartDate, EndDate,
                Reason, Note, FromTime, ToTime, Status, EntryBy, EntryDate;
        Button BtnApproval;

        CardView MyCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MovementId = (TextView) itemView.findViewById(R.id.t_MovementId);
            CompanyId = (TextView) itemView.findViewById(R.id.t_CompanyId);
            EmpId = (TextView) itemView.findViewById(R.id.t_EmpId);
            EmpCode = (TextView) itemView.findViewById(R.id.t_EmpCode);
            FullName = (TextView) itemView.findViewById(R.id.t_FullName);
            StartDate = (TextView) itemView.findViewById(R.id.t_StartDates);
            EndDate = (TextView) itemView.findViewById(R.id.t_EndDates);
            Reason = (TextView) itemView.findViewById(R.id.t_Reasons);
            Note = (TextView) itemView.findViewById(R.id.t_Notes);
            FromTime = (TextView) itemView.findViewById(R.id.t_FromTimes);
            ToTime = (TextView) itemView.findViewById(R.id.t_ToTimes);
            Status = (TextView) itemView.findViewById(R.id.t_Statuss);
            EntryBy = (TextView) itemView.findViewById(R.id.t_EntryBys);
            EntryDate = (TextView) itemView.findViewById(R.id.t_EntryDates);
            BtnApproval = (Button) itemView.findViewById(R.id.btnAprove);
            MyCardView = itemView.findViewById(R.id.atdregapprovalcard);
        }
    }
}
