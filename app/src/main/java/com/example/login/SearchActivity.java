package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private DBManager dbManager;
    private EditText voitureIdEditText;
    private ListView voitureListListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> voitureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        dbManager = new DBManager(this);

        voitureIdEditText = findViewById(R.id.textView4);
        voitureListListView = findViewById(R.id.listView);

        voitureList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, voitureList);
        voitureListListView.setAdapter(listAdapter);

        dbManager.open();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
      /*  Button listButton = findViewById(R.id.button);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listVoiture();
            }
        });*/
        Button searchButton = findViewById(R.id.button); // Utilisez le bon ID du bouton
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        voitureListListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchActivity.this, "Item clicked: " + voitureList.get(position), Toast.LENGTH_SHORT).show();
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

    private void search() {
        voitureList.clear();
        String voitureIdStr = voitureIdEditText.getText().toString().trim();

        if (!voitureIdStr.isEmpty()) {
            int voitureId = Integer.parseInt(voitureIdStr);
            voitureList.addAll(dbManager.search(voitureId));
            listAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action1) {
            // Gérer l'action "Parametre"
            showToast("Option Parametre sélectionnée");
            return true;
        } else if (itemId == R.id.action2) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();  // Facultatif : fermez l'activité actuelle si nécessaire

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }}

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
