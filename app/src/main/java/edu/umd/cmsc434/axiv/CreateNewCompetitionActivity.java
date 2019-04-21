package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import edu.umd.cmsc434.axiv.AppData.PrivateCompetitionInfo;

public class CreateNewCompetitionActivity extends AppCompatActivity {

    CheckBox steps, exercise, nutrition, heartRate, bloodPressure, sleep, hydration, weight;
    EditText compName, orgName;
    Button submitComp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_competition);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        compName = (EditText) findViewById(R.id.new_competition_name);
        orgName = (EditText) findViewById(R.id.new_competition_org_name);

        submitComp = (Button) findViewById(R.id.create_new_competition_button);

        steps = (CheckBox) findViewById(R.id.step_checkbox);
        exercise = (CheckBox) findViewById(R.id.exercise_checkbox);
        nutrition = (CheckBox) findViewById(R.id.nutrition_checkbox);
        heartRate = (CheckBox) findViewById(R.id.heart_rate_checkbox);
        bloodPressure = (CheckBox) findViewById(R.id.blood_pressure_checkbox);
        sleep = (CheckBox) findViewById(R.id.sleep_checkbox);
        hydration = (CheckBox) findViewById(R.id.hydration_checkbox);
        weight = (CheckBox) findViewById(R.id.weight_checkbox);

        submitComp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(compName.getText().toString().replace("\n", "").replace(" ", "").equals("")) {
                    Toast.makeText(CreateNewCompetitionActivity.this, "Invalid Competition Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(orgName.getText().toString().replace("\n", "").replace(" ", "").equals("")){
                    Toast.makeText(CreateNewCompetitionActivity.this, "Invalid Competition Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!steps.isChecked() && !exercise.isChecked() && !nutrition.isChecked() && !heartRate.isChecked() && !bloodPressure.isChecked() &&
                        !sleep.isChecked() && !hydration.isChecked() && !weight.isChecked()){
                    Toast.makeText(CreateNewCompetitionActivity.this, "Please Select At Least One Competition Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                String competitionName = compName.getText().toString();
                String organizationName = orgName.getText().toString();

                ArrayList<AppData.User> users = new ArrayList<AppData.User>();
                users.add(new AppData.User("My Username", 100.0, null));




                AppData.userPrivateCompetitions.add(new PrivateCompetitionInfo(competitionName,organizationName,users.size(),users));

                Intent indvComp = new Intent(CreateNewCompetitionActivity.this,IndividualCompetition.class);
                indvComp.putExtra("userList", AppData.userPrivateCompetitions.size()-1);

                CreateNewCompetitionActivity.this.startActivity(indvComp);
            }
        });





    }

}
