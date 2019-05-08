package edu.txstate.cas388.rentalcarapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class Cars_List extends ListActivity {

//    int[] intCarID = {1,2,3,4,5};
//    String[] strCarName = {"Camry", "F-150", "Model S", "GS-350", "Tundra"};
//    String[] strCarBrand = {"Toyota", "Ford", "Tesla", "Lexus", "Toyota"};
//    double[] dblCarCostPerDay = {40.5, 45, 150, 115, 47.5};
//    String[] strCarUrl = {"https://www.toyota.com/camry/camry-features", "https://www.ford.com/trucks/f150/features/", "https://www.tesla.com/models", "https://www.lexus.com/models/GS/specifications/gs-350-f-sport-rwd", "https://www.toyota.com/tundra/tundra-features"};
//    int[] intCarImage = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    List<Car> carList;

    //TextView listItem = findViewById(R.id.txtCarsRow);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cars__list);
//        carList = new ArrayList<Car>();
//
//        for (int i=0; i < strCarName.length; i++) {
//            Car car = new Car(intCarID[i],strCarName[i],strCarBrand[i],dblCarCostPerDay[i],intCarImage[i], strCarUrl[i]);
//            carList.add(car);
//        }
//        //listItem.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon, 0, 0, 0);
//        setListAdapter(new ArrayAdapter<>(Cars_List.this, R.layout.activity_cars__list, R.id.txtCarRows, strCarName));
        getCars();
    }

    void getCars(){
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));
        RestAPIClient.get(Cars_List.this, "Cars.json", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        carList = new ArrayList<Car>();
                        System.out.println("JSON Response: "+response);
                        for (int i=0; i <response.length(); i++){
                            try {
                                carList.add(new Car(response.getJSONObject(i)));
                            } catch (Exception ex ) {
                                System.out.println("This got caught");
                                ex.printStackTrace();
                            }
                        }
                        setListAdapter(new ArrayAdapter<Car>(Cars_List.this, R.layout.activity_cars__list,
                                R.id.txtCarRows , carList));
                    }

                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        carList = new ArrayList<Car>();
                        Iterator<String> keys = response.keys();
                        while (keys.hasNext()){
                            String key = keys.next();
                            try {
                                carList.add( new Car(response.getJSONObject(key)));
                            } catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        setListAdapter(new ArrayAdapter<Car>(Cars_List.this, R.layout.activity_cars__list,
                                R.id.txtCarRows , carList));
                    }
                });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);

        Car selectedCar = carList.get(position);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Cars_List.this);
        SharedPreferences.Editor editor = sharedPref.edit();


        editor.putString("name", selectedCar.getCarName());
        editor.putString("brand", selectedCar.getCarBrand());
        editor.putFloat("cost", (float) selectedCar.getCostPerDay());
        editor.putInt("id", selectedCar.getCarID());
        editor.putString("color",selectedCar.getCarColor());

        editor.commit();

        startActivity(new Intent(Cars_List.this, Car_Info.class));
    }
}
