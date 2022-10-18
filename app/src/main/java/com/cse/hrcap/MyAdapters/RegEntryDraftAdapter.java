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
import com.cse.hrcap.RegularizationDraft;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;

import java.util.List;

public class RegEntryDraftAdapter extends RecyclerView.Adapter<RegEntryDraftAdapter.ViewHolder> {

    private final List<RegDraftInfo> list;
    private Context context;

    public RegEntryDraftAdapter(List<RegDraftInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.regdraftcustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RegDraftInfo data = list.get(position);


        holder.CreateId.setText("Create Id: "+Integer.toString(data.getId())+"\n");
        holder.CreateTime.setText("Create Time: "+data.getCreatedate()+"\n");
        holder.Reason.setText("Leave Type: "+data.getReason()+"\n");


        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RegDraftInfo data = list.get(position);
                int nposition = data.getId();

                RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(context.getApplicationContext());

                db.regDraftDAO().deleteRegdraftinfo(nposition);
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
                RegDraftInfo data = list.get(position);
                int nposition = data.getId();

                Intent i = new Intent(context, RegularizationDraft.class);
                i.putExtra("DataPosition", nposition);
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

        TextView CreateId,CreateTime,Reason;
        ImageButton delete,view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            delete =(ImageButton)itemView.findViewById(R.id.btn_delete);
            view =(ImageButton)itemView.findViewById(R.id.btn_view);
            CreateId = (TextView)itemView.findViewById(R.id.tv_Create_id);
            CreateTime = (TextView)itemView.findViewById(R.id.tv_create_times);
            Reason = (TextView)itemView.findViewById(R.id.tv_reasons);



        }
    }
}
