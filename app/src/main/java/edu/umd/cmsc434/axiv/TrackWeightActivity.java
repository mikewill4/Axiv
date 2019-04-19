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

import edu.umd.cmsc434.axiv.Metric.WeightMetric;

public class TrackWeightActivity extends AppCompatActivity {

    Date eventOccurance;
    EditText weight_input;
    Calendar cal = Calendar.getInstance();
    DatePicker calendar;
    TimePicker timePicker;
    TextView displayDateTime;
    Button dateButton;
    Button setDate;
    Button setTime;
    Button submitWeight;
    Spinner unitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_weight);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitWeight = (Button) findViewById(R.id.track_weight_submit_weight);
        submitWeight.setVisibility(View.GONE);

        //EDIT_TEXT
        weight_input = (EditText) findViewById(R.id.weight_input);

        //SPINNER CODE
        unitSpinner = (Spinner) findViewById(R.id.input_weight_unit_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.weight_units ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        //TIME SELECTION CODE

        displayDateTime =(TextView) findViewById(R.id.track_weight_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_weight_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_weight_time_picker);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_weight_date_button);
        setDate = (Button) findViewById(R.id.track_weight_set_date_button);
        setTime = (Button) findViewById(R.id.track_weight_set_time_button);

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
                submitWeight.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormat.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
            }
        });

        submitWeight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(weight_input.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackWeightActivity.this, "Invalid Value for Weight", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                double weight_value = Double.parseDouble(weight_input.getText().toString());


                if(unitSpinner.getSelectedItem().toString().equals("kg")){
                    weight_value *= 2.2;
                }


                AppData.userMetricHistory.add(new WeightMetric(eventOccurance,weight_value));
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
