package edu.umd.cmsc434.axiv;

import android.content.Intent;
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

        InvalidCreds.setText("");
        Info.setText("No. of attempts remaining: 5");
        getSupportActionBar().setTitle("Log In");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
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
