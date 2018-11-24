package fr.tordesillas.ccexpert.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HeroRoll implements Parcelable {
    private String nameFr;
    private String enName;
    private String deName;
    private int proba;
    private int type;
    private int occurrences;

    public HeroRoll(String nameFr, String enName, String deName, int proba, int type) {
        this.nameFr = nameFr;
        this.enName = enName;
        this.deName = deName;
        this.proba = proba;
        this.type = type;
        occurrences = 0;
    }

    public String getNameFr() {
        return nameFr;
    }

    public String getEnName() {
        return enName;
    }

    public String getDeName() {
        return deName;
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
        nameFr = in.readString();
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
        parcel.writeString(nameFr);
        parcel.writeString(enName);
        parcel.writeString(deName);
        parcel.writeInt(proba);
        parcel.writeInt(type);
        parcel.writeInt(occurrences);
    }
}
