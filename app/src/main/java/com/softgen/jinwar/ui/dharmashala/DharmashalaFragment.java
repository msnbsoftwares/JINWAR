package com.softgen.jinwar.ui.dharmashala;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softgen.jinwar.R;
import com.softgen.jinwar.beans.Dharmashala;
import com.softgen.jinwar.network.RequestManager;
import com.softgen.jinwar.network.URLPaths;
import com.softgen.jinwar.network.VolleyResponseListener;
import com.softgen.jinwar.recyclerviews.DharmashalaRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DharmashalaFragment extends Fragment{
    Spinner statesSpinner;
    Spinner citiesSpinner;

    public static Set<String> validStates = new HashSet<>();
    public static Set<String> validCities = new HashSet<>();
    public static Map<String, Set<String> > citiesForStates = new HashMap<>();
    public static Map<String, List<Dharmashala> > dharmashalasForStatesCities = new HashMap<>();
    public static Map<String, Dharmashala> dharmashalaIDToDharmashala = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_dharmashala, container, false);

        JSONObject headers = new JSONObject();

        RequestManager.getInstance(root.getContext()).executeSyncPostRequest(URLPaths.AVAILABLE_DHARMASHALAS, headers, new VolleyResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                if(response!=null){
                    validCities.clear();    validStates.clear();    citiesForStates.clear();    dharmashalasForStatesCities.clear();    dharmashalaIDToDharmashala.clear();
                    JSONArray availableDharmashalas = new JSONArray();
                    try {
                        availableDharmashalas = new JSONArray(response.getString("available_dharmashalas"));

                        System.out.println(availableDharmashalas.toString());

                        for(int count = 0; count < availableDharmashalas.length(); count++){
                            Dharmashala dharmashala = new Dharmashala((JSONObject) availableDharmashalas.get(count));

                            String dharmashalaID = dharmashala.getDharmashalaID();
                            String dharmashalaCity = dharmashala.getCityName();
                            String dharmashalaState = dharmashala.getStateName();
                            String stateCity = dharmashalaState+"@##@"+dharmashalaCity;

                            validCities.add(dharmashalaCity);
                            validStates.add(dharmashalaState);

                            if(citiesForStates.containsKey(dharmashalaState)){
                                citiesForStates.get(dharmashalaState).add(dharmashalaCity);
                            }else{
                                Set<String> citiesSet = new HashSet<>();
                                citiesSet.add(dharmashalaCity);

                                citiesForStates.put(dharmashalaState, citiesSet);
                            }

                            if(dharmashalasForStatesCities.containsKey(stateCity)){
                                dharmashalasForStatesCities.get(stateCity).add(dharmashala);
                            }else{
                                List<Dharmashala> dharmashalasList = new ArrayList<>();
                                dharmashalasList.add(dharmashala);

                                dharmashalasForStatesCities.put(stateCity, dharmashalasList);
                            }

                            dharmashalaIDToDharmashala.put(dharmashalaID, dharmashala);
                        }

                        statesSpinner = root.findViewById(R.id.dharmashalaState);
                        citiesSpinner = root.findViewById(R.id.dharmashalaCity);

                        ArrayAdapter<String> statesAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, validStates.toArray(new String[validStates.size()]));
                        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        statesSpinner.setAdapter(statesAdapter);

                        /*
                        * On selection of any value from states
                        * */
                        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                final String selectedState = (String) parent.getItemAtPosition(position);
                                Set<String> citiesSet = citiesForStates.get(selectedState);

                                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, citiesSet.toArray(new String[citiesSet.size()]));
                                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                citiesSpinner.setAdapter(citiesAdapter);

                                /*
                                * On selection from any value from cities
                                * */
                                citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedCity = (String) parent.getItemAtPosition(position);

                                        initRecyclerView(root, dharmashalasForStatesCities.get(selectedState+"@##@"+selectedCity));
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                
                            }
                        });
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else{

                }
            }

            @Override
            public void onError(String message) {
                System.out.println("Error aaya hai babu..");
            }
        });

        return root;
    }

    private void initRecyclerView(View view, List<Dharmashala> dharmashalaListForCity){
        RecyclerView dharmashalaRecyclerView = view.findViewById(R.id.availableDharmashalas);
        DharmashalaRecyclerViewAdapter dharmashalaRecyclerViewAdapter = new DharmashalaRecyclerViewAdapter(view.getContext(), dharmashalaListForCity);
        dharmashalaRecyclerView.setAdapter(dharmashalaRecyclerViewAdapter);
        dharmashalaRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}
