package es.iesnervion.atellez.sqlite_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by atellez on 15/02/17.
 */

public class AnotacionSQLiteHelper extends SQLiteOpenHelper {

    //TODO CREAR BIEN LA BASE DE DATOS

    //Segun algunas opiniones no es  eficiente hacer el autoincremento en la BD
    String sqlCreate = "CREATE TABLE Anotaciones(codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "cantidad FLOAT NOT NULL, " +
            "nota TEXT NOT NULL, " +
            "tipo TEXT NOT NULL, " +
            "fechaCreacion TEXT NOT NULL)";
    //Formato fecha DATETIME '2007-01-01 10:00:00'
    public AnotacionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Anotaciones");

        //Creacion de la nueva version de la BD
        db.execSQL(sqlCreate);
    }
}
