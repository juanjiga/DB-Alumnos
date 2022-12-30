package com.juanjiga.db_alumnos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper_db extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "database.db";

    public static final String TABLE_NAME = "nombre_tabla";


    String sqlCreate = "CREATE TABLE " + TABLE_NAME +" (codigo INTEGER, nombre TEXT)";
    String sqlCreate2 = "CREATE TABLE " + TABLE_NAME + " (codigo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT)";

    public Helper_db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Si no existe la base de datos la crea y ejecuta los siguientes comandos
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate2);
    }

}
