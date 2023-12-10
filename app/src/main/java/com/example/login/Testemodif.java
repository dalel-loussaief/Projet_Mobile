package com.example.login;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Testemodif extends AppCompatActivity {
    private DBManager dbManager;
    private EditText voitureIdEditText,  voitureMatriculeEditText, voitureMarqueEditText,voituremodeleEditText,voitureAnneeEditText,voitureDispoEditText;
    private ListView voitureListListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> voitureList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif);
        dbManager = new DBManager(this);
        voitureIdEditText = findViewById(R.id.textView4);
        voitureMarqueEditText = findViewById(R.id.textView);
        voituremodeleEditText = findViewById(R.id.textView10);
        voitureAnneeEditText = findViewById(R.id.textView12);
        voitureDispoEditText = findViewById(R.id.textView14);
        voitureMatriculeEditText=findViewById(R.id.textView2);
        voitureListListView = findViewById(R.id.listView);

        Button deleteButton = findViewById(R.id.button6);
        Button updateButton = findViewById(R.id.button);
        Button listButton = findViewById(R.id.button4);
        ImageButton imageButton = findViewById(R.id.imageButton2);



        voitureList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, voitureList);
        voitureListListView.setAdapter(listAdapter);

        dbManager.open();


     deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVoiture();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVoiture();
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
                Toast.makeText(Testemodif.this, "Item clicked: " + voitureList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton myButton = findViewById(R.id.imageButton2);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Testemodif", "ImageButton clicked");
                // Lancer l'activité SearchActivity
                Intent intent = new Intent(Testemodif.this, SearchActivity.class);
                startActivity(intent);

                // Appeler la méthode search de SearchActivity
                search();
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


    private void listVoiture() {
        voitureList.clear();
        voitureList.addAll(dbManager.getAllVoitureAsStringList());
        listAdapter.notifyDataSetChanged();
    }
    private void deleteVoiture() {
        String voitureIdStr = voitureIdEditText.getText().toString().trim();

        if (!voitureIdStr.isEmpty()) {
            long voitureId = Long.parseLong(voitureIdStr);
            dbManager.deleteVoiture(voitureId);
            Toast.makeText(this, "voiture supprimer avec ID: " + voitureId, Toast.LENGTH_SHORT).show();

            voitureIdEditText.getText().clear();
            listVoiture();
        } else {
            Toast.makeText(this, "SVP entrer ID de voiture", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateVoiture() {
        String voitureIdStr = voitureIdEditText.getText().toString().trim();
        String voitureMarque = voitureMarqueEditText.getText().toString().trim();
        String voitureModeleStr = voituremodeleEditText.getText().toString().trim();
        String voitureAnneeStr = voitureAnneeEditText.getText().toString().trim();
        String voitureDispoStr = voitureDispoEditText.getText().toString().trim();
        String voitureMatricule= voitureMatriculeEditText.getText().toString().trim();
        if (!voitureMarque.isEmpty() && !voitureModeleStr.isEmpty() && !voitureAnneeStr.isEmpty() && !voitureDispoStr.isEmpty() && !voitureMatricule.isEmpty()) {
            long voitureId = Long.parseLong(voitureIdStr);


            Voiture updatedVoiture = new Voiture((int) voitureId, voitureMatricule,voitureMarque, voitureModeleStr,voitureAnneeStr,voitureDispoStr);
            int rowsAffected = dbManager.updateVoiture(updatedVoiture);

            if (rowsAffected > 0) {
                Toast.makeText(this, "car updated with ID: " + voitureId, Toast.LENGTH_SHORT).show();

                voitureIdEditText.getText().clear();
                voitureMarqueEditText.getText().clear();
                voituremodeleEditText.getText().clear();
                voitureAnneeEditText.getText().clear();
                voitureDispoEditText.getText().clear();
                voitureMatriculeEditText.getText().clear();
                listVoiture();
            } else {
                Toast.makeText(this, "Failed to update car. Please check the ID.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter ", Toast.LENGTH_SHORT).show();
        }
    }
    private void search() {
        voitureList.clear();
        String voitureIdStr = voitureIdEditText.getText().toString().trim();

        if (!voitureIdStr.isEmpty()) {
            try {
                int voitureId = Integer.parseInt(voitureIdStr);
                voitureList.addAll(dbManager.search(voitureId));
                listAdapter.notifyDataSetChanged();
            } catch (NumberFormatException e) {
                // Gérer la conversion échouée ici
                Toast.makeText(this, "Veuillez entrer un ID valide", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Gérer le cas où la chaîne est vide
            Toast.makeText(this, "Veuillez entrer un ID", Toast.LENGTH_SHORT).show();
        }
    }


}
