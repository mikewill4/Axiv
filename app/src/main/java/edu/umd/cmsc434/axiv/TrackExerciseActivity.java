package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TrackExerciseActivity extends AppCompatActivity {

    Button dateButton;
    Button timeButton;
    CalendarView calendar;
    TimePicker timePicker;
    TextView dateTimeDisplay;
    Spinner exerciseSpinner;
    EditText enterTimeDuration;
    EditText enterCalories;
    Button submitLog;
    Button setDate;
    Button setTime;

    String time = "";
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_exercise);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        exerciseSpinner = (Spinner) findViewById(R.id.track_exercise_activity_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.exersizes ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(adapter);


        dateTimeDisplay = (TextView) findViewById(R.id.track_exercise_date_time_display);
        dateTimeDisplay.setText(date + " - " + time);

        dateButton = (Button) findViewById(R.id.track_exercise_date_button);
        timeButton = (Button) findViewById(R.id.track_exercise_time_button);

        calendar = (CalendarView) findViewById(R.id.track_exercise_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_exercise_time_picker);
        timePicker.setIs24HourView(false);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);


        setDate = (Button) findViewById(R.id.track_exercise_set_date_button);
        setTime = (Button) findViewById(R.id.track_exercise_set_time_button);
        setDate.setVisibility(View.GONE);
        setTime.setVisibility(View.GONE);



        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.VISIBLE);
                setDate.setVisibility(View.VISIBLE);
            }
        });

        timeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                setTime.setVisibility(View.VISIBLE);
            }
        });

        calendar.setOnDateChangeListener(new OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = month + "/" + dayOfMonth + "/" + year;
                dateTimeDisplay.setText(date + " - " + time);
            }
        });

        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                time = hourOfDay + ":" + (minute < 10 ? "0" + minute : minute) ;
                dateTimeDisplay.setText(date + " - " + time);
            }
        });

        setDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.setVisibility(View.GONE);
                setDate.setVisibility(View.GONE);
            }
        });

        setTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.GONE);
                setTime.setVisibility(View.GONE);
            }
        });

        enterTimeDuration = (EditText) findViewById(R.id.track_exercise_enter_duration);
        enterCalories = (EditText) findViewById(R.id.track_exercise_enter_calories);


        submitLog = (Button) findViewById(R.id.track_exercise_submit_log);

        submitLog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrackExerciseActivity.this,MainActivity.class));


            }
        });




    }
}
