package com.cse.hrcap.MyAdapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;

import java.util.List;

public class AtdRegSummaryAdapter extends RecyclerView.Adapter<AtdRegSummaryAdapter.ViewHolder> {

    private final List<AtdRegInfo> list;
    private Context context;

    public AtdRegSummaryAdapter(List<AtdRegInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.atdregsummarycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtdRegInfo data = list.get(position);

        holder.MovementId.setText("MovementId: "+data.getMovementid());
        holder.CompanyId.setText("CompanyId: "+data.getCompanyid());
        holder.EmpId.setText("EmpId: "+data.getEmpid());
        holder.EmpCode.setText("EmpCode: "+data.getEmpcode());
        holder.FullName.setText("FullName: "+data.getFullname());
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

        TextView MovementId,CompanyId,EmpId,EmpCode,FullName,StartDate,EndDate,Reason,
                Note,FromTime,ToTime,Status,EntryBy,EntryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MovementId = (TextView)itemView.findViewById(R.id.t_MovementId);
            CompanyId = (TextView)itemView.findViewById(R.id.t_CompanyId);
            EmpId = (TextView)itemView.findViewById(R.id.t_EmpId);
            EmpCode = (TextView)itemView.findViewById(R.id.t_EmpCode);
            FullName = (TextView)itemView.findViewById(R.id.t_FullName);
            StartDate = (TextView)itemView.findViewById(R.id.t_StartDate);
            EndDate = (TextView)itemView.findViewById(R.id.t_EndDate);
            Reason = (TextView)itemView.findViewById(R.id.t_Reason);
            Note = (TextView)itemView.findViewById(R.id.t_Note);
            FromTime = (TextView)itemView.findViewById(R.id.t_FromTime);
            ToTime = (TextView)itemView.findViewById(R.id.t_ToTime);
            Status = (TextView)itemView.findViewById(R.id.t_Status);
            EntryBy = (TextView)itemView.findViewById(R.id.t_EntryBy);
            EntryDate = (TextView)itemView.findViewById(R.id.t_EntryDate);

        }
    }
}
