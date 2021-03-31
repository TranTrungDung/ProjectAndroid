package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link google#newInstance} factory method to
 * create an instance of this fragment.
 */
public class google extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    MapView mapView;
    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public google() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment google.
     */
    // TODO: Rename and change types and number of parameters
    public static google newInstance(String param1, String param2) {
        google fragment = new google();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_google, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.fragment);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //,
        LatLng sydney = new LatLng( 21.007495801671514, 105.8247061009694);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Shop MIKENCO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17));
        LatLng sy = new LatLng( 20.993554214060847, 105.84968630914871);
        mMap.addMarker(new MarkerOptions().position(sy).title("Shop MIKENCO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sy,11));
        LatLng sk = new LatLng( 20.972433464203363, 105.7855142942802);
        mMap.addMarker(new MarkerOptions().position(sk).title("Shop MIKENCO"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sk,11));

    }
}