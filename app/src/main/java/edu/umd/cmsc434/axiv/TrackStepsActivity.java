package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TrackStepsActivity extends AppCompatActivity {

    Date eventOccurance;
    Button submitSteps;
    EditText numSteps;
    TextView displayDateTime;
    DatePicker calendar;
    Button dateButton;
    Button setDate;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_steps);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitSteps = (Button) findViewById(R.id.track_steps_submit_steps);
        submitSteps.setVisibility(View.GONE);

        //EDIT_TEXT
        numSteps = (EditText) findViewById(R.id.steps_input);

        //TIME SELECTION CODE

        displayDateTime =(TextView) findViewById(R.id.track_steps_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_steps_calandar);
        calendar.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_steps_date_button);
        setDate = (Button) findViewById(R.id.track_steps_set_date_button);

        setDate.setVisibility(View.GONE);

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
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                calendar.setVisibility(View.GONE);
                setDate.setVisibility(View.GONE);
                submitSteps.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormatNoTime.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
            }
        });

        submitSteps.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(numSteps.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackStepsActivity.this, "Invalid Value for Steps", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                int stepValue = Integer.parseInt(numSteps.getText().toString());



                AppData.userMetricHistory.add(new Metric.StepsMetric(eventOccurance, stepValue));
                System.out.println(AppData.userMetricHistory);
                startActivity(new Intent(TrackStepsActivity.this,MainActivity.class));
            }
        });




    }
}
