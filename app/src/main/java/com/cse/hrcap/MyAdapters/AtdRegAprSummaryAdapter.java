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
import com.cse.hrcap.RegularizationApproval;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AtdRegAprSumInfo data = list.get(position);

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }



        holder.MovementId.setText("MovementId: " + data.getMovementid());
        holder.CompanyId.setText("CompanyId: " + data.getCompanyid());
        holder.EmpId.setText("EmpId: " + Integer.toString(data.getEmpid()));
        holder.EmpCode.setText("(" + data.getEmpcode()+")");
        holder.FullName.setText("FullName: " + data.getFullname());

        holder.StartDate.setText("StartDate: " + data.getStartdate());
        holder.EndDate.setText("EndDate: " + data.getEnddate());
        holder.Reason.setText("Reason: " + data.getReason());
        holder.Note.setText("Note: " + data.getNote());
        holder.FromTime.setText("FromTime: " + data.getFromtime());
        holder.ToTime.setText("ToTime: " + data.getTotime());
        holder.Status.setText("Status: " + data.getStatus());
        holder.EntryBy.setText("EntryBy: " + data.getEntryby());
        holder.EntryDate.setText("EntryDate: " + data.getEntrydate() + "\n");

        //Button Checking
        holder.BtnApproval.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AtdRegAprSumInfo data = list.get(position);

                String CompanyId = data.getCompanyid();
                String Employee = data.getEntryby();
                String MovementId = data.getMovementid();
                String FromTime = data.getFromtime();
                String ToTime = data.getTotime();


                Intent intent = new Intent(context, RegularizationApproval.class);
                intent.putExtra("ICompanyId",CompanyId);
                intent.putExtra("IMovementId",MovementId);
                intent.putExtra("IFromTime",FromTime);
                intent.putExtra("IToTime",ToTime);
                context.startActivity(intent);




                // remove your item from data base
             //   Toast.makeText(context, " "+CompanyId+" "+Employee+" "+LeaveId, Toast.LENGTH_SHORT).show();


            }
        });
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
            BtnApproval = (Button)itemView.findViewById(R.id.btnAprove);
            MyCardView = itemView.findViewById(R.id.atdregapprovalcard);
        }
    }
}
