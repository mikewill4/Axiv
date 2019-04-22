package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstRegisterFragment extends Fragment {

    private LoginFragment loginFragment;
    private AccountDetailsFragment accountDetailsFragment;
    private SpecificationsFragment specificationsFragment;
    private BaselineQuestionsFragment baselineQuestionsFragment;
    private Button AccountDetailsButton;
    private Button SpecificationsButton;
    private Button BaselineQuestionsButton;
    private Button CompleteRegistration;


    public FirstRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first_register, container, false);

        TextView haveAccount = (TextView) rootView.findViewById(R.id.accountText);
        haveAccount.setText(Html.fromHtml("<font color=#000000>Already have an account?</font> <font color=#fda500> Sign in.</font>"));

        loginFragment = new LoginFragment();
        accountDetailsFragment = new AccountDetailsFragment();
        specificationsFragment = new SpecificationsFragment();
        baselineQuestionsFragment = new BaselineQuestionsFragment();

        AccountDetailsButton = rootView.findViewById(R.id.btnAccountDetails);
        SpecificationsButton = rootView.findViewById(R.id.btnSpecs);
        BaselineQuestionsButton = rootView.findViewById(R.id.btnBaselineQuestions);
        CompleteRegistration = rootView.findViewById(R.id.btnCompleteRegistration);

        AccountDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(accountDetailsFragment);
            }
        });

        SpecificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(specificationsFragment);
            }
        });

        BaselineQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(baselineQuestionsFragment);
            }
        });

        CompleteRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(loginFragment);
            }
        });

        return rootView;
    }
}
