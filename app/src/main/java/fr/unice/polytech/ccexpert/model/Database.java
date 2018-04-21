package fr.unice.polytech.ccexpert.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Database extends SQLiteOpenHelper {
    private static String DB_NAME = "ccexpert_database";
    private SQLiteDatabase db;
    private final Context myContext;
    private Sets sets;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.sets = Sets.getInstance();
    }

    public void openDataBase() {
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void execute() {
        loadHeroes();
        loadDungeons();
        loadTalents();
        loadArtifacts();
        loadPets();
    }

    public void createDataBase() {
        checkDataBase();
        this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database", e);
        }
    }

    private void checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void loadHeroes() {
        Cursor c = db.rawQuery("select * from heroes", null);
        c.moveToFirst();
        Hero hero;
        while (!c.isAfterLast()) {
            hero = new Hero(c.getString(1), c.getString(2));
            sets.addHero(hero, c.getInt(0));
            c.moveToNext();
        }
        c.close();
    }

    private void loadDungeons() {
        Cursor c = db.rawQuery("select * from Dungeons", null);
        c.moveToFirst();
        Dungeon d;
        while (!c.isAfterLast()) {
            d = new Dungeon(c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4));
            sets.addDungeon(d);
            c.moveToNext();
        }
        c.close();
    }

    private void loadTalents() {
        Cursor c = db.rawQuery("select * from talents", null);
        c.moveToFirst();
        Talent t;
        while (!c.isAfterLast()) {
            t = new Talent(c.getString(0), c.getString(1));
            sets.addTalent(t);
            c.moveToNext();
        }
        c.close();
    }

    private void loadArtifacts() {
        Cursor c = db.rawQuery("select * from artifacts", null);
        c.moveToFirst();
        Artifact a;
        while (!c.isAfterLast()) {
            a = new Artifact(c.getString(0), c.getString(1));
            sets.addArtifact(a);
            c.moveToNext();
        }
        c.close();
    }

    private void loadPets() {
        Cursor c = db.rawQuery("select * from pets", null);
        c.moveToFirst();
        Pet p;
        while (!c.isAfterLast()) {
            p = new Pet(c.getString(0), c.getString(1));
            sets.addPet(p);
            c.moveToNext();
        }
        c.close();
    }
}
