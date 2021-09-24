package com.example.simpleparadox.listycity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> cityDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities ={"Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"};
        String []provinces = {"AB", "BC", "ON", "CO", "CO", "CA"};
        cityDataList = new ArrayList<>();

        for (int i =0; i < cities.length; i++){
            cityDataList.add((new City(cities[i], provinces[i])));
        }

        cityAdapter = new CustomList(this, cityDataList);

        cityList.setAdapter(cityAdapter);

    final FloatingActionButton addCityButton = findViewById(R.id.add_city_button);
    addCityButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            new AddCityFragment().show(getSupportFragmentManager(), "ADD CITY");
        }
    });

        // update which city is selected
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City selectedCity = (City) cityList.getItemAtPosition(position);
                DialogFragment fr = new AddCityFragment().newInstance(selectedCity);
                fr.show(getSupportFragmentManager(), "EDIT CITY");
                // fr.dismiss();
                // System.out.println("selected city " +  selectedCity.getCityName());
                // update with setters
            }
        });

    }

    @Override
    public void onOkPressed(City newCity, boolean isNewCity){

        // add a flag of toAdd or just edit
        //
        if(isNewCity){
            System.out.println("adding new city" + newCity.getCityName());
            cityAdapter.add(newCity);
        } else{
            System.out.println("edited");
            return;
        }
        // cityAdapter.remove(newCity);
        // cityAdapter.insert(newCity, 0);
        // cityDataList.set(0, newCity);
        // cityAdapter.notifyDataSetChanged();
    }

}
