package edu.umd.cmsc434.axiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        Button createGoal = (Button) findViewById(R.id.add_reminder_button);
        createGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RemindersActivity.this, "This feature has yet to be implemented. Sorry!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
