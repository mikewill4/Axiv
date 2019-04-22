package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import edu.umd.cmsc434.axiv.Metric.BloodPressureMetric;

public class TrackBPActivity extends AppCompatActivity {

    Date eventOccurance;
    Button submitBPReading;
    EditText SystolicInput;
    EditText DiastolicInput;
    TextView displayDateTime;
    DatePicker calendar;
    TimePicker timePicker;
    Button dateButton;
    Button setDate;
    Button setTime;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_blood_pressure);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitBPReading = (Button) findViewById(R.id.track_BP_submit_BP);
        submitBPReading.setVisibility(View.GONE);

        //EDIT_TEXT
        SystolicInput = (EditText) findViewById(R.id.systolic_BP_input);
        DiastolicInput = (EditText) findViewById(R.id.diastolic_BP_input);

        //TIME SELECTION CODE

        displayDateTime =(TextView) findViewById(R.id.track_BP_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_BP_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_BP_time_picker);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_BP_date_button);
        setDate = (Button) findViewById(R.id.track_BP_set_date_button);
        setTime = (Button) findViewById(R.id.track_BP_set_time_button);

        setDate.setVisibility(View.GONE);
        setTime.setVisibility(View.GONE);

        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
                setDate.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.GONE);

            }
        });


        setDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.set(Calendar.YEAR,calendar.getYear());
                cal.set(Calendar.MONTH,calendar.getMonth());
                cal.set(Calendar.DATE, calendar.getDayOfMonth());
                calendar.setVisibility(View.GONE);
                setDate.setVisibility(View.GONE);
                timePicker.setVisibility(View.VISIBLE);
                setTime.setVisibility(View.VISIBLE);
            }
        });

        setTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.set(Calendar.HOUR,timePicker.getCurrentHour());
                cal.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                timePicker.setVisibility(View.GONE);
                setTime.setVisibility(View.GONE);
                submitBPReading.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormat.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
            }
        });

        submitBPReading.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SystolicInput.getText().toString().equals("") || DiastolicInput.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackBPActivity.this, "Invalid Value for Blood Pressure", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                int systolicValue = Integer.parseInt(SystolicInput.getText().toString());
                int diastolicValue = Integer.parseInt(DiastolicInput.getText().toString());

                AppData.userMetricHistory.add(new BloodPressureMetric(eventOccurance, systolicValue, diastolicValue));
                AppData.appUser.updateScore(systolicValue/10 * -1);
                AppData.appUser.updateMetricScore("Blood pressure", systolicValue/10 * -1);

                System.out.println(AppData.userMetricHistory);

                onBackPressed();
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent newIntent = new Intent("android.intent.action.HOMEPAGE");
        newIntent.putExtra("currId", 1);
        startActivity(newIntent);
    }
}
