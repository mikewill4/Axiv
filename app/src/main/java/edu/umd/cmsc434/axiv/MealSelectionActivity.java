package edu.umd.cmsc434.axiv;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MealSelectionActivity extends AppCompatActivity {

    Intent intent;
    public  static final int RequestPermissionCode  = 1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_selection);

        EnableRuntimePermission();

        Button mealPictureButton = (Button) findViewById(R.id.meal_picture_button);
        Button mealBarcodeButton = (Button) findViewById(R.id.meal_barcode_button);
        Button mealManualInputButton = (Button) findViewById(R.id.meal_manual_input_button);

        mealBarcodeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast featureNotFinished = Toast.makeText(MealSelectionActivity.this,"This Feature Is Not Completed Yet. Try The Buttons Below!", Toast.LENGTH_LONG);
                featureNotFinished.show();
            }
        });


        mealPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });

        mealManualInputButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MealSelectionActivity.this, MealManualInputActivity.class));
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            startActivity(new Intent(this,MealConfirmationActivity.class));
        }
    }

    public void EnableRuntimePermission(){
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.CAMERA}, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent newIntent = new Intent("android.intent.action.HOMEPAGE");
        newIntent.putExtra("currId", 1);
        startActivity(newIntent);
    }
}
