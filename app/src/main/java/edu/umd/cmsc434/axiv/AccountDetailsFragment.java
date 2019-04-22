package edu.umd.cmsc434.axiv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AccountDetailsFragment extends Fragment {

    private Button Confirm;
    private FirstRegisterFragment firstRegisterFragment;

    public AccountDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account_details, container, false);


        firstRegisterFragment = new FirstRegisterFragment();
        Confirm = rootView.findViewById(R.id.btnCompleteRegistration);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(firstRegisterFragment);
            }
        });



        return rootView;
    }
}
