package com.cse.hrcap.MyAdapters;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;

import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> {

    private final List<HolidayInfo> list;
    private Context context;
    int mSerialNumber = 1;

    public HolidayAdapter(List<HolidayInfo> list, Context context) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holidaycustomlv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HolidayInfo data = list.get(position);

        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }

        holder.Serial.setText(mSerialNumber + ". ");
        mSerialNumber++;


        holder.HolidayId.setText("HolidayId: " + Integer.toString(data.getHolidayid()));
        holder.CompanyId.setText("CompanyId: " + data.getCompanyid());
        holder.HolidayName.setText("" + data.getHolidayname());
        holder.ShortName.setText("ShortName: " + data.getShortname());
        holder.ReligionSpecific.setText("ReligionSpecific: " + data.getReligionspecific());
        holder.ReligionId.setText("ReligionId: " + Integer.toString(data.getReligionid()));
        holder.ReligionName.setText("ReligionName: " + data.getReligionname());

        //have some problem in data type of typeid ,sometime 1 ,sometime null
        holder.TypeId.setText("TypeId: " + data.getTypeid());
        holder.TypeName.setText("TypeName: " + data.getTypename());
        holder.Description.setText("Description: " + data.getDescription());
        holder.Active.setText("Active: " + data.getActive());
        holder.EveryYearSameMonthDay.setText("EveryYearSameMonthDay: " + data.getEveryyearsamemonthday() + "\n");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView HolidayId, CompanyId, HolidayName,ShortName,ReligionSpecific,ReligionId,ReligionName,
                TypeId,TypeName,Description,Active,EveryYearSameMonthDay,Serial;

        CardView MyCardView;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            HolidayId = (TextView)itemView.findViewById(R.id.holidayid);
            CompanyId = (TextView)itemView.findViewById(R.id.companyid);
            HolidayName = (TextView)itemView.findViewById(R.id.holidayname);
            ShortName = (TextView)itemView.findViewById(R.id.shortname);
            ReligionSpecific = (TextView)itemView.findViewById(R.id.religionspecific);
            ReligionId = (TextView)itemView.findViewById(R.id.religionid);
            ReligionName = (TextView)itemView.findViewById(R.id.religionname);
            TypeId = (TextView)itemView.findViewById(R.id.typeid);
            TypeName = (TextView)itemView.findViewById(R.id.typename);
            Description = (TextView)itemView.findViewById(R.id.description);
            Active = (TextView)itemView.findViewById(R.id.active);
            EveryYearSameMonthDay =(TextView)itemView.findViewById(R.id.everyyearsamemonthday);
            Serial = (TextView)itemView.findViewById(R.id.serial);
            MyCardView = itemView.findViewById(R.id.holidaycard);

        }
    }
}
