package fr.unice.polytech.ccexpert.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.unice.polytech.ccexpert.R;

public class Database extends SQLiteOpenHelper {
    private static String DB_NAME = "ccexpert_database";
    private SQLiteDatabase db;
    private final Context myContext;
    private Sets sets;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.sets = new Sets();
    }

    public void openDataBase() throws SQLException, IOException {
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public Sets execute() {
        loadHeroes();
        loadCompoHeroes();
        loadDungeons();

        return sets;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }

    private boolean checkDataBase() {
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
        return checkDB != null;
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
            hero = new Hero(c.getString(1), c.getString(2), R.drawable.anubis,
                    c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8),
                    c.getInt(9), c.getInt(10), c.getInt(11), c.getInt(12),c.getInt(13),
                    c.getInt(14), c.getInt(15),c.getInt(16));
            sets.addHero(hero, c.getInt(0));
            c.moveToNext();
        }
        c.close();
    }

    private void loadCompoHeroes() {
        Cursor c = db.rawQuery("select * from Compositionhero", null);
        c.moveToFirst();
        HeroFaculties heroFaculties;
        while (!c.isAfterLast()) {
            heroFaculties = new HeroFaculties(c.getInt(1), c.getInt(2), c.getInt(3), c.getString(4),
                    c.getInt(5), c.getInt(6), c.getString(7), c.getInt(8), c.getInt(9),
                    c.getInt(10), c.getInt(11));
            sets.addHeroCompo(heroFaculties, c.getInt(0));
            c.moveToNext();
        }
        c.close();
    }

    private void loadDungeons() {
        Cursor c = db.rawQuery("select * from Dungeons", null);
        c.moveToFirst();
        Dungeon d;
        while (!c.isAfterLast()) {
            d = new Dungeon(c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4),
                    c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8),
                    c.getInt(9), c.getInt(10), c.getInt(11));
            sets.addDungeon(d);
            c.moveToNext();
        }
        c.close();
    }
}
