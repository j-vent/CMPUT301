package com.example.simpleparadox.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    private EditText cityName;
    private EditText provinceName;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener{
        void onOkPressed(City newCity, boolean isNewCity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener)  context;
        }
        else{
            throw new RuntimeException(context.toString() +
                    "must implement OnFragmentInteractionListener");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_city_fragment_layout, null);
        cityName = view.findViewById(R.id.city_name_editText);
        provinceName = view.findViewById(R.id.province_editText);

        // savedInstanceState.getBundle().
        boolean isBundleNull = true;
        if(getArguments() != null) {

            // final String cityOut = testCity.getCityName();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            City testCity = (City) getArguments().getSerializable("city");
            // write current name
            System.out.println("printing name " + testCity.getCityName() + testCity.getProvinceName());
            cityName.setText(testCity.getCityName());
            provinceName.setText(testCity.getProvinceName());

            return builder.setView(view).setTitle("Add/Edit city").setNegativeButton("Cancel", null).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    City editedCity = (City) getArguments().getSerializable("city");
                    String city = cityName.getText().toString();
                    String province = provinceName.getText().toString();
                    System.out.println("city " + city);
                    System.out.println("prov " + province);
                    editedCity.setCityName(city);
                    editedCity.setProvinceName(province);
                    System.out.println("city " + editedCity.getCityName());
                    System.out.println("prov " + editedCity.getProvinceName());
                    // cityName.setText(city);
                    // provinceName.setText(province);
                    // System.out.println("citydisp " + cityName.getText());
                    // System.out.println("provdisp " + provinceName.getText());
                    listener.onOkPressed(editedCity, false);
                    // listener.onOkPressed(new City(city, province));
                    //}

                }
            }).create();

        }
        else{
            System.out.println("in elese");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view).setTitle("Add/Edit city").setNegativeButton("Cancel", null).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String city = cityName.getText().toString();
                String province = provinceName.getText().toString();

                /**
                if(testCity != null){
                    testCity.setCityName(city);
                    testCity.setProvinceName(province);
                    System.out.println("updated name" + testCity.getCityName());
                    System.out.println("updated prov" + testCity.getProvinceName());
                    listener.onOkPressed(testCity);
                }
                else{
                **/

                // listener.onOkPressed(testCity);
                listener.onOkPressed(new City(city, province), true);
                //}
            }
        }).create();
        }
    }
    static AddCityFragment newInstance(City city){
        Bundle args = new Bundle(0);
        args.putSerializable("city", city);
        AddCityFragment fragment = new AddCityFragment();
        fragment.setArguments(args);
        City testCity = (City) fragment.getArguments().getSerializable("city");
        System.out.println(testCity.getCityName());
        System.out.println(testCity);
        // System.out.println(fragment.getArguments().getString("city"));
        return fragment;
    }
}

