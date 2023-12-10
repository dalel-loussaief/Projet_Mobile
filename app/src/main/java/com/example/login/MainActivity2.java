package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connecter);
        dbManager = new DBManager(this);
        dbManager.open();

        Button connecterButton = findViewById(R.id.button3);
        connecterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Acceuilpage activity
                Intent intent = new Intent(MainActivity2.this, Acceuilpage.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the DBManager instance
        if (dbManager != null) {
            dbManager.close();
        }
    }
}
