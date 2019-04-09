package edu.txstate.cas388.rentalcarapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Cars_List extends ListActivity {

    int[] intCarID = {1,2,3,4,5};
    String[] strCarName = {"Camry", "F-150", "Model S", "GS-350", "Tundra"};
    String[] strCarBrand = {"Toyota", "Ford", "Tesla", "Lexus", "Toyota"};
    double[] dblCarCostPerDay = {40.5, 45, 150, 115, 47.5};
    String[] strCarUrl = {"https://www.toyota.com/camry/camry-features", "https://www.ford.com/trucks/f150/features/", "https://www.tesla.com/models", "https://www.lexus.com/models/GS/specifications/gs-350-f-sport-rwd", "https://www.toyota.com/tundra/tundra-features"};
    int[] intCarImage = {R.drawable.camry, R.drawable.ford, R.drawable.tesla, R.drawable.lexus, R.drawable.tundra};
    List<Car> carList;

    //TextView listItem = findViewById(R.id.txtCarsRow);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cars__list);
        carList = new ArrayList<Car>();

        for (int i=0; i < strCarName.length; i++) {
            Car car = new Car(intCarID[i],strCarName[i],strCarBrand[i],dblCarCostPerDay[i],intCarImage[i], strCarUrl[i]);
            carList.add(car);
        }
        //listItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon, 0, 0, 0);
        setListAdapter(new ArrayAdapter<>(Cars_List.this, R.layout.activity_cars__list, R.id.txtCarsRow, strCarName));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);

        Car selectedCar = carList.get(position);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Cars_List.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("img", selectedCar.getCarImage());
        editor.putString("name", selectedCar.getCarName());
        editor.putString("brand", selectedCar.getCarBrand());
        editor.putFloat("cost", (float) selectedCar.getCostPerDay());
        editor.putInt("id", selectedCar.getCarID());
        editor.putString("url",selectedCar.getCarUrl());

        editor.commit();

        startActivity(new Intent(Cars_List.this, Car_Info.class));
    }
}
