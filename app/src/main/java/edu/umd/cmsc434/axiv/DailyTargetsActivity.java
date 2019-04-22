package edu.umd.cmsc434.axiv;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DailyTargetsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_targets);

        listView = (ListView) findViewById(R.id.daily_targets_list);

        ArrayList<String> dailyTargets = new ArrayList<>();
        dailyTargets.add("Hit 300,000 steps this month.");
        dailyTargets.add("Eat less than 2500 calories per day.");
        dailyTargets.add("Exercise 4 times this week.");
        dailyTargets.add("Drink 24 ounces of water per day.");
        dailyTargets.add("Lose 10 pounds this year.");


        DailyTargetAdapter adapter = new DailyTargetAdapter(this, dailyTargets);

        listView.setAdapter(adapter);
        UIUtils.setListViewHeightBasedOnItems(listView);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    builder.setTitle("ALMOST THERE");
                    builder.setMessage("So close to the goal, you can almost taste it!");
                    builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Dialog will be dismissed when clicked
                        }
                    });
                    builder.show();
            }
        });

        Button createGoal = (Button) findViewById(R.id.add_goal_button);
        createGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DailyTargetsActivity.this, "This feature has yet to be implemented. Sorry!", Toast.LENGTH_LONG).show();
            }
        });

//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                builder.setTitle(healthSuggestions.get(position));
//                builder.setMessage(R.string.more_information);
//                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Dialog will be dismissed when clicked
//                    }
//                });
//                builder.show();
//            }
//        });
    }

}

class DailyTargetAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> targets;

    public DailyTargetAdapter(Activity context, ArrayList<String> targets) {
        super(context, R.layout.listitem_individual_comp_users);
        this.context = context;
        this.targets = targets;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return targets.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.listitem_goals, null, true);

        TextView dailyTarget = (TextView) rowView.findViewById(R.id.daily_target_text);
        ProgressBar progressBar = (ProgressBar) rowView.findViewById(R.id.daily_target_progressbar);

        dailyTarget.setText(targets.get(position));
        progressBar.setProgress((int)(Math.random() * 100));

        return rowView;

    };
}
