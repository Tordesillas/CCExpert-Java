package fr.unice.polytech.ccexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Dungeon implements Parcelable {
    private String urlYoutube;
    private int door;
    private int base;
    private int[] heroesIds;
    private boolean f2p;
    private boolean allFlames;

    public Dungeon(String urlYoutube, int door, int base, int compo1, int compo2, int compo3, int compo4, int compo5, int compo6, int f2p, int allFlames) {
        this.urlYoutube = urlYoutube;
        this.door = door;
        this.base = base;
        heroesIds = new int[]{compo1, compo2, compo3, compo4, compo5, compo6};
        this.f2p = f2p == 0;
        this.allFlames = allFlames == 1;
    }

    public String getUrlYoutube() {
        return urlYoutube;
    }

    public int getDoor() {
        return door;
    }

    public int getBase() {
        return base;
    }

    public int[] getHeroesIds() {
        return heroesIds;
    }

    public boolean isF2p() {
        return f2p;
    }

    public boolean isAllFlames() {
        return allFlames;
    }

    private Dungeon(Parcel in) {
        this.urlYoutube = in.readString();
        this.door = in.readInt();
        this.base = in.readInt();
        List<Integer> tmp = new ArrayList<>();
        in.readList(tmp, getClass().getClassLoader());
        this.heroesIds = new int[]{in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt()};
        this.f2p = in.readByte() == 1;
        this.allFlames = in.readByte() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlYoutube);
        dest.writeInt(door);
        dest.writeInt(base);
        for (int id : heroesIds) {
            dest.writeInt(id);
        }
        dest.writeByte((byte) (f2p ? 1 : 0));
        dest.writeByte((byte) (allFlames ? 1 : 0));
    }

    public static final Parcelable.Creator<Dungeon> CREATOR = new Parcelable.Creator<Dungeon>() {
        @Override
        public Dungeon createFromParcel(Parcel source) {
            return new Dungeon(source);
        }

        @Override
        public Dungeon[] newArray(int size) {
            return new Dungeon[size];
        }
    };
}
