package edu.umd.cmsc434.axiv;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private TextView InvalidCreds;
    private TextView ForgotPassword;
    private int counter = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.etUsername); // Assigns the respective variable in the xml to Name
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        InvalidCreds = (TextView) findViewById(R.id.tvInvalidCreds);
        ForgotPassword = (TextView) findViewById(R.id.btnForgotPassword);

        InvalidCreds.setText("");
        Info.setText("No. of attempts remaining: 5");

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
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
    }


    private void validate(String userName, String userPassword) {
        if ((userName.length() > 0) && (userPassword.length() > 0)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
