package fr.tordesillas.ccexpert.controller.processor;

import android.content.res.Resources;

import java.text.NumberFormat;
import java.util.Locale;

import fr.tordesillas.ccexpert.R;

public class AccuracyProcessor {
    private Resources res;
    private final static int[] PERCENTAGES = {12, 32, 46, 82};

    public AccuracyProcessor(Resources res) {
        this.res = res;
    }

    public String printAccuracyAmount(int basic, int extra) {
        double amount = (basic+extra)/10000;

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        StringBuilder sb = new StringBuilder();
        sb.append(res.getString(R.string.processorText1))
                .append(f.format(100 + amount*100))
                .append(res.getString(R.string.processorText2))
                .append(f.format(Math.round(amount*100)))
                .append(res.getString(R.string.processorText3))
                .append("\n\n");

        for (int nb : PERCENTAGES) {
            double res = Math.round(100 + amount*100 - nb);
            if (res >= 100) {
                sb.append(this.res.getString(R.string.processorText4))
                        .append(nb)
                        .append(this.res.getString(R.string.processorText5))
                        .append(res)
                        .append("%.\n");
            } else {
                sb.append(this.res.getString(R.string.processorText4))
                        .append(nb)
                        .append(this.res.getString(R.string.processorText6))
                        .append(res)
                        .append(this.res.getString(R.string.processorText7))
                        .append("\n");
            }
        }
        return sb.toString();
    }
}
