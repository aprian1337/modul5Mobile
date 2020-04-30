package com.aprian1337.appsforschooldetection;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private GoogleMap mGoogleMap;
    private Button btnCariSekolah;
    private ProgressBar pBar;
    private double mLatitude = 0;
    private double mLongitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCariSekolah = findViewById(R.id.btnCariSekolah);
        pBar = findViewById(R.id.pBar);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                initMap();
            }
        });

        btnCariSekolah.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String sb = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + mLatitude + "," + mLongitude +
                        "&radius=2500" +
                        "&types=school" +
                        "&key=" + getResources().getString(R.string.api_key_android);

                onCari();
                new PlacesTask().execute(sb);
            }
        });
    }

    private void initMap() {
        onCari();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 115);
            return;
        }

        if (mGoogleMap != null) {
            mGoogleMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
    }
    LatLng myLocation;
    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        myLocation = new LatLng(mLatitude, mLongitude);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        onAfterCari();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private class PlacesTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... url) {
            String data = null;
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                onAfterCari();
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            new ParserTask().execute(result);
        }

    }

    private String downloadUrl(String strUrl) {
        String data = "";
        InputStream iStream;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            br.close();
            iStream.close();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

        JSONObject jObject;

        @Override
        protected List<HashMap<String, String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            ParseForJSON parserPlace = new ParseForJSON();

            try {
                jObject = new JSONObject(jsonData[0]);
                places = parserPlace.parse(jObject);
            } catch (Exception e) {
                onAfterCari();
                e.printStackTrace();
            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {
            // clear map sebelumnya
            mGoogleMap.clear();
            for (int i = 0; i < list.size(); i++) {
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> hmPlace = list.get(i);
                double lat = Double.parseDouble(hmPlace.get("lat"));
                double lng = Double.parseDouble(hmPlace.get("lng"));
                LatLng latLng = new LatLng(lat, lng);
                String nama = hmPlace.get("place_name");
                String namaJln = hmPlace.get("vicinity");
                double distance = CalculationByDistance(myLocation,latLng);
                String distances = String.format("%.2f", distance);
                markerOptions.position(latLng);
                markerOptions.title(nama + " ("+distances+" km)");
                markerOptions.snippet(namaJln);
                mGoogleMap.addMarker(markerOptions);
//                Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
//                .add(myLocation, latLng)
//                .width(5)
//                .color(Color.RED));
            }

            onAfterCari();
        }
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 115) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        initMap();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 115);
                    }
                }
            }
        }
    }

    private void onCari() {
        pBar.setVisibility(View.VISIBLE);
        btnCariSekolah.setVisibility(View.GONE);
    }

    private void onAfterCari() {
        pBar.setVisibility(View.GONE);
        btnCariSekolah.setVisibility(View.VISIBLE);
    }
}