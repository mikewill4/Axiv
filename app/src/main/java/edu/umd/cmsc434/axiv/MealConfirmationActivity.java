package edu.umd.cmsc434.axiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MealConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_confirmation);


        Button submit = (Button) findViewById(R.id.meal_confirm_submit);
        EditText numServings = (EditText) findViewById(R.id.meal_confirm_input_servings);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MealConfirmationActivity.this,MainActivity.class));
            }
        });
    }

}
