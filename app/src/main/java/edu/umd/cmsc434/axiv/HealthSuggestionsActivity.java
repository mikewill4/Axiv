package edu.umd.cmsc434.axiv;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        healthSuggestions.add("Do 50 bicep curls each day.");
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
        moreInformation.add("Bicep curls are an easy exercise that can help build muscle and burn calories. They only require dumbbells and no complex machines.");
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

        HealthSuggestionsListAdapter listAdapter = new HealthSuggestionsListAdapter(this, healthSuggestions);

        listView.setAdapter(listAdapter);

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

class HealthSuggestionsListAdapter extends ArrayAdapter<String> {

    private List<String> healthSuggestions;

    public HealthSuggestionsListAdapter(Activity context, List<String> healthSuggestions) {
        super(context, R.layout.listitem_health_suggestion);
        this.healthSuggestions = healthSuggestions;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return healthSuggestions.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(final int position, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View rowView = inflater.inflate(R.layout.listitem_health_suggestion, null,true);

        TextView healthSuggestion = (TextView) rowView.findViewById(R.id.health_suggestion);
        healthSuggestion.setText(healthSuggestions.get(position));

        ImageView healthSuggestionIcon = (ImageView) rowView.findViewById(R.id.health_suggestion_icon);
        if (position == 0) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_airline_seat_flat_white_24dp);
        } else if (position == 1) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_local_dining_white_24dp);
        } else if (position == 2) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_fitness_center_white_24dp);
        } else if (position == 3) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_directions_run_white_24dp);
        } else if (position == 4) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_timeline_white_24dp);
        } else if (position == 5) {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_favorite_white_24dp);
        } else if (position == 6) {
            healthSuggestionIcon.setImageResource(R.drawable.icons8_weight_60);
            healthSuggestionIcon.setPadding(0, 0, -16, 0);
        } else {
            healthSuggestionIcon.setImageResource(R.drawable.baseline_local_drink_white_24dp);
        }

        return rowView;

    };
}
