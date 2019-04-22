package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class BaselineQuestionsFragment extends Fragment {

    private FirstRegisterFragment firstRegisterFragment;
    private Button CompleteRegistration;
    private EditText Exercise;
    private EditText Water;
    private EditText Sleep;
    private EditText Steps;
    private RadioGroup Diet;
    private RadioGroup BloodPressure;

    public BaselineQuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_baseline_questions, container, false);

        CompleteRegistration = rootView.findViewById(R.id.btnCompleteRegistration);

        Diet = rootView.findViewById(R.id.rgDiet);
        BloodPressure = rootView.findViewById(R.id.rgBP);
        Exercise = rootView.findViewById(R.id.etExercise);
        Water = rootView.findViewById(R.id.etHydration);
        Sleep = rootView.findViewById(R.id.etSleep);
        Steps = rootView.findViewById(R.id.etSteps);

        firstRegisterFragment = new FirstRegisterFragment();

        CompleteRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }


    public void onDietRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbNotHealthy:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.rbSomewhatHealthy:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.rbVeryHealthy:
                if (checked)

                    break;
        }
    }

    public void onBPRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbHighBP:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.rbNormalBP:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.rbUnknownBP:
                if (checked)

                    break;
        }
    }
}
