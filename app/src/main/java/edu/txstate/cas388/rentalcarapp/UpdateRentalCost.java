package edu.txstate.cas388.rentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class UpdateRentalCost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rental_cost);

        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);

        final int intId = sharedPref2.getInt("id", 0);
        final String strName = sharedPref2.getString("name", "");
        final String strColor = sharedPref2.getString("color", "");
        final String strBrand = sharedPref2.getString("brand", "");

        final int index = intId - 101; //this replaces a switch statement (for example, if the passed in ID of the car is 102, the Ford F-150, then the put will correspond to index 1 in the JSON

        TextView txtCarID = findViewById(R.id.txtCarId2);
        TextView txtCarName = findViewById(R.id.txtCarName2);
        final EditText txtNewCost = findViewById(R.id.edtxtNewCost);
        Button btnUpdateCost = findViewById(R.id.btnUpdateCost);
        Button btnHome = findViewById(R.id.btnHome);

        String convertedID = Integer.toString(intId);

        txtCarID.setText("Car ID: " + convertedID);
        txtCarName.setText("Model: " + strName);

        btnUpdateCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final double dblNewCost = Double.parseDouble(txtNewCost.getText().toString());

                String url = "Cars/" +index +".json/";
                JSONObject uCar = new JSONObject();
                try {
                    uCar.put("Cost", dblNewCost);
                    uCar.put("Id", intId);
                    uCar.put("Brand", strBrand);
                    uCar.put("Color", strColor);
                    uCar.put("Name", strName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                StringEntity entity = null;
                try {

                    entity = new StringEntity(uCar.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                RestAPIClient.put(UpdateRentalCost.this, url, entity,
                        "application/json", new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                Toast.makeText(UpdateRentalCost.this, "The new price of the " + strName+ " is $" + dblNewCost, Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Toast.makeText(UpdateRentalCost.this, responseString, Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateRentalCost.this, MainActivity.class));
            }
        });
    }
}
