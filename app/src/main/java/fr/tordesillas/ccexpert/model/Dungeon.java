package fr.tordesillas.ccexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Dungeon implements Parcelable {
    private String urlYoutube;
    private int door;
    private int base;
    private boolean f2p;

    public Dungeon(String urlYoutube, int door, int base, int f2p) {
        this.urlYoutube = urlYoutube;
        this.door = door;
        this.base = base;
        this.f2p = f2p == 0;
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

    public boolean isF2p() {
        return f2p;
    }

    private Dungeon(Parcel in) {
        this.urlYoutube = in.readString();
        this.door = in.readInt();
        this.base = in.readInt();
        List<Integer> tmp = new ArrayList<>();
        in.readList(tmp, getClass().getClassLoader());
        this.f2p = in.readByte() == 1;
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
        dest.writeByte((byte) (f2p ? 1 : 0));
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
