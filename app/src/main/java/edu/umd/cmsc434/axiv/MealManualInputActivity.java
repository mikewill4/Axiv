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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MealManualInputActivity extends AppCompatActivity {

    Date eventOccurance;
    Button submitMeal;
    EditText foodTypeInput;
    EditText calPerServingInput;
    EditText servingsInput;
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
        setContentView(R.layout.activity_meal_manual_input);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        submitMeal = (Button) findViewById(R.id.track_meals_submit_meal);
        submitMeal.setVisibility(View.GONE);

        foodTypeInput = (EditText) findViewById(R.id.meal_manual_input_food_type);
        calPerServingInput = (EditText) findViewById(R.id.meal_manual_input_calories);
        servingsInput = (EditText) findViewById(R.id.meal_manual_input_servings);

        displayDateTime =(TextView) findViewById(R.id.track_meals_date_time_display);
        displayDateTime.setVisibility(View.GONE);

        calendar = (DatePicker) findViewById(R.id.track_meals_calandar);
        timePicker = (TimePicker) findViewById(R.id.track_meals_time_picker);
        calendar.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        dateButton = (Button) findViewById(R.id.track_meals_date_button);
        setDate = (Button) findViewById(R.id.track_meals_set_date_button);
        setTime = (Button) findViewById(R.id.track_meals_set_time_button);

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
                submitMeal.setVisibility(View.VISIBLE);
                dateButton.setVisibility(View.VISIBLE);
                eventOccurance = cal.getTime();
                displayDateTime.setText(AppData.standardDateFormat.format(eventOccurance));
                displayDateTime.setVisibility(View.VISIBLE);
            }
        });

        submitMeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if(foodTypeInput.getText().toString().replace("\n","").replace(" ", "").equals("")){
                    Toast error_popup = Toast.makeText(MealManualInputActivity.this, "Invalid Value for Food Type", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }


                if(servingsInput.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(MealManualInputActivity.this, "Invalid Value for Servings", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }


                if(calPerServingInput.getText().toString().equals("")){
                    Toast error_popup = Toast.makeText(MealManualInputActivity.this, "Invalid Value for Number of Calories", Toast.LENGTH_SHORT);
                    error_popup.show();
                    return;
                }

                String foodType = foodTypeInput.getText().toString();
                int numServings = Integer.parseInt(servingsInput.getText().toString());
                int numCalPerServing = Integer.parseInt(calPerServingInput.getText().toString());


                AppData.userMetricHistory.add(new Metric.MealMetric(eventOccurance, foodType ,numServings,numCalPerServing));
                System.out.println(AppData.userMetricHistory);
                startActivity(new Intent(MealManualInputActivity.this,MainActivity.class));
            }
        });
    }

}