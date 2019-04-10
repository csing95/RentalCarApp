package edu.txstate.cas388.rentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Car_Info extends AppCompatActivity {

    int intId;
    int intImg;
    double dblCost;
    String strURL;
    String strName;
    String strBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car__info);

        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);

        intId = sharedPref2.getInt("id", 0);
        intImg = sharedPref2.getInt("img", 0);
        dblCost = sharedPref2.getFloat("cost", 0);
        strURL = sharedPref2.getString("url", "");
        strName = sharedPref2.getString("name", "");
        strBrand = sharedPref2.getString("brand", "");

        Button btnCostCalculation = findViewById(R.id.btnCalculateCost);
        final Button btnGoToWebsite = findViewById(R.id.btnGoToWebsite);

        TextView txtCarID = findViewById(R.id.txtCarID);
        TextView txtCarBrand = findViewById(R.id.txtCarBrand);
        TextView txtCarName = findViewById(R.id.txtCarName);
        TextView txtCarCostPerDayLabel = findViewById(R.id.txtCostPerDay);
        ImageView imgCarImage = findViewById(R.id.imgCarImage);
        imgCarImage.setImageResource(intImg);

        //Data conversions
        String convertedID = Integer.toString(intId);

        DecimalFormat currency = new DecimalFormat("$###,###.00");
        String convertedCost = currency.format(dblCost);

        txtCarID.setText("Car ID: " + convertedID);
        txtCarBrand.setText("Make: " + strBrand);
        txtCarName.setText("Model: " + strName);
        txtCarCostPerDayLabel.setText("Cost per day: " + convertedCost);

        if (strURL.equals("")){
            btnGoToWebsite.setEnabled(false);
        }

        btnGoToWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnGoToWebsite.isEnabled()){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strURL)));

                }
            }
        });

        final EditText txtNumberOfDays = findViewById(R.id.txtNumberOfDays);
        btnCostCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intDays = Integer.parseInt(txtNumberOfDays.getText().toString());
                double dblTotalCost = dblCost * intDays;
                DecimalFormat currency = new DecimalFormat("$###,###.00");
                Toast.makeText(Car_Info.this, "Total Cost: " + currency.format(dblTotalCost), Toast.LENGTH_LONG).show();

            }
        });
    }
}
