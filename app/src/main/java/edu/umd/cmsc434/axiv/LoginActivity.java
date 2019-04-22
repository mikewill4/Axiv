package edu.umd.cmsc434.axiv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class LoginActivity extends AppCompatActivity {

    static{
        AppData applicationData = new AppData();
    }

    private FrameLayout mainFrame;

    private LoginFragment loginFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainFrame = (FrameLayout) findViewById(R.id.login_frame);

        loginFragment = new LoginFragment();

        setFragment(loginFragment);


    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.login_frame, fragment);
        fragmentTransaction.commit();
    }
}
