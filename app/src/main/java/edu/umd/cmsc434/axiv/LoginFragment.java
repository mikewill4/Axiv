package edu.umd.cmsc434.axiv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Register;
    private TextView InvalidCreds;
    private TextView ForgotPassword;
    private int counter = 5;
    private FirstRegisterFragment firstRegisterFragment;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Name = (EditText) rootView.findViewById(R.id.etUsername); // Assigns the respective variable in the xml to Name
        Password = (EditText) rootView.findViewById(R.id.etPassword);
        Info = (TextView) rootView.findViewById(R.id.tvInfo);
        Login = (Button) rootView.findViewById(R.id.btnLogin);
        Register = (Button) rootView.findViewById(R.id.btnRegister);
        InvalidCreds = (TextView) rootView.findViewById(R.id.tvInvalidCreds);
        ForgotPassword = (TextView) rootView.findViewById(R.id.btnForgotPassword);

        InvalidCreds.setText("");
        Info.setText("No. of attempts remaining: 5");

        firstRegisterFragment = new FirstRegisterFragment();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity)getActivity()).setFragment(firstRegisterFragment);
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Password reset");
                builder.setMessage("An email has been sent to AxivUser@gmail.com. Follow the steps in the email to reset your password.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }

    private void validate(String userName, String userPassword) {
        if ((userName.length() > 0) && (userPassword.length() > 0)) {
            Intent intent = new Intent(this.getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            InvalidCreds.setText("Invalid username or password.");
            counter--;
            Info.setText("No. of attempts remaining: " + String.valueOf(counter));
            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
    }

}
