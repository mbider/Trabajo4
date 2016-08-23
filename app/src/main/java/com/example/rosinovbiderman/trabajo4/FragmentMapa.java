package com.example.rosinovbiderman.trabajo4;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentMapa extends Fragment implements OnMapReadyCallback, View.OnClickListener{

    GoogleMap map;
    ArrayList<Ciudad> CiudadesElegidas;
    Button b1, b2, b3, b4;
    Ciudad cElegida;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        String url = "https://tp4ort.firebaseio.com/geonames.json";
        new GeoGameTask().execute(url);

        b1 = (Button) v.findViewById(R.id.btn1);
        b2 = (Button) v.findViewById(R.id.btn2);
        b3 = (Button) v.findViewById(R.id.btn3);
        b4 = (Button) v.findViewById(R.id.btn4);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    private class GeoGameTask extends AsyncTask<String, Void, ArrayList<Ciudad>>{
        @Override
        protected void onPostExecute(ArrayList<Ciudad> Ciudades) {
            super.onPostExecute(Ciudades);
            Random rand = new Random();
            CiudadesElegidas = new ArrayList<>();

            Ciudad c1 = Ciudades.get(rand.nextInt(Ciudades.size()));
            CiudadesElegidas.add(c1);
            b1.setText(c1.name);
            Ciudades.remove(c1);
            Ciudad c2 = Ciudades.get(rand.nextInt(Ciudades.size()));
            CiudadesElegidas.add(c2);
            b2.setText(c2.name);
            Ciudades.remove(c2);
            Ciudad c3 = Ciudades.get(rand.nextInt(Ciudades.size()));
            CiudadesElegidas.add(c3);
            b3.setText(c3.name);
            Ciudades.remove(c3);
            Ciudad c4 = Ciudades.get(rand.nextInt(Ciudades.size()));
            CiudadesElegidas.add(c4);
            b4.setText(c4.name);
            Ciudades.remove(c4);

            cElegida = CiudadesElegidas.get(rand.nextInt(CiudadesElegidas.size()));

            LatLng latLng = new LatLng(cElegida.lat,cElegida.lng);
            CameraUpdate ciudad = CameraUpdateFactory.newLatLng(latLng);
            map.moveCamera(ciudad);

            CameraUpdate zoom = CameraUpdateFactory.zoomTo(2);
            map.animateCamera(zoom);

            MarkerOptions mo = new MarkerOptions()
                    .position(latLng);
            map.addMarker(mo);


        }

        @Override
        protected ArrayList<Ciudad> doInBackground(String... parametros) {
            String url = parametros[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return parsearResultado(response.body().string());
            } catch (IOException |JSONException e) {
                Log.d("Error","Error maquina");
                return null;
            }
        }

        ArrayList<Ciudad> parsearResultado(String JSONstr) throws JSONException {
            ArrayList<Ciudad> Ciudades = new ArrayList<>();
            JSONArray JSONciudades = new JSONArray(JSONstr);
            for(int i = 0; i < JSONciudades.length(); i++){
                JSONObject JSONciudad = JSONciudades.getJSONObject(i);
                String clase = JSONciudad.getString("clase");
                String countrycode = JSONciudad.getString("countrycode");
                double lat = JSONciudad.getDouble("lat");
                double lng = JSONciudad.getDouble("lng");
                String name = JSONciudad.getString("name");
                int population = JSONciudad.getInt("population");

                Ciudad c = new Ciudad(clase, countrycode, lat, lng, name, population);
                Ciudades.add(c);
            }
            return Ciudades;
        }
    }

    @Override
    public void onClick(View view) {
        Toast msj;
        switch (view.getId()) {
            case R.id.btn1:
                if (b1.getText() == cElegida.name) {
                    msj = Toast.makeText(this.getContext(), "Correcto!", Toast.LENGTH_SHORT);
                } else {
                    msj = Toast.makeText(this.getContext(), "Incorrecto, intentalo otra vez", Toast.LENGTH_SHORT);
                }
                msj.show();
                break;
            case R.id.btn2:
                if (b2.getText() == cElegida.name) {
                    msj = Toast.makeText(this.getContext(), "Correcto!", Toast.LENGTH_SHORT);
                } else {
                    msj = Toast.makeText(this.getContext(), "Incorrecto, intentalo otra vez", Toast.LENGTH_SHORT);
                }
                msj.show();
                break;
            case R.id.btn3:
                if (b3.getText() == cElegida.name) {
                    msj = Toast.makeText(this.getContext(), "Correcto!", Toast.LENGTH_SHORT);
                } else {
                    msj = Toast.makeText(this.getContext(), "Incorrecto, intentalo otra vez", Toast.LENGTH_SHORT);
                }
                msj.show();
                break;
            case R.id.btn4:
                if (b4.getText() == cElegida.name) {
                    msj = Toast.makeText(this.getContext(), "Correcto!", Toast.LENGTH_SHORT);
                } else {
                    msj = Toast.makeText(this.getContext(), "Incorrecto, intentalo otra vez", Toast.LENGTH_SHORT);
                }
                msj.show();
                break;
        }
    }
}