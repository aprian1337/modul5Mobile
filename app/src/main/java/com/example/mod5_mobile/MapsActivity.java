package com.example.mod5_mobile;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng smkm1 = new LatLng(-8.3527384,114.1554808);
        mMap.addMarker(new MarkerOptions().position(smkm1).title("SMK Muhammadiyah 1 Genteng").snippet("Jl. KH. Ahmad Dahlan No.10, Dusun Krajan, Genteng Wetan, Kec. Genteng, Kabupaten Banyuwangi, Jawa Timur 68465"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(smkm1,15));
        LatLng smkm2 = new LatLng(-8.3703197,114.1657305);
        mMap.addMarker(new MarkerOptions().position(smkm2).title("SMK Muhammadiyah 2 Genteng").snippet("Jalan Raya Hasanudin No.103, Genteng, Dusun Cangaan, Genteng Wetan, Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68465"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(smkm2,15));
//        -8.50924,114.2616105 //SMKM3
//        -8.3053305,114.0495634 //SMKM4
//        -8.3973883,114.2002511 //SMKM5
//        -8.3973759,114.2002509 //SMKM6
//        -8.3403975,112.9453714 //SMKM7
//        -8.4900343,114.1524912 //SMKM8
//        -7.3877103,108.2914479 //SMKM9
//        -8.473861,114.2159771 //SMKM10
//        -8.2163375,114.3614936 //SMAM1
//        -8.3639632,114.1421191 //SMAM2
//        -8.3639632,114.1421191 //SMAM2-2
//        -8.361898,114.1535331 //SMAM3
//        -8.3904428,114.0799897 //SMAM4
    }
}
