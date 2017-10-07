package fr.unice.polytech.ccexpert.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseBrowser {
    //private Database db;
    private Sets sets;

    public DatabaseBrowser(String path) throws IOException {
        sets = new Sets();

        loadDatabase(path);
        loadHeroes();
        loadCompoHeroes();
        loadDungeons();

        //db.close();
    }

    private void loadDatabase(String path) throws IOException {
        /*db = Database.openOrCreate(path);
        if (Storage.getInstance().readObject("initialized") == null) {
            OutputStream o = FileSystemStorage.getInstance().openOutputStream(Database.getDatabasePath(path));
            InputStream i = Display.getInstance().getResourceAsStream(getClass(), "/" + path);
            Util.copy(i, o);
            Util.cleanup(o);
            Util.cleanup(i);

            db.close();
            db = Database.openOrCreate(path);
            Storage.getInstance().writeObject("initalized", "true");
        }*/
    }

    private void loadHeroes() throws IOException {
        /*Cursor c = db.executeQuery("select * from heroes");
        Row l;
        Hero hero;
        while (c.next()) {
            l = c.getRow();
            hero = new Hero(l.getString(1), l.getString(2), main.getTheme().getImage("Anubis_Icon.png"),
                    l.getInteger(4), l.getInteger(5), l.getInteger(6), l.getInteger(7), l.getInteger(8),
                    l.getInteger(9), l.getInteger(10), l.getInteger(11), l.getInteger(12),l.getInteger(13),
                    l.getInteger(14), l.getInteger(15),l.getInteger(16));
            sets.addHero(hero, l.getInteger(0));
        }
        c.close();*/
    }

    private void loadCompoHeroes() throws IOException {
        /*Cursor c = db.executeQuery("select * from Compositionhero");
        Row l;
        HeroFaculties heroFaculties;
        while (c.next()) {
            l = c.getRow();
            heroFaculties = new HeroFaculties(l.getInteger(1), l.getInteger(2), l.getInteger(3), l.getString(4),
                    l.getInteger(5), l.getInteger(6), l.getString(7), l.getInteger(8), l.getInteger(9),
                    l.getInteger(10), l.getInteger(11));
            sets.addHeroCompo(heroFaculties, l.getInteger(0));
        }
        c.close();*/
    }

    private void loadDungeons() throws IOException {
        /*Cursor c = db.executeQuery("select * from Dungeons");
        Row l;
        Dungeon d;
        while (c.next()) {
            l = c.getRow();
            d = new Dungeon(l.getString(1), l.getInteger(2), l.getInteger(3), l.getInteger(4),
                    l.getInteger(5), l.getInteger(6), l.getInteger(7), l.getInteger(8),
                    l.getInteger(9), l.getInteger(10), l.getInteger(11));
            sets.addDungeon(d);
        }
        c.close();*/
    }

    public Sets getSetsFromDatabase() {
        return sets;
    }
}
