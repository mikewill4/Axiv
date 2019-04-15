package edu.umd.cmsc434.axiv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class IndividualCompetition extends AppCompatActivity {

    ListView competitionListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_competition);

        ArrayList<AppData.User> userList = AppData.userPrivateCompetitions.get(getIntent().getIntExtra("userList",0)).participants;

        Collections.sort(userList);
        Collections.reverse(userList);


        MyIndividualCompetitorAdapter adapter = new MyIndividualCompetitorAdapter(this,userList);
        competitionListView = (ListView) findViewById(R.id.individual_competition_user_listview);
        competitionListView.setAdapter(adapter);
    }


}

class MyIndividualCompetitorAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<AppData.User> userList;

    public MyIndividualCompetitorAdapter(Activity context, ArrayList<AppData.User> userList) {
        super(context, R.layout.listitem_individual_comp_users);

        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return userList.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.listitem_individual_comp_users, null, true);

        TextView userName = (TextView) rowView.findViewById(R.id.individual_comp_name);
        TextView userScore = (TextView) rowView.findViewById(R.id.individual_comp_local_score);

        userName.setText(position+1  + ". " + userList.get(position).userName);
        userScore.setText("Score:\n" + userList.get(position).score);

        return rowView;

    };
}
