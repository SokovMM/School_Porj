package com.example.school_porj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class fourth4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth4);

        Bundle arguments = getIntent().getExtras();
        String nameClass = arguments.get("GetNameClass").toString();
        Integer id_class = (Integer) arguments.get("GetIdClass");

        String nameStudent = arguments.get("GetNameStudent").toString();
        Integer idStudent = (Integer) arguments.get("GetIdStudent");

        String namePrichin = arguments.get("GetNamePrichin").toString();
        Integer idPrichin = (Integer) arguments.get("GetIdPrichin");


        TextView getclas = findViewById(R.id.getClassfromAct4);
        TextView getName = findViewById(R.id.getNameFrom4);
        TextView getPrichin = findViewById(R.id.GetReason4);


        getclas.setText(nameClass);
        getName.setText(nameStudent);
        getPrichin.setText(namePrichin);


    }

}