package com.cse.hrcap.MyAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.R;
import com.cse.hrcap.network.AttendanceReportList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AttandanceReportAdapter extends RecyclerView.Adapter<AttandanceReportAdapter.AddedReportViewHolder> {
    private final Context context;
    private final List<AttendanceReportList> items;

    public AttandanceReportAdapter(List<AttendanceReportList> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public AddedReportViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_added_report, parent, false);
        return new AddedReportViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AddedReportViewHolder holder, @SuppressLint("RecyclerView") final int position) {


        if (holder.getLayoutPosition() % 2 == 0) {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            holder.MyCardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }


        // Input date string1
        String originalText = items.get(position).getDate();
        String shortenedText = originalText.substring(0, 10);


        String inputDate = "2023-03-01T00:00:00";
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");
        String outputDate = outputFormat.format(date);
       // System.out.println(outputDate);




        setBoldText(holder.AtdDate, "Attendance Date: ", outputDate+"");
        setBoldText(holder.tvInTime, "In Time: ", items.get(position).getInTime()+" ");
        setBoldText(holder.tvOutTime, "Out Time: ", items.get(position).getOutTime()+"");
        setBoldText(holder.tvLateHour, "Late Hour: ", items.get(position).getLateHour()+" ");
        setBoldText(holder.tvOverTime, "Over Time: ", items.get(position).getOverTime()+"");
        setBoldText(holder.tvWorkedHour, "Worked Hour: ", items.get(position).getTotalWorkedHour()+" ");
        setBoldText(holder.tvBreakDuration, "Break Duration: ", items.get(position).getBreakDuration()+"");
        setBoldText(holder.tvHolidayName, "Holiday Name: ", items.get(position).getHoliDayName()+" ");
        setBoldText(holder.tvLeaveType, "Leave Type: ", items.get(position).getLeaveype()+"");
        setBoldText(holder.tvLeaveReason, "Leave Reason: ", items.get(position).getLeaveReason()+"");


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
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class AddedReportViewHolder extends RecyclerView.ViewHolder {

        TextView AtdDate, tvInTime, tvOutTime,tvLateHour,tvOverTime,tvWorkedHour,tvBreakDuration,tvHolidayName,tvLeaveType,tvLeaveReason;
        CardView MyCardView;

        public AddedReportViewHolder(View itemView) {
            super(itemView);
            AtdDate = itemView.findViewById(R.id.tvAtdDate);
            tvInTime = itemView.findViewById(R.id.tvInTime);
            tvOutTime = itemView.findViewById(R.id.tvOutTime);
            tvLateHour = itemView.findViewById(R.id.tvLateHour);
            tvOverTime = itemView.findViewById(R.id.tvOverTime);
            tvWorkedHour = itemView.findViewById(R.id.tvTotalWorkedHour);
            tvBreakDuration = itemView.findViewById(R.id.tvBreakDuration);
            tvHolidayName = itemView.findViewById(R.id.tvHoliDayName);
            tvLeaveType = itemView.findViewById(R.id.tvLeaveType);
            tvLeaveReason = itemView.findViewById(R.id.tvLeaveReason);
            MyCardView = itemView.findViewById(R.id.mycards);
        }

        public void set(final AttendanceReportList item) {
            //UI setting code

        }
    }
}