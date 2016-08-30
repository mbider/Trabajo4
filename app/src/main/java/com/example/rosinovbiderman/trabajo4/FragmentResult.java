package com.example.rosinovbiderman.trabajo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentResult extends Fragment {

    TextView respcorrectas;
    TextView cantpreguntas;
    int correctas;
    int cantpreg;
    MainActivity ma;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_result, container, false);

        respcorrectas = (TextView) v.findViewById(R.id.correctas);
        cantpreguntas = (TextView) v.findViewById(R.id.totalpreguntas);

        ma = new MainActivity();

        correctas = ma.getCorrectas();
        cantpreg = ma.getCantpreg();

        respcorrectas.setText("Respuestas correctas: " + String.valueOf(correctas));
        cantpreguntas.setText("Cantidad de preguntas respondidas: " + String.valueOf(cantpreg));
        return v;
    }
}
