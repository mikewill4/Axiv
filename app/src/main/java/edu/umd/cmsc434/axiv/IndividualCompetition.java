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

    private ArrayList<CompetitionUser> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_competition);

        userList = new ArrayList<CompetitionUser>();
        userList.add(new CompetitionUser("Jeff Bezos",234));
        userList.add(new CompetitionUser("Elon Musk",124));
        userList.add(new CompetitionUser("Brendan Iribe",438));
        Collections.sort(userList);
        Collections.reverse(userList);


        MyIndividualCompetitorAdapter adapter = new MyIndividualCompetitorAdapter(this,userList);
        competitionListView = (ListView) findViewById(R.id.individual_competition_user_listview);
        competitionListView.setAdapter(adapter);
    }


}

class CompetitionUser implements Comparable<CompetitionUser> {

    public String userName;
    public double score;

    public CompetitionUser(String userName, double score){
        this.userName = userName;
        this.score = score;
    }

    public int compareTo(CompetitionUser other){
        if(this.score<other.score){
            return -1;
        }else if(this.score > other.score){
            return 1;
        } else return 0;
    }



}

class MyIndividualCompetitorAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<CompetitionUser> userList;

    public MyIndividualCompetitorAdapter(Activity context, ArrayList<CompetitionUser> userList) {
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
