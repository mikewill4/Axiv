package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import edu.umd.cmsc434.axiv.Metric.ExerciseMetric;

public class TrackExerciseActivity extends AppCompatActivity {

    Button dateButton;
    DatePicker calendar;
    TimePicker timePicker;
    TextView dateTimeDisplay;
    Spinner exerciseSpinner;
    EditText enterTimeDuration;
    EditText enterCalories;
    Button submitLog;
    Button setDate;
    Button setTime;

    Date eventOccurance;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_exercise);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        submitLog = (Button) findViewById(R.id.track_exercise_submit_log);
        submitLog.setVisibility(View.GONE);


        exerciseSpinner = (Spinner) findViewById(R.id.track_exercise_activity_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.exersizes ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(adapter);


        dateTimeDisplay = (TextView) findViewById(R.id.track_exercise_date_time_display);
        dateTimeDisplay.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_exercise_date_button);

        calendar = (DatePicker) findViewById(R.id.track_exercise_calandar);
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
                submitLog.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                dateTimeDisplay.setText(AppData.standardDateFormat.format(eventOccurance));
                dateTimeDisplay.setVisibility(View.VISIBLE);
            }
        });

        enterTimeDuration = (EditText) findViewById(R.id.track_exercise_enter_duration);
        enterTimeDuration.setHint("Required");
        enterCalories = (EditText) findViewById(R.id.track_exercise_enter_calories);
        enterCalories.setHint("Optional");


        submitLog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int calories;

                if(enterCalories.getText().toString().equals("")){
                    calories = 100;
                } else calories = Integer.parseInt(enterCalories.getText().toString());

                if(enterTimeDuration.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackExerciseActivity.this,"Invalid Values for Duration", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                AppData.userMetricHistory.add(new ExerciseMetric(eventOccurance,exerciseSpinner.getSelectedItem().toString(),
                        calories, Integer.parseInt(enterTimeDuration.getText().toString())));

                System.out.println(AppData.userMetricHistory);

                AppData.appUser.updateScore(calories/20);
                AppData.appUser.updateMetricScore("Exercise", calories/20);

                Toast.makeText(TrackExerciseActivity.this, "Added New Exercise Log", Toast.LENGTH_SHORT).show();

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
