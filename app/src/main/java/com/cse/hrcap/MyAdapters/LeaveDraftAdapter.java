package com.cse.hrcap.MyAdapters;




import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.LeaveDraft;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LeaveDraftInfo data = list.get(position);


        holder.LeaveId.setText("LeaveId: "+Integer.toString(data.getLeaveid())+"\n");
        holder.CreateTime.setText("Create Time: "+data.getCreatedate()+"\n");
        holder.LeaveType.setText("Leave Type: "+data.getLeavetype()+"\n");
        holder.DayType.setText("Day Type: "+"\n"+data.getDaytype()+"\n");
       // holder.getAdapterPosition();

        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveDraftInfo data = list.get(position);
                int nposition = data.getLeaveid();

                LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(context.getApplicationContext());

                db.leaveDraftDAO().deleteLeavedraftinfo(nposition);
                // remove your item from data base
                Toast.makeText(context, "removed"+nposition, Toast.LENGTH_SHORT).show();
                list.remove(position);  // remove the item from list
                notifyItemRemoved(position); // notify the adapter about the removed item

            }
        });

        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
        //                            i.putExtra("Employee", userid);


        holder.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LeaveDraftInfo data = list.get(position);
                int nposition = data.getLeaveid();

                Intent i = new Intent(context, LeaveDraft.class);
                i.putExtra("Position", nposition);
                context.startActivity(i);

            }
        });




        //Toast.makeText(context, ""+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView LeaveId,CreateTime,LeaveType,DayType,full;
        ImageButton delete,view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            delete =(ImageButton)itemView.findViewById(R.id.delete);
            view =(ImageButton)itemView.findViewById(R.id.view);
            LeaveId = (TextView)itemView.findViewById(R.id.leave_ids);
            CreateTime = (TextView)itemView.findViewById(R.id.create_times);
            LeaveType = (TextView)itemView.findViewById(R.id.leave_types);
            DayType = (TextView)itemView.findViewById(R.id.day_types);


        }
    }
}