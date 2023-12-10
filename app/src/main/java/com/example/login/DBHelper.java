package com.example.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "compte";
    public static final int DATABASE_VERSION = 2; // Increment the version number
    public static final String TABLE_USER = "user";
    public static final String KEY_Id = "id";
    public static final String KEY_Nom = "Nom";
    public static final String KEY_Prenom = "prenom";
    public static final String KEY_Password = "Password";
    public static final String TABLE_VOITURE = "voiture";
    public static final String KEY_ID = "id";
    public static final String KEY_MATRICULE = "matricule";
    public static final String KEY_MARQUE = "marque";
    public static final String KEY_MODELE = "modele";
    public static final String KEY_DISPO = "dispo";
    // Constructeur
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_VOITURE = "CREATE TABLE " + TABLE_VOITURE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_MATRICULE + " TEXT,"
                + KEY_MARQUE + " TEXT,"
                + KEY_MODELE + " TEXT,"
                + KEY_DISPO + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_VOITURE);
        // Création de la table
        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
                + KEY_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_Nom + " TEXT,"
                + KEY_Prenom + " TEXT,"
                + KEY_Password + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOITURE);
       // onCreate(db);
        // Supprimer la table existante et la recréer lors de la mise à niveau de la base de données
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}