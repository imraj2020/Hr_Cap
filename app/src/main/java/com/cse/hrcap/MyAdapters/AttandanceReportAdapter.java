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

        //In time
        String timeString = items.get(position).getInTime(); // replace with your actual time string from Retrofit response

        String formattedTime = null;
        try {
            if (timeString != null) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
                Date date = inputFormat.parse(timeString);

                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
                formattedTime = outputFormat.format(date);

                // display formattedTime to the user
            } else {
                formattedTime = "0";
                // handle null value case
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // handle parse exception
        }


        //Worked time
        String timeString1 = items.get(position).getTotalWorkedHour(); // replace with your actual time string from Retrofit response

        String formattedTime1 = null;
        try {
            if (timeString1 != null) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
                Date date1 = inputFormat.parse(timeString1);

                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
                formattedTime1 = outputFormat.format(date1);

                // display formattedTime to the user
            } else {
                // handle null value case
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // handle parse exception
        }


        //Over time
        String timeString2 = items.get(position).getOverTime(); // replace with your actual time string from Retrofit response

        String formattedTime2 = null;
        try {
            if (timeString2 != null) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
                Date date2 = inputFormat.parse(timeString2);

                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
                formattedTime2 = outputFormat.format(date2);

                // display formattedTime to the user
            } else {
                // handle null value case
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // handle parse exception
        }

        //Out time
        String timeString3 = items.get(position).getOutTime(); // replace with your actual time string from Retrofit response

        String formattedTime3 = null;
        try {
            if (timeString3 != null) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
                Date date3 = inputFormat.parse(timeString3);

                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
                formattedTime3 = outputFormat.format(date3);

                // display formattedTime to the user
            } else {
                formattedTime3 = "0";
                // handle null value case
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // handle parse exception
        }


        // Input date string1
        String originalText = items.get(position).getDate();
        String shortenedText = originalText.substring(0, 10);


        String inputDate = items.get(position).getDate();
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat("dd");
        String outputDate = outputFormat.format(date);
        // System.out.println(outputDate);


        String holidayName = items.get(position).getHoliDayName();

        if (holidayName != null && !holidayName.trim().isEmpty()) {
            holder.AtdDate.setVisibility(View.VISIBLE);
            holder.tvInTime.setVisibility(View.GONE);
            holder.tvOutTime.setVisibility(View.GONE);
            holder.tvLateHour.setVisibility(View.GONE);
            holder.tvOverTime.setVisibility(View.GONE);
            holder.tvWorkedHour.setVisibility(View.GONE);
            holder.tvBreakDuration.setVisibility(View.GONE);
            holder.tvHolidayName.setVisibility(View.VISIBLE);
            holder.tvLeaveType.setVisibility(View.GONE);
            holder.tvLeaveReason.setVisibility(View.GONE);
            holder.Status.setVisibility(View.GONE);
        } else {
            holder.AtdDate.setVisibility(View.VISIBLE);

            holder.tvInTime.setVisibility(View.VISIBLE);
            holder.tvOutTime.setVisibility(View.VISIBLE);
            holder.tvLateHour.setVisibility(View.GONE);
            holder.tvOverTime.setVisibility(View.VISIBLE);
            holder.tvWorkedHour.setVisibility(View.VISIBLE);
            holder.tvBreakDuration.setVisibility(View.GONE);

            holder.tvHolidayName.setVisibility(View.GONE);

            holder.tvLeaveType.setVisibility(View.GONE);
            holder.tvLeaveReason.setVisibility(View.GONE);
            holder.Status.setVisibility(View.VISIBLE);
        }

        //Leave

        String leave = items.get(position).getLeaveype();

        if (leave != null && !leave.trim().isEmpty()) {
            holder.AtdDate.setVisibility(View.VISIBLE);
            holder.tvInTime.setVisibility(View.GONE);
            holder.tvOutTime.setVisibility(View.GONE);
            holder.tvLateHour.setVisibility(View.GONE);
            holder.tvOverTime.setVisibility(View.GONE);
            holder.tvWorkedHour.setVisibility(View.GONE);
            holder.tvBreakDuration.setVisibility(View.GONE);
            holder.tvHolidayName.setVisibility(View.GONE);
            holder.tvLeaveType.setVisibility(View.VISIBLE);
            holder.tvLeaveReason.setVisibility(View.VISIBLE);
            holder.Status.setVisibility(View.GONE);
        } else {
//            holder.AtdDate.setVisibility(View.VISIBLE);
//            holder.tvInTime.setVisibility(View.VISIBLE);
//            holder.tvOutTime.setVisibility(View.VISIBLE);
//            holder.tvLateHour.setVisibility(View.GONE);
//            holder.tvOverTime.setVisibility(View.VISIBLE);
//            holder.tvWorkedHour.setVisibility(View.VISIBLE);
//            holder.tvBreakDuration.setVisibility(View.GONE);
////            holder.tvHolidayName.setVisibility(View.GONE);
//            holder.tvLeaveType.setVisibility(View.GONE);
//            holder.tvLeaveReason.setVisibility(View.GONE);
//
//            holder.Status.setVisibility(View.VISIBLE);
        }

//        if (formattedTime == null) {
//            formattedTime = "";
//        }
//        if(formattedTime3==null) {
//            formattedTime3 = "";
//        }


        holder.AtdDate.setText(outputDate + "");
        holder.tvInTime.setText(formattedTime !=null ? formattedTime:"");
        holder.tvOutTime.setText(formattedTime3 !=null ? formattedTime3:"");
        holder.tvLateHour.setText(items.get(position).getLateHour() + "");
        holder.tvOverTime.setText(formattedTime2 + "");
        holder.tvWorkedHour.setText(formattedTime1 + "");
        holder.tvBreakDuration.setText(items.get(position).getBreakDuration() + "");
        holder.tvHolidayName.setText(items.get(position).getHoliDayName() + "");
        holder.tvLeaveType.setText(items.get(position).getLeaveype() + "");
        holder.tvLeaveReason.setText(items.get(position).getLeaveReason() + "");
        holder.Status.setText(items.get(position).getStatus() + "");


//        setBoldText(holder.AtdDate, "Attendance Date: ", outputDate+"");
//        setBoldText(holder.tvInTime, "In Time: ", items.get(position).getInTime()+" ");
//        setBoldText(holder.tvOutTime, "Out Time: ", items.get(position).getOutTime()+"");
//        setBoldText(holder.tvLateHour, "Late Hour: ", items.get(position).getLateHour()+" ");
//        setBoldText(holder.tvOverTime, "Over Time: ", items.get(position).getOverTime()+"");
//        setBoldText(holder.tvWorkedHour, "Worked Hour: ", items.get(position).getTotalWorkedHour()+" ");
//        setBoldText(holder.tvBreakDuration, "Break Duration: ", items.get(position).getBreakDuration()+"");
//        setBoldText(holder.tvHolidayName, "Holiday Name: ", items.get(position).getHoliDayName()+" ");
//        setBoldText(holder.tvLeaveType, "Leave Type: ", items.get(position).getLeaveype()+"");
//        setBoldText(holder.tvLeaveReason, "Leave Reason: ", items.get(position).getLeaveReason()+"");


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

        TextView AtdDate, tvInTime, tvOutTime, tvLateHour, tvOverTime, tvWorkedHour, tvBreakDuration, tvHolidayName, tvLeaveType, tvLeaveReason;
        TextView Status;
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
            Status = itemView.findViewById(R.id.tvStatus);
            MyCardView = itemView.findViewById(R.id.mycards);
        }

        public void set(final AttendanceReportList item) {
            //UI setting code

        }
    }
}