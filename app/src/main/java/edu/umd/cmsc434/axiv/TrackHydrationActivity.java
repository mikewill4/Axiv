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

import edu.umd.cmsc434.axiv.Metric.HydrationMetric;

public class TrackHydrationActivity extends AppCompatActivity {

    Date eventOccurance;
    Button submitWaterLog;
    EditText waterInput;
    Spinner unitSpinner;
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
        setContentView(R.layout.activity_track_hydration);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        submitWaterLog = (Button) findViewById(R.id.track_water_submit_water);
        submitWaterLog.setVisibility(View.GONE);

        //EDIT_TEXT
        waterInput = (EditText) findViewById(R.id.water_input);

        //SPINNER CODE
        unitSpinner = (Spinner) findViewById(R.id.input_water_unit_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liquid_units ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        //TIME SELECTION CODE

        displayDateTime =(TextView) findViewById(R.id.track_water_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_water_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_water_time_picker);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_water_date_button);
        setDate = (Button) findViewById(R.id.track_water_set_date_button);
        setTime = (Button) findViewById(R.id.track_water_set_time_button);

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
                submitWaterLog.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormat.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
            }
        });

        submitWaterLog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(waterInput.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(TrackHydrationActivity.this, "Invalid Value for Water Volume", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                double waterValue = Double.parseDouble(waterInput.getText().toString());


                if(unitSpinner.getSelectedItem().toString().equals("fl. oz")){
                    waterValue *= 29.57;
                } else if(unitSpinner.getSelectedItem().toString().equals("L")){
                    waterValue *= 1000;
                } else if(unitSpinner.getSelectedItem().toString().equals("cups")){
                    waterValue *= 236.59;
                }


                AppData.userMetricHistory.add(new HydrationMetric(eventOccurance, (int) waterValue));
                AppData.appUser.updateScore((int)waterValue/200 * -1);
                AppData.appUser.updateMetricScore("Hydration", (int)waterValue/200 * -1);

                Toast.makeText(TrackHydrationActivity.this, "Added New Hydration Log", Toast.LENGTH_SHORT).show();


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
