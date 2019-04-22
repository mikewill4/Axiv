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

import edu.umd.cmsc434.axiv.Metric.HeartRateMetric;

public class TrackHRActivity extends AppCompatActivity {

    Date eventOccurance;
    Button submitHRReading;
    EditText HRInput;
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
        setContentView(R.layout.activity_track_heart_rate);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitHRReading = (Button) findViewById(R.id.track_HR_submit_HR);
        submitHRReading.setVisibility(View.GONE);

        //EDIT_TEXT
        HRInput = (EditText) findViewById(R.id.HR_input);

        //TIME SELECTION CODE

        displayDateTime =(TextView) findViewById(R.id.track_HR_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_HR_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_HR_time_picker);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_HR_date_button);
        setDate = (Button) findViewById(R.id.track_HR_set_date_button);
        setTime = (Button) findViewById(R.id.track_HR_set_time_button);

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
                submitHRReading.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormat.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
            }
        });

        submitHRReading.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(HRInput.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackHRActivity.this, "Invalid Value for Heart Rate", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                int HRValue = Integer.parseInt(HRInput.getText().toString());



                AppData.userMetricHistory.add(new HeartRateMetric(eventOccurance, HRValue));
                AppData.appUser.updateScore(HRValue/20);
                AppData.appUser.updateMetricScore("Heart rate", HRValue/20);

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
