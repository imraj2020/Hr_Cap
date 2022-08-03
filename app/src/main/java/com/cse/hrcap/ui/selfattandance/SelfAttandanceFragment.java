package com.cse.hrcap.ui.selfattandance;

import static android.content.Context.LOCATION_SERVICE;

import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import android.app.ProgressDialog;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cse.hrcap.DbHelper;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;

public class SelfAttandanceFragment extends Fragment implements LocationListener {
    private SelfAttandanceViewModel mViewModel;
    TextView text_location, text_location_latitude,text_location_longitude, today_date, today_time;
    LocationManager locationManager;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    ProgressDialog progressDialog;
    SelfAttandanceFragmentBinding binding;
    public static SelfAttandanceFragment newInstance() {
        return new SelfAttandanceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =SelfAttandanceFragmentBinding.inflate(inflater);
        text_location = binding.textLocation;
        text_location_latitude = binding.textLocationLatitude;
        text_location_longitude = binding.textLocationLongitude;
        today_date = binding.todayDate;
        today_time = binding.todayTime;

        
          //Date formet
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        today_date.setText(date);



        //Time Formet
        Date d=new Date();
        SimpleDateFormat stf=new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = stf.format(d);
        today_time.setText(currentDateTimeString);



        //working with database

//        DbHelper dbHelper = new DbHelper(requireContext());
//        String  dates = date.trim();
//        String  times = currentDateTimeString.trim();
//        dbHelper.insertRecord(dates, times);




        //Progress dilogue start
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("Getting Location Please Wait..."); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.setCancelable(false);
        progressDialog.show();



        //runtime permission
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        locationManager = (LocationManager) requireActivity().getSystemService( LOCATION_SERVICE );

        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }


        getlocation();
//        button_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getlocation();
//            }
//        });

        return binding.getRoot();


    }

    // alert dialogue while gps not enable
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        Toast.makeText(requireContext(), "Please Enable Internet and Gps first", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(requireActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @SuppressLint("MissingPermission")
    private void getlocation() {

        try {
            locationManager = (LocationManager) requireContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(requireContext(), "Please check internet connection and turn on gps", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onLocationChanged(Location location) {


        Toast.makeText(requireContext(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            text_location.setText(address);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String stringdouble1= Double.toString(latitude);
            text_location_latitude.setText(stringdouble1);
            String stringdouble2= Double.toString(longitude);
            text_location_longitude.setText(stringdouble2);
            progressDialog.dismiss();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }


    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelfAttandanceViewModel.class);
        // TODO: Use the ViewModel
    }
}