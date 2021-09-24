package com.example.listcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityView;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    ArrayList<String> cities;
    private Button addBtn;
    // global var to denote city selected in listview
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference listview
        cityView = findViewById(R.id.list_view);

        // create initial string arraylist (mutable unlike string array)
        cities = new ArrayList<>();
        cities.add("Edmonton");
        cities.add("Toronto");
        cities.add("Montreal");
        cities.add("Vancouver");

        // instantiate dataList and populate
        dataList = new ArrayList<>();
        dataList.addAll(cities);

        // setup adapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityView.setAdapter(cityAdapter);

        // listen for add button click
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // show edittext and confirm button
                setVisibilityAddCityComponents(true);
            }
        });

        // add new city to listview
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText cityText = (EditText) findViewById(R.id.city_editText);
                String cityToAdd = cityText.getText().toString();
                if(cityToAdd.length() > 0){
                    dataList.add(cityToAdd);
                    // notify adapter that a new city was added
                    cityAdapter.notifyDataSetChanged();
                    // hides keyboard
                    cityText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    // hides add city edittext + confirm button
                    setVisibilityAddCityComponents(false);
                }
            }
        });

        // update which city is selected
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = (String) (cityView.getItemAtPosition(position));
            }
        });

        // listen for delete button click
        Button deleteBtn = (Button) findViewById(R.id.delBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dataList.remove(selectedCity);
                // notify adapter that a city was deleted
                cityAdapter.notifyDataSetChanged();
            }
        });
    }

    /** toggle visibility of editText and confirm button **/
    public void setVisibilityAddCityComponents(boolean isVisible){
        int visibilityCode = (isVisible) ? View.VISIBLE : View.INVISIBLE;
        EditText cityText = (EditText) findViewById(R.id.city_editText);
        cityText.getText().clear();
        cityText.setVisibility(visibilityCode);
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        confirmBtn.setVisibility(visibilityCode);
    }


}