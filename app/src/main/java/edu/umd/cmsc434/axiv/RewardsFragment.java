package edu.umd.cmsc434.axiv;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {


    private ListView recentlyAddedListView;
    private ListView trendingListView;
    private ListView foodListView;
    private ListView clothingListView;
    private ListView accessoriesListView;

    public RewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rewards, container, false);

        recentlyAddedListView = (ListView) rootView.findViewById(R.id.rewards_recently_added_listview);
        trendingListView = (ListView) rootView.findViewById(R.id.rewards_trending_listview);
        foodListView = (ListView) rootView.findViewById(R.id.rewards_food_listview);
        clothingListView = (ListView) rootView.findViewById(R.id.rewards_clothing_listview);
        accessoriesListView = (ListView) rootView.findViewById(R.id.rewards_accessories_listview);

        final List<String> rewards = new ArrayList<>();
        rewards.add("Reward #1");
        rewards.add("Reward #2");
        rewards.add("Reward #3");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this.getActivity(),
                android.R.layout.simple_list_item_1,
                rewards
        );

        recentlyAddedListView.setAdapter(arrayAdapter);
        trendingListView.setAdapter(arrayAdapter);
        foodListView.setAdapter(arrayAdapter);
        clothingListView.setAdapter(arrayAdapter);
        accessoriesListView.setAdapter(arrayAdapter);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        recentlyAddedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(rewards.get(position));
                builder.setMessage("Implement me");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        trendingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(rewards.get(position));
                builder.setMessage("Implement me");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(rewards.get(position));
                builder.setMessage("Implement me");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        clothingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(rewards.get(position));
                builder.setMessage("Implement me");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        accessoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(rewards.get(position));
                builder.setMessage("Implement me");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        getActivity().getMenuInflater().inflate(R.menu.points, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

}
