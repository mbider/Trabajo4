package com.example.rosinovbiderman.trabajo4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    FragmentMapa fragmentJuego;
    FragmentResult fragmentResult;
    public int correctas;
    public int cantpreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentJuego = new FragmentMapa();
        fragmentResult = new FragmentResult();
        

    }

    public void MostrarJuego(View v){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenedorPrincipal, fragmentJuego)
                .commit();
    }

    public void MostrarResult(View v){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenedorPrincipal, fragmentResult)
                .commit();
        Log.d("lol", String.valueOf(correctas));
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correct){
        correctas = correct;
    }

    public int getCantpreg() {
        return cantpreg;
    }

    public void setCantpreg(int preguntas){
        cantpreg = preguntas;
    }
}
