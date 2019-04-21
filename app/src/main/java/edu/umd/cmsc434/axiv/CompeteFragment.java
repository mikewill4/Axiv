package edu.umd.cmsc434.axiv;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.umd.cmsc434.axiv.AppData.PrivateCompetitionInfo;
import edu.umd.cmsc434.axiv.AppData.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompeteFragment extends Fragment {

    private ListView featuredListView;
    private ListView privatelistView;
    private ListView invites;

    private Button createNewCompetition;




    public CompeteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View competeView = inflater.inflate(R.layout.fragment_compete, container, false);

        MyPrivateListAdapter privateListAdapter = new MyPrivateListAdapter(this.getActivity(),AppData.userPrivateCompetitions);
        privatelistView = (ListView) competeView.findViewById(R.id.compete_private_listview);
        privatelistView.setAdapter(privateListAdapter);
        UIUtils.setListViewHeightBasedOnItems(privatelistView);

        MyFeaturedListAdapter featuredListAdapter = new MyFeaturedListAdapter(this.getActivity(),AppData.userFeaturedCompetitions);
        featuredListView = (ListView) competeView.findViewById(R.id.compete_featured_listview);
        featuredListView.setAdapter(featuredListAdapter);
        UIUtils.setListViewHeightBasedOnItems(featuredListView);

        MyInvitesListAdapter invitesListAdapter = new MyInvitesListAdapter(this.getActivity(),AppData.userCompetitionInvites, privateListAdapter);
        invites = (ListView) competeView.findViewById(R.id.compete_invites_listview);
        invites.setAdapter(invitesListAdapter);
        UIUtils.setListViewHeightBasedOnItems(invites);

        createNewCompetition = (Button) competeView.findViewById(R.id.compete_create_new_button);

        createNewCompetition.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),CreateNewCompetitionActivity.class));
            }
        });


        privatelistView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent indvComp = new Intent(getActivity(),IndividualCompetition.class);
                indvComp.putExtra("userList", position);

                startActivity(indvComp);
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        featuredListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(AppData.userFeaturedCompetitions.get(position).progressPercentage >= 100){
                    builder.setTitle("CONGRATS");
                    builder.setMessage("Go into any " + AppData.userFeaturedCompetitions.get(position).sponsorName + " store and show them this app to claim your prize!");
                    builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Dialog will be dismissed when clicked
                        }
                    });
                    builder.show();

                } else {
                    builder.setTitle("ALMOST THERE");
                    builder.setMessage("So close to the goal, you can almost taste it!");
                    builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Dialog will be dismissed when clicked
                        }
                    });
                    builder.show();
                }
            }
        });



        // Inflate the layout for this fragment
        return competeView;
    }

}



