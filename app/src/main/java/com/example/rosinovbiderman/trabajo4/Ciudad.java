package com.example.rosinovbiderman.trabajo4;

/**
 * Created by 41824396 on 9/8/2016.
 */
public class Ciudad {
    String clase;
    String countrycode;
    double lat;
    double lng;
    String name;
    int population;

    public Ciudad(String clase, String countrycode, double lat, double lng, String name, int population) {
        this.clase = clase;
        this.countrycode = countrycode;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.population = population;
    }
}
