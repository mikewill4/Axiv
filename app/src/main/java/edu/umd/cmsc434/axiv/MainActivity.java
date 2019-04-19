package edu.umd.cmsc434.axiv;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNavigation;
    private FrameLayout mainFrame;


    private MyHealthFragment myHealthFragment;
    private MetricsFragment metricsFragment;
    private CompeteFragment competeFragment;
    private RewardsFragment rewardsFragment;
    private SettingsFragment settingsFragment;

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    // View pager
    private static final int NUM_PAGES = 5;
    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;
    private MenuItem prevMenuItem;

    // Saving state
    private int currId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }


        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNavigation = (BottomNavigationView) findViewById(R.id.main_navigation);

        myHealthFragment = new MyHealthFragment();
        metricsFragment = new MetricsFragment();
        competeFragment = new CompeteFragment();
        rewardsFragment = new RewardsFragment();
        settingsFragment = new SettingsFragment();

        //setFragment(myHealthFragment);
        getSupportActionBar().setTitle(R.string.my_health_title);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_my_health:
                        //setFragment(myHealthFragment);
                        pager.setCurrentItem(0);
                        getSupportActionBar().setTitle(R.string.my_health_title);
                        break;
                    case R.id.navigation_metrics:
                        //setFragment(metricsFragment);
                        pager.setCurrentItem(1);
                        getSupportActionBar().setTitle(R.string.track_metrics);
                        break;
                    case R.id.navigation_compete:
                        //setFragment(competeFragment);
                        pager.setCurrentItem(2);
                        getSupportActionBar().setTitle(R.string.compete_title);
                        break;
                    case R.id.navigation_rewards:
                        //setFragment(rewardsFragment);
                        pager.setCurrentItem(3);
                        getSupportActionBar().setTitle(R.string.rewards_title);
                        break;
                    case R.id.navigation_settings:
                        //setFragment(settingsFragment);
                        pager.setCurrentItem(4);
                        getSupportActionBar().setTitle(R.string.settings_title);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mainNavigation.getMenu().getItem(position).setChecked(false);
                }
                mainNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mainNavigation.getMenu().getItem(position);
                switch (position) {
                    case 0:
                        getSupportActionBar().setTitle(R.string.my_health_title);
                        break;
                    case 1:
                        getSupportActionBar().setTitle(R.string.track_metrics);
                        break;
                    case 2:
                        getSupportActionBar().setTitle(R.string.compete_title);
                        break;
                    case 3:
                        getSupportActionBar().setTitle(R.string.rewards_title);
                        break;
                    case 4:
                        getSupportActionBar().setTitle(R.string.settings_title);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pagerAdapter.addFragment(myHealthFragment);
        pagerAdapter.addFragment(metricsFragment);
        pagerAdapter.addFragment(competeFragment);
        pagerAdapter.addFragment(rewardsFragment);
        pagerAdapter.addFragment(settingsFragment);
        pager.setAdapter(pagerAdapter);

    }

//    private void setFragment(Fragment fragment) {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.replace(R.id.main_frame, fragment);
//        fragmentTransaction.commit();
//        manager.executePendingTransactions();
//    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getExtras() != null) {
            currId = getIntent().getExtras().getInt("currId");
            pager.setCurrentItem(currId);
            switch (currId) {
                case 0:
                    getSupportActionBar().setTitle(R.string.my_health_title);
                    break;
                case 1:
                    getSupportActionBar().setTitle(R.string.track_metrics);
                    break;
                case 2:
                    getSupportActionBar().setTitle(R.string.compete_title);
                    break;
                case 3:
                    getSupportActionBar().setTitle(R.string.rewards_title);
                    break;
                case 4:
                    getSupportActionBar().setTitle(R.string.settings_title);
                    break;
                default:
                    break;
            }
        }
    }
}
