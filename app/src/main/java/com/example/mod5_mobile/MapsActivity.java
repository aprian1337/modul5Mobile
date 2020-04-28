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
        LatLng banyuwangi = new LatLng(-8.3324789,114.0850144);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(banyuwangi, 10));
        LatLng smkm1 = new LatLng(-8.3527384,114.1554808);
        mMap.addMarker(new MarkerOptions().position(smkm1).title("SMK Muhammadiyah 1 Genteng").snippet("Jl. KH. Ahmad Dahlan No.10, Dusun Krajan, Genteng Wetan, Kec. Genteng, Kabupaten Banyuwangi, Jawa Timur 68465"));
        LatLng smkm2 = new LatLng(-8.3703197,114.1657305);
        mMap.addMarker(new MarkerOptions().position(smkm2).title("SMK Muhammadiyah 2 Genteng").snippet("Jalan Raya Hasanudin No.103, Genteng, Dusun Cangaan, Genteng Wetan, Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68465"));
        LatLng smkm3 = new LatLng(-8.50924,114.2616105);
        mMap.addMarker(new MarkerOptions().position(smkm3).title("SMK Muhammadiyah 3 Tegaldlimoe").snippet("Sumber Luhur, Tegaldlimo, Banyuwangi Regency, East Java 68484"));
        LatLng smkm4 = new LatLng(-8.3053305,114.0495634);
        mMap.addMarker(new MarkerOptions().position(smkm4).title("SMK Muhammadiyah 4 Glenmore").snippet("Jl. Raya Pasar Glenmore, Sep Wetan, Sepanjang, Kec. Glenmore, Kabupaten Banyuwangi, Jawa Timur 68466"));
        LatLng smkm5 = new LatLng(-8.3973883,114.2002511);
        mMap.addMarker(new MarkerOptions().position(smkm5).title("SMK Muhammadiyah 5 Srono").snippet("JL. RAYA SRONO ROGOJAMPI, Sukomaju, Kec. Srono, Kab. Banyuwangi Prov. Jawa Timur"));
        LatLng smkm6 = new LatLng(-8.3973759,114.2002509);
        mMap.addMarker(new MarkerOptions().position(smkm6).title("SMK Muhammadiyah 6 Rogojampi").snippet("Jl. KH. Hasyim Asy'ari No. 38, Pancoran Kulon, Rogojampi, Rogojampi - Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68462"));
        LatLng smkm7 = new LatLng(-8.2288199,114.3587067);
        mMap.addMarker(new MarkerOptions().position(smkm7).title("SMK Muhammadiyah 7 Banyuwangi").snippet("Tukangkayu, Banyuwangi Sub-District, Banyuwangi Regency, East Java 68416"));
        LatLng smkm8 = new LatLng(-8.4900343,114.1524912);
        mMap.addMarker(new MarkerOptions().position(smkm8).title("SMK Muhammadiyah 8 Siliragung").snippet("Jl. MT Haryono No.42, Dusun Krajan, Siliragung, Kabupaten Banyuwangi, Jawa Timur 68488"));
        LatLng smkm9 = new LatLng(-7.3877103,108.2914479);
        mMap.addMarker(new MarkerOptions().position(smkm9).title("SMK Muhammadiyah 9 Gambiran").snippet("Jl. Singosari, Sumberjaya, Wringin Agung, Kec. Gambiran, Kabupaten Banyuwangi, Jawa Timur 68486"));
        LatLng smkm10 = new LatLng(-8.473861,114.2159771);
        mMap.addMarker(new MarkerOptions().position(smkm10).title("SMK Muhammadiyah 10 Purwoharjo").snippet("Jl. Brawijaya, Krajan, Purwoharjo, Kabupaten Banyuwangi, Jawa Timur 68483"));
        LatLng smam1 = new LatLng(-8.2163375,114.3614936);
        mMap.addMarker(new MarkerOptions().position(smam1).title("SMA Muhammadiyah 1 Banyuwangi").snippet("Jl. Jaksa Agung Suprapto No.60, Penganjuran, Kec. Banyuwangi, Kabupaten Banyuwangi, Jawa Timur 68416"));
        LatLng smam2 = new LatLng(-8.3639632,114.1421191);
        mMap.addMarker(new MarkerOptions().position(smam2).title("SMA Muhammadiyah 2 Genteng").snippet("Jl. Diponegoro No.60, Dusun Krajan, Genteng Kulon, Kec. Genteng, Kabupaten Banyuwangi, Jawa Timur 68465"));
        LatLng smam3 = new LatLng(-8.361898,114.1535331);
        mMap.addMarker(new MarkerOptions().position(smam3).title("SMA Muhammadiyah 3 Genteng").snippet("Jl. Gajah Mada No.185, Dusun Sawahan, Genteng Kulon, Kec. Genteng, Kabupaten Banyuwangi, Jawa Timur 68465"));
        LatLng smam4 = new LatLng(-8.3904428,114.0799897);
        mMap.addMarker(new MarkerOptions().position(smam4).title("SMA Muhammadiyah 4 Songgon").snippet("Jl. Raya Sragi, Dusun Sragi Tengah, Sragi, Songgon, Kabupaten Banyuwangi, Jawa Timur 68463"));
    }
}