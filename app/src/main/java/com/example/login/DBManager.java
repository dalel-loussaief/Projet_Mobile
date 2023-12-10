package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }
    public long addVoiture(Voiture voiture) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_MATRICULE, voiture.getMatricule());
        values.put(DBHelper.KEY_MARQUE, voiture.getMarque());
        values.put(DBHelper.KEY_MODELE, voiture.getModele());
        values.put(DBHelper.KEY_DISPO, voiture.getDispo());


        return database.insert(DBHelper.TABLE_VOITURE, null, values);
    }
    public int updateVoiture(Voiture voiture) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_MATRICULE, voiture.getMatricule());
        values.put(DBHelper.KEY_MARQUE, voiture.getMarque());
        values.put(DBHelper.KEY_MODELE, voiture.getModele());
        values.put(DBHelper.KEY_DISPO, voiture.getDispo());

        return database.update(
                DBHelper.TABLE_VOITURE,
                values,
                DBHelper.KEY_ID + " = ?",
                new String[]{String.valueOf(voiture.getId())}
        );
    }
    public void deleteVoiture(long voitureId) {
        database.delete(DBHelper.TABLE_VOITURE, DBHelper.KEY_ID
                + "=" + voitureId, null);
    }
    public List<String> getAllVoitureAsStringList() {
        List<String> voitureList = new ArrayList<>();

        String[] columns = {DBHelper.KEY_ID, DBHelper.KEY_MATRICULE, DBHelper.KEY_MARQUE,DBHelper.KEY_MODELE,DBHelper.KEY_DISPO};
        Cursor cursor = database.query(DBHelper.TABLE_VOITURE, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int voitureId = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.KEY_ID));
                String voitureMatricule = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_MATRICULE));
                String voitureMarque = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_MARQUE));
                String voitureMODELE = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_MODELE));
                String voitureDISPO= cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_DISPO));
                voitureList.add(voitureId + "- " + voitureMatricule + " : " + voitureMarque+ voitureMODELE +":"+ voitureDISPO +":");
            } while (cursor.moveToNext());
        }

        cursor.close();
        return voitureList;
    }
    public List<String> search(int id) {
        List<String> voitureList = new ArrayList<>();

        String[] columns = {DBHelper.KEY_ID, DBHelper.KEY_MATRICULE, DBHelper.KEY_MARQUE,DBHelper.KEY_DISPO};
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_VOITURE + " WHERE " + DBHelper.KEY_ID + " = " + id, null);

        if (cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.KEY_ID));
                String Name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_MATRICULE));
                String Marque = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_MARQUE));
                String dispo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.KEY_DISPO));
                voitureList.add(productId + "- " + Name + " : " + Marque +" :" +dispo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return voitureList;
    }
    // Inside your DBManager class or wherever you perform database operations
    public long insertUser(String nom, String prenom, String password) {
        long result = -1;

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DBHelper.KEY_Nom, nom);
            contentValues.put(DBHelper.KEY_Prenom, prenom);
            contentValues.put(DBHelper.KEY_Password, password);

            result = db.insertOrThrow(DBHelper.TABLE_USER, null, contentValues);
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        }

        return result;
    }

    public boolean isUserExists(String nom, String prenom) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {DBHelper.KEY_ID};

        // Specify the WHERE clause with the user's name
        String selection = DBHelper.KEY_Nom + " = ? AND " + DBHelper.KEY_Prenom + " = ?";
        String[] selectionArgs = {nom, prenom};

        // Query the database
        Cursor cursor = db.query(
                DBHelper.TABLE_USER, // Table to query
                columns,            // Columns to return
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // don't sort order
        );

        boolean userExists = cursor.moveToFirst();
        cursor.close();
        return userExists;
    }
}


