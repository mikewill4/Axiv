package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ArrayList<EditText> Details;
    private String Goals;
    private ArrayList<EditText> Specs;
    private RegisterDetailsFragment registerDetailsFragment;
    private Button DetailsButton;
    private Button GoalsButton;
    private Button SpecsButton;
    private boolean DetailsComplete = false;
    private boolean GoalsComplete = false;
    private boolean SpecsComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        //DetailsButton = findViewById(R.id.btnDetails);
        //GoalsButton = findViewById(R.id.btnGoals);
        //SpecsButton = findViewById(R.id.btnSpecs);

        DetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDetailsFragment = new RegisterDetailsFragment();
                setFragment(registerDetailsFragment);
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
