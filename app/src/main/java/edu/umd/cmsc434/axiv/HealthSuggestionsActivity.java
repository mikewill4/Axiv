package edu.umd.cmsc434.axiv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        List<String> healthSuggestions = new ArrayList<>();
        healthSuggestions.add("Walk 5 miles every week.");
        healthSuggestions.add("Read for 30 min before going to bed.");
        healthSuggestions.add("Eat a salad for lunch.");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                healthSuggestions
        );

        listView.setAdapter(arrayAdapter);
    }
}
