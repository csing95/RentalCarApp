package edu.txstate.cas388.rentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
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
    String strColor;
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
        strColor = sharedPref2.getString("color", "");
        strName = sharedPref2.getString("name", "");
        strBrand = sharedPref2.getString("brand", "");

        Button btnCostCalculation = findViewById(R.id.btnCalculateCost);
        final Button btnUpdate = findViewById(R.id.btnUpdate);

        TextView txtCarID = findViewById(R.id.txtCarID);
        TextView txtCarBrand = findViewById(R.id.txtCarBrand);
        TextView txtCarName = findViewById(R.id.txtCarName);
        TextView txtCarColor = findViewById(R.id.txtCarColor);
        TextView txtCarCostPerDayLabel = findViewById(R.id.txtCostPerDay);
        ImageView imgCarImage = findViewById(R.id.imgCarImage);
        //imgCarImage.setImageResource(intImg);

        //Data conversions
        String convertedID = Integer.toString(intId);

        if (intId==101){
            imgCarImage.setImageResource(R.drawable.pic1);
        } else if (intId==102) {
            imgCarImage.setImageResource(R.drawable.pic2);
        } else if (intId==103) {
            imgCarImage.setImageResource(R.drawable.pic3);
        } else if (intId==104) {
            imgCarImage.setImageResource(R.drawable.pic4);
        } else if (intId==105) {
            imgCarImage.setImageResource(R.drawable.pic5);
        }

        DecimalFormat currency = new DecimalFormat("$###,###.00");
        String convertedCost = currency.format(dblCost);

        txtCarID.setText("Car ID: " + convertedID);
        txtCarBrand.setText("Make: " + strBrand);
        txtCarName.setText("Model: " + strName);
        txtCarColor.setText("Color: " + strColor);
        txtCarCostPerDayLabel.setText("Cost per day: " + convertedCost);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(strURL))); //This is if you want to go to a website. Leaving this here for studying purposes

                Intent intent = new Intent(Car_Info.this, UpdateRentalCost.class);
                intent.putExtra("id",intId);
                startActivity(intent);

            }
        });

        final EditText txtNumberOfDays = findViewById(R.id.txtNumberOfDays);
        btnCostCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intDays = Integer.parseInt(txtNumberOfDays.getText().toString());
                if (intDays<30) {
                    double dblTotalCost = dblCost * intDays;
                    DecimalFormat currency = new DecimalFormat("$###,###.00");
                    Toast.makeText(Car_Info.this, "Total Cost: " + currency.format(dblTotalCost), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Car_Info.this, "Rental requested longer than 30 days, please call 512-777-2222", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
