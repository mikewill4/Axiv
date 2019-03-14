package edu.umd.cmsc434.axiv;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNavigation;
    private FrameLayout mainFrame;

    private MyHealthFragment myHealthFragment;
    private MetricsFragment metricsFragment;
    private CompeteFragment competeFragment;
    private RewardsFragment rewardsFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNavigation = (BottomNavigationView) findViewById(R.id.main_navigation);

        myHealthFragment = new MyHealthFragment();
        metricsFragment = new MetricsFragment();
        competeFragment = new CompeteFragment();
        rewardsFragment = new RewardsFragment();
        settingsFragment = new SettingsFragment();

        setFragment(myHealthFragment);
        getSupportActionBar().setTitle(R.string.my_health_title);

        mainNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_my_health:
                        setFragment(myHealthFragment);
                        getSupportActionBar().setTitle(R.string.my_health_title);
                        return true;
                    case R.id.navigation_metrics:
                        setFragment(metricsFragment);
                        getSupportActionBar().setTitle(R.string.metrics_title);
                        return true;
                    case R.id.navigation_compete:
                        setFragment(competeFragment);
                        getSupportActionBar().setTitle(R.string.compete_title);
                        return true;
                    case R.id.navigation_rewards:
                        setFragment(rewardsFragment);
                        getSupportActionBar().setTitle(R.string.rewards_title);
                        return true;
                    case R.id.navigation_settings:
                        setFragment(settingsFragment);
                        getSupportActionBar().setTitle(R.string.settings_title);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
