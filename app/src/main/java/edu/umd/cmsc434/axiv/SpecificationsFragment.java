package edu.umd.cmsc434.axiv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SpecificationsFragment extends Fragment {
    private EditText Gender;
    private EditText DateOfBirth;
    private EditText HeightFeet;
    private EditText HeightInches;
    private EditText Weight;
    private Button Confirm;
    private FirstRegisterFragment firstRegisterFragment;


    public SpecificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_specifications, container, false);

        Gender = rootView.findViewById(R.id.etGender);
        DateOfBirth = rootView.findViewById(R.id.etDOB);
        HeightFeet = rootView.findViewById(R.id.etHeightFeet);
        HeightInches = rootView.findViewById(R.id.etHeightInches);
        Weight = rootView.findViewById(R.id.etWeight);
        Confirm = rootView.findViewById(R.id.btnCompleteRegistration);


        firstRegisterFragment = new FirstRegisterFragment();

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(firstRegisterFragment);
            }
        });



        return rootView;
    }

}
