package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Acceuilpage extends AppCompatActivity {
    private DBManager dbManager;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilpage);
        btn = findViewById(R.id.button10);
        // Initialize the DBManager and open the database
        dbManager = new DBManager(this);
        dbManager.open();

        dbManager.open();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(Acceuilpage.this,Testeajout.class);
                //secondActivity.putExtra("second",txt1.getText().toString());
                startActivity(secondActivity);


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database connection when the activity is destroyed
        if (dbManager != null) {
            dbManager.close();
        }
    }
}
