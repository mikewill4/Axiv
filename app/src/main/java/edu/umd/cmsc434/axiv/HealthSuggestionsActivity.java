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
        healthSuggestions.add("Walk 5 miles every week.");
        healthSuggestions.add("Read for 30 min before going to bed.");
        healthSuggestions.add("Eat a salad for lunch.");

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
                builder.setMessage(R.string.more_information);
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
