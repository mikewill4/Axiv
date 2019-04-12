package edu.umd.cmsc434.axiv;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstRegisterFragment extends Fragment {

    private LoginFragment loginFragment;

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

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(loginFragment);
            }
        });

        return rootView;
    }
}
