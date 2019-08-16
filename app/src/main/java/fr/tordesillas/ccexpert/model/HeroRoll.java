package fr.tordesillas.ccexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class HeroRoll implements Parcelable {
    private String frName;
    private String enName;
    private String deName;
    private String ruName;
    private String esName;
    private int proba;
    private int type;
    private int occurrences;

    public HeroRoll(String frName, String enName, String deName, String ruName, String esName, int proba, int type) {
        this.frName = frName;
        this.enName = enName;
        this.deName = deName;
        this.ruName = ruName;
        this.esName = esName;
        this.proba = proba;
        this.type = type;
        occurrences = 0;
    }

    public String getName() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return frName;
            case "deu":
                return deName;
            case "rus":
                return ruName;
            case "spa":
                return esName;
            default:
                return enName;
        }
    }

    public int getProba() {
        return proba;
    }

    public int getType() {
        return type;
    }

    public String getPicture() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }

    public int getOccurrencesAndInc() {
        occurrences++;
        return occurrences;
    }

    public void zeroOccurences() {
        occurrences = 0;
    }

    private HeroRoll(Parcel in) {
        frName = in.readString();
        enName = in.readString();
        deName = in.readString();
        proba = in.readInt();
        type = in.readInt();
        occurrences = in.readInt();
    }

    public static final Creator<HeroRoll> CREATOR = new Creator<HeroRoll>() {
        @Override
        public HeroRoll createFromParcel(Parcel in) {
            return new HeroRoll(in);
        }

        @Override
        public HeroRoll[] newArray(int size) {
            return new HeroRoll[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(frName);
        parcel.writeString(enName);
        parcel.writeString(deName);
        parcel.writeInt(proba);
        parcel.writeInt(type);
        parcel.writeInt(occurrences);
    }
}
