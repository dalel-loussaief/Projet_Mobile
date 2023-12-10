package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Testeajout extends AppCompatActivity{

    private DBManager dbManager;
    private EditText voitureIdEditText,  voitureMatriculeEditText, voitureMarqueEditText,voituremodeleEditText,voitureAnneeEditText,voitureDispoEditText;
    private ListView voitureListListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> voitureList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testeajout);
        dbManager = new DBManager(this);
        voitureIdEditText = findViewById(R.id.textView4);
        voitureMarqueEditText = findViewById(R.id.textView);
        voituremodeleEditText = findViewById(R.id.textView10);
        voitureAnneeEditText = findViewById(R.id.textView12);
        voitureDispoEditText = findViewById(R.id.textView14);
        voitureMatriculeEditText=findViewById(R.id.textView2);
        voitureListListView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.button);
        Button listButton = findViewById(R.id.button4);


        voitureList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, voitureList);
        voitureListListView.setAdapter(listAdapter);

        dbManager.open();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVoiture();
            }
        });


        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listVoiture();
            }
        });

        voitureListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Testeajout.this, "Item clicked: " + voitureList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        Button myButton = findViewById(R.id.button6);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(Testeajout.this, Testemodif.class);
                startActivity(secondActivity);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbManager != null) {
            dbManager.close();
        }
    }

    private void addVoiture() {
        String voitureMarque = voitureMarqueEditText.getText().toString().trim();
        String voitureMatricule = voitureMatriculeEditText.getText().toString().trim();
        String voitureModele = voituremodeleEditText.getText().toString().trim();
        String voitureAnnee = voitureAnneeEditText.getText().toString().trim();
        String voitureDispo = voitureDispoEditText.getText().toString().trim();
        if (!voitureMarque.isEmpty() && !voitureModele.isEmpty() && !voitureAnnee.isEmpty() && !voitureDispo.isEmpty() && !voitureMatricule.isEmpty()) {


            Voiture newVoiture = new Voiture(null, voitureMatricule,voitureModele,voitureMarque ,voitureAnnee,voitureDispo);
            long voitureId = dbManager.addVoiture(newVoiture);

            Toast.makeText(this, "car added with ID: " + voitureId, Toast.LENGTH_SHORT).show();
            voitureMarqueEditText.getText().clear();
            voitureMatriculeEditText.getText().clear();
            voituremodeleEditText.getText().clear();
            voitureAnneeEditText.getText().clear();
            voitureDispoEditText.getText().clear();
            listVoiture();
        } else {
            Toast.makeText(this, "Please enter os donnees", Toast.LENGTH_SHORT).show();
        }
    }
    private void listVoiture() {
        voitureList.clear();
        voitureList.addAll(dbManager.getAllVoitureAsStringList());
        listAdapter.notifyDataSetChanged();
    }



}

