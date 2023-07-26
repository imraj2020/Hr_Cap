package com.cse.hrcap.MyAdapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.hrcap.R;
import com.cse.hrcap.network.AttdanceSummary;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelfAttendanceSummaryAdapter extends RecyclerView.Adapter<SelfAttendanceSummaryAdapter.ViewHolder> {
    private List<AttdanceSummary> attendanceSummaryList;

    public SelfAttendanceSummaryAdapter(List<AttdanceSummary> attendanceSummaryList) {
        this.attendanceSummaryList = attendanceSummaryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selfsummarycustomlv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AttdanceSummary attendanceSummary = attendanceSummaryList.get(position);


//            attendanceSummary = attendanceSummaryList.get(attendanceSummaryList.size() - position - 1);


        String inout = attendanceSummary.getInOut();


        CardView cardView = holder.MyCardView;

        // Set the color of the CardView based on the value of the inout string
        if (inout.equalsIgnoreCase("In")) {
            cardView.setCardBackgroundColor(Color.parseColor("#039BE5"));
        } else if (inout.equalsIgnoreCase("Out")) {
            cardView.setCardBackgroundColor(Color.parseColor("#86C8BC"));
        }


        // ...

// Given date string
        String myDate = attendanceSummary.getCheckInDate();

// Parse the date string into a LocalDate object
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }
        LocalDate dateFromMyString = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateFromMyString = LocalDate.parse(myDate, formatter);
        }

// Get the month value from the parsed date
        int monthFromMyString = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            monthFromMyString = dateFromMyString.getMonthValue();
        }

// Get the current date
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

// Get the current month value
        int currentMonth = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentMonth = currentDate.getMonthValue();
        }

// Compare the two month values
        if (monthFromMyString == currentMonth) {
            // The months are the same
            // Your code here for when the months match
            holder.CheckInDate.setText("" + attendanceSummary.getCheckInDate());
            holder.PunchTime.setText("" + attendanceSummary.getPunchTime());
            holder.InOut.setText("" + attendanceSummary.getInOut());
            holder.EntryBy.setText("" + attendanceSummary.getEntryBy());
            holder.EntryDate.setText("" + attendanceSummary.getEntryDate());

        } else {
            // The months are different
            // Your code here for when the months don't match
        }




    }

    @Override
    public int getItemCount() {
        return attendanceSummaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView CheckInDate, PunchTime, InOut, EntryBy, EntryDate;
        CardView MyCardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CheckInDate = (TextView) itemView.findViewById(R.id.tv_CheckInDate);
            PunchTime = (TextView) itemView.findViewById(R.id.tv_PunchTime);
            InOut = (TextView) itemView.findViewById(R.id.tv_InOut);
            EntryBy = (TextView) itemView.findViewById(R.id.tv_EntryBy);
            EntryDate = (TextView) itemView.findViewById(R.id.tv_EntryDate);
            MyCardView = itemView.findViewById(R.id.selfsummarycard);

        }
    }
}
