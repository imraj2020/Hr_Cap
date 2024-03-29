package com.cse.hrcap.ui.selfattandance;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import static android.content.Context.MODE_PRIVATE;
import static android.location.LocationManager.*;

import static com.cse.hrcap.R.id.ETreason;
import static java.util.Objects.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.LoginActivity;
import com.cse.hrcap.LoginDbHelper;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.network.AtdcheckResponse;
import com.cse.hrcap.network.AttandanceRequest;
import com.cse.hrcap.network.ChengePasswordResponse;
import com.cse.hrcap.network.EmployeeResponse;
import com.cse.hrcap.network.MyApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelfAttandanceFragment extends Fragment {
    private SelfAttandanceViewModel mViewModel;
    TextView text_location, text_location_latitude, text_location_longitude, today_date, today_time, tv_Companyid, tv_employee;
    LocationManager locationManager;
    FusedLocationProviderClient client;
    Button BtnLocation, CancelBtn, BtnSave;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    ProgressDialog progressDialog;
    SelfAttandanceFragmentBinding binding;
    SwitchCompat mySwitch;
    String Status;
    private Handler handler = new Handler();
    private Runnable runnable;

    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    public String convertedTime;

    String Prompt;

    String enteredText = null;

    String Type;


    public static SelfAttandanceFragment newInstance() {
        return new SelfAttandanceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SelfAttandanceFragmentBinding.inflate(inflater);
        text_location = binding.textLocation;
        text_location_latitude = binding.textLocationLatitude;
        text_location_longitude = binding.textLocationLongitude;
        today_date = binding.todayDate;
        today_time = binding.todayTime;
        BtnLocation = binding.getlocation;
        mySwitch = binding.checking;
        CancelBtn = binding.btncancel;
        BtnSave = binding.btnsave;


        // Start a runnable that updates the time every second
        handler.post(updateTimeRunnable);

        //Shared Preference for switch

        SharedPreferences preferences = this.getActivity().getPreferences(MODE_PRIVATE);
        boolean tgpref = preferences.getBoolean("tgpref", true);  //default is true
        if (tgpref = true) //if (tgpref) may be enough, not sure
        {
            mySwitch.setChecked(true);
            Status = "IN";
            // Toast.makeText(requireContext(),"Status is:"+Status,Toast.LENGTH_LONG).show();
        }
        if (tgpref = false) {
            mySwitch.setChecked(false);
            Status = "OUT";
            //  Toast.makeText(requireContext(),"Status is:"+Status,Toast.LENGTH_LONG).show();
        }


        //Date formet
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());
        today_date.setText(date);


//        //Time Formet
//        Date d = new Date();
//        SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
//        String currentDateTimeString = stf.format(d);
//        today_time.setText(currentDateTimeString);

        //testing Alart dialogue
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }


        // check if there any problem in getapplication context
        client = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Location button
        BtnLocation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
            }
        });

        //Toggle Button
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", true); // value to store
                    editor.commit();
                    Status = "IN";
                    // Toast.makeText(requireContext(),"Status is:"+Status,Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("tgpref", false); // value to store
                    editor.commit();
                    Status = "OUT";
                    //Toast.makeText(requireContext(),"Status is:"+Status,Toast.LENGTH_LONG).show();
                }

            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        //Save button
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(binding.textLocation.getText().toString().trim()) || TextUtils.isEmpty(binding.textLocationLatitude.getText().toString().trim()) ||
                        TextUtils.isEmpty(binding.textLocationLongitude.getText().toString().trim())) {
                    Toast.makeText(requireContext(), "Please Click On 'GET LOCATION' Button First", Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(binding.textLocation.getText().toString().trim())) {
                    binding.textLocation.setError("Location Can't be Empty");
                }
                if (TextUtils.isEmpty(binding.textLocationLatitude.getText().toString().trim())) {
                    binding.textLocationLatitude.setError("Latitude Can't be Empty");
                }
                if (TextUtils.isEmpty(binding.textLocationLongitude.getText().toString().trim())) {
                    binding.textLocationLongitude.setError("Longitude Can't be Empty");
                } else {

                    BtnSave.setEnabled(false);
                    AttandanceCheck();
                    // AttandanceRequest();

                }

            }
        });


        return binding.getRoot();


    }

    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            // Get the current time
            String currentTime = sdf.format(new Date());

            // Update the TextView with the current time
            today_time.setText(currentTime);


            SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm a", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.US);

            try {
                Date date = inputFormat.parse(currentTime);
                convertedTime = outputFormat.format(date);
                // Now, convertedTime contains the time in 24-hour format
                // You can use convertedTime in your Android app
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Schedule the next update after 1 second
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove the callbacks when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(updateTimeRunnable);
    }


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
//                        Intent i = new Intent(SelfAttendanceEntry.this, MainActivity.class);
//                        startActivity(i);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && (grantResults.length > 0) &&
                grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_LONG).show();
        }
    }

    //  @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) requireContext().
                getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(GPS_PROVIDER) || locationManager.isProviderEnabled(NETWORK_PROVIDER)) {

            //when location service is enable
            //get last location


            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    //check condition for location
                    if (location != null) {

                        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            text_location.setText(addresses.get(0).getAddressLine(0));


                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Getting Address Failed", Toast.LENGTH_LONG).show();
                        }
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        String stringdouble1 = Double.toString(latitude);
                        text_location_latitude.setText(stringdouble1);
                        String stringdouble2 = Double.toString(longitude);
                        text_location_longitude.setText(stringdouble2);
                        binding.textLocation.setError(null);
                        binding.textLocationLongitude.setError(null);
                        binding.textLocationLatitude.setError(null);
                    } else {
                        // when location result is null
                        //Initialized location request
                        // LocationRequest locationRequest;
                        Toast.makeText(requireContext(), "Location is null", Toast.LENGTH_LONG).show();
//
//
//                        LocationCallback locationCallback = new LocationCallback() {
//                            @Override
//                            public void onLocationResult(@NonNull LocationResult locationResult) {
//                                Location location1 = locationResult.getLastLocation();
//                                double latitude = location1.getLatitude();
//                                double longitude = location1.getLongitude();
//
//                                String stringdouble1 = Double.toString(latitude);
//                                text_location_latitude.setText(stringdouble1);
//                                String stringdouble2 = Double.toString(longitude);
//                                text_location_longitude.setText(stringdouble2);
//                            }
//                        };


                    }

                }
            });

        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelfAttandanceViewModel.class);
        // TODO: Use the ViewModel
    }

    private void AttandanceCheck() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employee = intent.getStringExtra("Employee");


        // Now, convertedTime contains the time in 24-hour format
        // You can use convertedTime in your Android app


        if (Status.equals("IN")) {
            Type = "1";
        } else if (Status.equals("OUT")) {
            Type = "2";
        } else {
            Type = "0";
        }


        Call<AtdcheckResponse> atdcheckResponse = MyApiClient.getUserService().atdcheckResponseCall(employee, convertedTime, Type, companyid);
        atdcheckResponse.enqueue(new Callback<AtdcheckResponse>() {
            @Override
            public void onResponse(Call<AtdcheckResponse> call, Response<AtdcheckResponse> response) {

                if (response.isSuccessful()) {
                    //Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_LONG).show();
                    AtdcheckResponse atdcheckResponse = response.body();


                    //    Toast.makeText(requireContext(), "Status is :" + atdcheckResponse.getStatus(), Toast.LENGTH_SHORT).show();

                    if (atdcheckResponse.getStatus().toString().equals("Ok")) {
                        //   Toast.makeText(requireContext(), "Status is :" + atdcheckResponse.getStatus(), Toast.LENGTH_SHORT).show();
                        Prompt = atdcheckResponse.getStatus();

                        AttandanceRequest();
//                        if (Prompt.equals("Ok")) {
//                            AttandanceRequest();
//                        }
                    }
                    if (atdcheckResponse.getStatus().equals("Late")) {
                        //    Toast.makeText(requireContext(), "Status is :" + atdcheckResponse.getStatus(), Toast.LENGTH_SHORT).show();
                        Prompt = atdcheckResponse.getStatus();
                        showDialog(requireContext());

                    }
                    if (atdcheckResponse.getStatus().equals("Extra")) {
                        //   Toast.makeText(requireContext(), "Status is :" + atdcheckResponse.getStatus(), Toast.LENGTH_SHORT).show();
                        Prompt = atdcheckResponse.getStatus();
                        showDialog(requireContext());

                    }
                    if (atdcheckResponse.getMessage() != null) {
                        Toast.makeText(requireContext(), "Status is :" + atdcheckResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(requireContext(), "Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<AtdcheckResponse> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void AttandanceRequest() {

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employee = intent.getStringExtra("Employee");

//        final AttandanceRequest attandanceRequest = new AttandanceRequest(employee,
//                today_date.getText().toString(), today_time.getText().toString(), Status, companyid,
//                text_location_latitude.getText().toString(), text_location_longitude.getText().toString(),
//                text_location.getText().toString());


        final AttandanceRequest attandanceRequest = new AttandanceRequest(employee,
                convertedTime, Type, employee, companyid,
                enteredText, text_location_latitude.getText().toString().trim(),
                text_location_longitude.getText().toString().trim(), text_location.getText().toString());


        Call<AttandanceRequest> call = MyApiClient.getUserService().PostDatass(attandanceRequest);


        call.enqueue(new Callback<AttandanceRequest>() {
            @Override
            public void onResponse(Call<AttandanceRequest> call, Response<AttandanceRequest> response) {
                if (response.isSuccessful()) {
                    AttandanceRequest attdanceresponse = response.body();
                    BtnSave.setEnabled(true);
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Status is :" + attdanceresponse.getStatus(), Toast.LENGTH_LONG).show();
                    String status = attdanceresponse.getStatus();
                    //Successfully Submitted or Already Submitted
                    if (status.equals("Success")) {
                        Navigation.findNavController(requireView()).popBackStack(R.id.nav_home, true);
                        Navigation.findNavController(requireView()).popBackStack(R.id.nav_selfattandance, true);
                        Navigation.findNavController(requireView()).navigate(R.id.nav_selfattandancesummary);
                    }

                } else {
                    BtnSave.setEnabled(true);
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<AttandanceRequest> call, Throwable t) {
                BtnSave.setEnabled(true);
                progressDialog.dismiss();
                //     Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.dialogue_layout, null);

        final EditText editText = customView.findViewById(ETreason);

        builder.setView(customView);
        builder.setTitle("Please Explain The Reason Of "+Prompt+" :");

        builder.setPositiveButton("OK", null);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                BtnSave.setEnabled(true);
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isNotEmpty = s.toString().trim().length() > 0;
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isNotEmpty);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        enteredText = editText.getText().toString();

                        AttandanceRequest();


                        alertDialog.dismiss();
                    }
                });
            }
        });

        alertDialog.setCancelable(false);
        alertDialog.show();
    }




    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}