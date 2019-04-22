package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TrackSleepActivity extends AppCompatActivity {

    Date eventOccurance;
    Date sleepTimeStart;
    Date sleepTimeEnd;
    Calendar calStart = Calendar.getInstance();
    Calendar calEnd = Calendar.getInstance();

    DatePicker calendarStart;
    DatePicker calendarEnd;

    TimePicker timePickerStart;
    TimePicker timePickerEnd;

    TextView displayDateTimeStart;
    TextView displayDateTimeEnd;


    Button dateButtonStart;
    Button dateButtonEnd;

    Button setDateStart;
    Button setTimeStart;
    Button setDateEnd;
    Button setTimeEnd;
    Button submitSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_sleep);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitSleep = (Button) findViewById(R.id.track_sleep_submit_sleep);
        submitSleep.setVisibility(View.GONE);

        //TIME SELECTION CODE

        displayDateTimeStart =(TextView) findViewById(R.id.track_sleep_start_date_time_display);
        displayDateTimeStart.setVisibility(View.GONE);

        displayDateTimeEnd =(TextView) findViewById(R.id.track_sleep_end_date_time_display);
        displayDateTimeEnd.setVisibility(View.GONE);



        calendarStart = (DatePicker) findViewById(R.id.track_sleep_start_calendar);
        timePickerStart = (TimePicker) findViewById(R.id.track_sleep_start_time_picker);
        calendarStart.setVisibility(View.GONE);
        timePickerStart.setVisibility(View.GONE);

        calendarEnd = (DatePicker) findViewById(R.id.track_sleep_end_calendar);
        timePickerEnd = (TimePicker) findViewById(R.id.track_sleep_end_time_picker);
        calendarEnd.setVisibility(View.GONE);
        timePickerEnd.setVisibility(View.GONE);

        dateButtonStart = (Button) findViewById(R.id.track_sleep_start_date_button);
        setDateStart = (Button) findViewById(R.id.track_sleep_start_set_date_button);
        setTimeStart = (Button) findViewById(R.id.track_sleep_start_set_time_button);

        setDateStart.setVisibility(View.GONE);
        setTimeStart.setVisibility(View.GONE);

        dateButtonEnd = (Button) findViewById(R.id.track_sleep_end_date_button);
        setDateEnd = (Button) findViewById(R.id.track_sleep_end_set_date_button);
        setTimeEnd = (Button) findViewById(R.id.track_sleep_end_set_time_button);

        setDateEnd.setVisibility(View.GONE);
        setTimeEnd.setVisibility(View.GONE);

        dateButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarStart.setVisibility(View.VISIBLE);
                setDateStart.setVisibility(View.VISIBLE);
                dateButtonStart.setVisibility(View.GONE);

            }
        });

        dateButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarEnd.setVisibility(View.VISIBLE);
                setDateEnd.setVisibility(View.VISIBLE);
                dateButtonEnd.setVisibility(View.GONE);

            }
        });


        setDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calStart.set(Calendar.YEAR,calendarStart.getYear());
                calStart.set(Calendar.MONTH,calendarStart.getMonth());
                calStart.set(Calendar.DATE, calendarStart.getDayOfMonth());
                calendarStart.setVisibility(View.GONE);
                setDateStart.setVisibility(View.GONE);
                timePickerStart.setVisibility(View.VISIBLE);
                setTimeStart.setVisibility(View.VISIBLE);
            }
        });

        setDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calEnd.set(Calendar.YEAR,calendarEnd.getYear());
                calEnd.set(Calendar.MONTH,calendarEnd.getMonth());
                calEnd.set(Calendar.DATE, calendarEnd.getDayOfMonth());
                calendarEnd.setVisibility(View.GONE);
                setDateEnd.setVisibility(View.GONE);
                timePickerEnd.setVisibility(View.VISIBLE);
                setTimeEnd.setVisibility(View.VISIBLE);
            }
        });

        setTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calStart.set(Calendar.HOUR,timePickerStart.getCurrentHour());
                calStart.set(Calendar.MINUTE, timePickerStart.getCurrentMinute());
                timePickerStart.setVisibility(View.GONE);
                setTimeStart.setVisibility(View.GONE);
                dateButtonStart.setVisibility(View.VISIBLE);
                sleepTimeStart = calStart.getTime();
                displayDateTimeStart.setText(AppData.standardDateFormat.format(sleepTimeStart));
                displayDateTimeStart.setVisibility(View.VISIBLE);

                if(displayDateTimeEnd.getVisibility() == View.VISIBLE){
                    submitSleep.setVisibility(View.VISIBLE);
                }
            }
        });

        setTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calEnd.set(Calendar.HOUR,timePickerEnd.getCurrentHour());
                calEnd.set(Calendar.MINUTE, timePickerEnd.getCurrentMinute());
                timePickerEnd.setVisibility(View.GONE);
                setTimeEnd.setVisibility(View.GONE);
                dateButtonEnd.setVisibility(View.VISIBLE);
                sleepTimeEnd = calEnd.getTime();
                eventOccurance = calEnd.getTime();

                displayDateTimeEnd.setText(AppData.standardDateFormat.format(sleepTimeEnd));
                displayDateTimeEnd.setVisibility(View.VISIBLE);

                if(displayDateTimeStart.getVisibility() == View.VISIBLE){
                    submitSleep.setVisibility(View.VISIBLE);
                }

            }
        });

        submitSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sleepTimeEnd.getTime() - sleepTimeStart.getTime() < 0){
                    Toast err = Toast.makeText(TrackSleepActivity.this,"Cannot have end time before start time", Toast.LENGTH_SHORT);
                    err.show();
                    return;
                }

                AppData.userMetricHistory.add(new Metric.SleepMetric(eventOccurance,sleepTimeStart,sleepTimeEnd));
                long duration = sleepTimeStart.getTime() - sleepTimeEnd.getTime();
                int hours= (int) Math.floor(duration/3600000);
                int minutes = (int) Math.floor((duration - hours*3600000) / 60000);
                AppData.appUser.updateScore(minutes/3);
                AppData.appUser.updateMetricScore("Sleep", minutes/3);

                Toast.makeText(TrackSleepActivity.this, "Added New Sleep Log", Toast.LENGTH_SHORT).show();


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
