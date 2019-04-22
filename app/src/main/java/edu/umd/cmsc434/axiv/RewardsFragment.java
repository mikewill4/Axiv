package edu.umd.cmsc434.axiv;


import android.app.Activity;
import android.app.UiAutomation;
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
import android.widget.ProgressBar;
import android.widget.TextView;

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

        final RewardsListAdapter recentlyAddedAdapter = new RewardsListAdapter(this.getActivity(), AppData.recentlyAddedRewards);
        RewardsListAdapter trendingAdapter = new RewardsListAdapter(this.getActivity(), AppData.trendingRewards);
        RewardsListAdapter foodAdapter = new RewardsListAdapter(this.getActivity(), AppData.foodRewards);
        RewardsListAdapter clothingAdapter = new RewardsListAdapter(this.getActivity(), AppData.clothingRewards);
        RewardsListAdapter accessoriesAdapter = new RewardsListAdapter(this.getActivity(), AppData.accessoriesRewards);


        recentlyAddedListView.setAdapter(recentlyAddedAdapter);
        trendingListView.setAdapter(trendingAdapter);
        foodListView.setAdapter(foodAdapter);
        clothingListView.setAdapter(clothingAdapter);
        accessoriesListView.setAdapter(accessoriesAdapter);

        UIUtils.setListViewHeightBasedOnItems(recentlyAddedListView);
        UIUtils.setListViewHeightBasedOnItems(trendingListView);
        UIUtils.setListViewHeightBasedOnItems(foodListView);
        UIUtils.setListViewHeightBasedOnItems(clothingListView);
        UIUtils.setListViewHeightBasedOnItems(accessoriesListView);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        recentlyAddedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                builder.setTitle(AppData.recentlyAddedRewards.get(position).sponsorName);
                builder.setMessage(AppData.recentlyAddedRewards.get(position).deal + " for " + AppData.recentlyAddedRewards.get(position).pointValue + " points.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.setPositiveButton("Purhcase", new DialogInterface.OnClickListener() {
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
                builder.setTitle(AppData.trendingRewards.get(position).sponsorName);
                builder.setMessage(AppData.trendingRewards.get(position).deal + " for " + AppData.trendingRewards.get(position).pointValue + " points.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.setPositiveButton("Purhcase", new DialogInterface.OnClickListener() {
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
                builder.setTitle(AppData.foodRewards.get(position).sponsorName);
                builder.setMessage(AppData.foodRewards.get(position).deal + " for " + AppData.foodRewards.get(position).pointValue + " points.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.setPositiveButton("Purhcase", new DialogInterface.OnClickListener() {
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
                builder.setTitle(AppData.clothingRewards.get(position).sponsorName);
                builder.setMessage(AppData.clothingRewards.get(position).deal + " for " + AppData.clothingRewards.get(position).pointValue + " points.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.setPositiveButton("Purhcase", new DialogInterface.OnClickListener() {
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
                builder.setTitle(AppData.accessoriesRewards.get(position).sponsorName);
                builder.setMessage(AppData.accessoriesRewards.get(position).deal + " for " + AppData.accessoriesRewards.get(position).pointValue + " points.");
                builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Dialog will be dismissed when clicked
                    }
                });
                builder.setPositiveButton("Purhcase", new DialogInterface.OnClickListener() {
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

class RewardsListAdapter extends ArrayAdapter<AppData.RewardInfo> {

    private final Activity context;
    private final ArrayList<AppData.RewardInfo> rewardsList;

    public RewardsListAdapter(Activity context, ArrayList<AppData.RewardInfo> rewardsList) {
        super(context, R.layout.listitem_featured_competition);
        this.context = context;
        this.rewardsList = rewardsList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return rewardsList.size();
    }


    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.listitem_reward, null, true);

        TextView sponsor = (TextView) rowView.findViewById(R.id.reward_org_name);
        TextView deal = (TextView) rowView.findViewById(R.id.reward_deal);
        TextView pointValue = (TextView) rowView.findViewById(R.id.reward_point_value);

        sponsor.setText(rewardsList.get(position).sponsorName);
        deal.setText(rewardsList.get(position).deal);
        pointValue.setText("Points:\n" + rewardsList.get(position).pointValue);

        return rowView;

    }
}
