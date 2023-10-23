package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int rid;
    ListView listaRecetas;
    ArrayList<String> listaRecetitas;
    CRUDRecetas CRUD;
    ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBorrar= findViewById(R.id.btnObtener);
        Button btnAdd= findViewById(R.id.btnAdd);
        Button btnEdit= findViewById(R.id.btnEdit);
        EditText tid=findViewById(R.id.recetaID);
        EditText title=findViewById(R.id.titulo);
        EditText description=findViewById(R.id.description);

        CRUD = new CRUDRecetas(this);
        listaRecetitas = new ArrayList<String>();
        CRUD.insertarReceta("Chilaquiles", "rica comida casera");
        CRUD.insertarReceta("Chilaquiles２", "２rica comida casera");

        listaRecetas=findViewById(R.id.listarecetas);
        renewlists();
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rid=Integer.parseInt(tid.getText().toString());
                CRUD.eliminarReceta(rid);
                renewlists();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = title.getText().toString();
                String d= description.getText().toString();
                CRUD.insertarReceta(t, d);
                renewlists();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rid=Integer.parseInt(tid.getText().toString());
                String t = title.getText().toString();
                String d= description.getText().toString();
                CRUD.actualizarReceta(rid,t,d);
                renewlists();
            }
        });


    }
    public void renewlists(){
        listaRecetitas = new ArrayList<String>();
        Cursor informacion=CRUD.mostrarRecetas();
        while (informacion.moveToNext()){
            String titulo=informacion.getString(1);
            listaRecetitas.add(titulo);
        }
        adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRecetitas);
        listaRecetas.setAdapter(adaptador);
    }
}