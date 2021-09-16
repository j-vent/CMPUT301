package com.example.listcity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // a. reference listview
        cityView = findViewById(R.id.list_view);

        // b.create string array
        cities = new ArrayList<>();
        cities.add("Edmonton");
        cities.add("Toronto");
        cities.add("Montreal");
        cities.add("Vancouver");

        // c./d. instantiate dataList and add to datalist
        dataList = new ArrayList<>();
        dataList.addAll(cities);

        // setup adapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityView.setAdapter(cityAdapter);

        // setup add button
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addCity();
                cityAdapter.notifyDataSetChanged();
            }
        });
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText cityText = (EditText) findViewById(R.id.city_editText);
                // TODO: add condition to only add if textbox is not empty
                dataList.add(cityText.getText().toString());
                cityAdapter.notifyDataSetChanged();
            }
        });

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TextView textView = (TextView) view.findViewById(R.id.content);
                // String selectedCity = textView.getText().toString();
                // change colour to denote selection
                selectedCity = (String) (cityView.getItemAtPosition(position));
                System.out.println("selected " + selectedCity);

            }
        });

        Button deleteBtn = (Button) findViewById(R.id.delBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TODO: add condition to only deleted if selected city is not empty
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
            }
        });


    }
    public void addCity(){
        // toggle visibility
        EditText cityText = (EditText) findViewById(R.id.city_editText);
        cityText.setVisibility(View.VISIBLE);
        Button confirmBtn = (Button) findViewById(R.id.confirmBtn);
        confirmBtn.setVisibility(View.VISIBLE);
//        confirmBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                dataList.add(cityText.getText().toString());
//                // cities.add("justine");
//
//            }
//        });

    }


}