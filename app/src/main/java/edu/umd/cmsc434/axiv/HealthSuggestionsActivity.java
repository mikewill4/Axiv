package edu.umd.cmsc434.axiv;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HealthSuggestionsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_suggestions);

        listView = (ListView) findViewById(R.id.health_suggestions_list);

        final List<String> healthSuggestions = new ArrayList<>();
        // Sleep
        healthSuggestions.add("Avoid using your phone right before you go to bed.");
        // Diet/calories
        healthSuggestions.add("Drink almond milk rather than cow's milk.");
        // Exercise
        healthSuggestions.add("Walk 5 miles every week.");
        // Steps
        healthSuggestions.add("Hit 10,000 steps every day.");
        // Blood pressure
        healthSuggestions.add("Reduce sodium in your diet.");
        // Heart rate
        healthSuggestions.add("Frequently measure your resting heart rate.");
        // Weight
        healthSuggestions.add("Eat your food slowly.");
        // Hydration
        healthSuggestions.add("Weigh yourself before and after exercise.");

        final List<String> moreInformation = new ArrayList<>();
        // Sleep:
        moreInformation.add("Using your phone will stimulate your brain, making it more difficult to fall asleep and stay asleep.");
        // Diet/calories:
        moreInformation.add("Almond milk is significantly less calories, low in sugar, and high in vitamins.");
        // Exercise:
        moreInformation.add("Walking is a simple exercise that can help improve cholesterol levels, strengthen bones, and keep blood pressure in check.");
        // Steps:
        moreInformation.add("Tracking your steps is an easy way to gauge your overall activity level.");
        // Blood pressure:
        moreInformation.add("A small reduction of sodium intake can greatly improve your heart and reduce blood pressure significantly.");
        // Heart rate:
        moreInformation.add("Your resting heart rate can helpful for gauging your current heart health.");
        // Weight:
        moreInformation.add("Fast eaters tend to gain more weight over time. When you eat slower then you will feel more full after eating less.");
        // Hydration:
        moreInformation.add("Sweating can contribute to dehydration, especially during the summer. Keep track of your sweat losses to ensure you are staying hydrated.");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                healthSuggestions
        );

        listView.setAdapter(arrayAdapter);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(healthSuggestions.get(position));
                builder.setMessage(moreInformation.get(position));
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });
    }
}
