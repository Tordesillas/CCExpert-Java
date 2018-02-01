package fr.unice.polytech.ccexpert.controller.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.StatsGuildWar;

public class GuildWarActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guildwar);

        final StatsGuildWar sgw = new StatsGuildWar();

        ((TextView) findViewById(R.id.guildWarTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        final EditText playerPower = findViewById(R.id.playerPower);
        final EditText playerScore = findViewById(R.id.playerScore);
        final RadioGroup radioGroupPosition = findViewById(R.id.radioGroupPosition);
        final EditText score = findViewById(R.id.score);

        (findViewById(R.id.firstButtonGuild)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playerPower.getText().length() <= 1 || playerScore.getText().length() <= 1) {
                    Toast.makeText(GuildWarActivity.this, "Des données sont manquantes.", Toast.LENGTH_SHORT).show();
                    return;
                }
                createDialog(sgw.printStats(GuildWarActivity.this,
                        Integer.parseInt(playerPower.getText().toString()),
                        Integer.parseInt(playerScore.getText().toString())));
                playerPower.setText("");
                playerScore.setText("");
            }
        });

        (findViewById(R.id.secondButtonGuild)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score.getText().toString().length() <= 1) {
                    Toast.makeText(GuildWarActivity.this, "Des données sont manquantes.", Toast.LENGTH_SHORT).show();
                    return;
                }
                createDialog(sgw.printFameStats(Integer.parseInt(score.getText().toString()),
                        findPosition(radioGroupPosition.getCheckedRadioButtonId())));
                score.setText("");
            }
        });
    }

    private void createDialog(String message) {
        if (message == null) {
            return;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Simulation terminée")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .show();
    }

    private int findPosition(int id) {
        switch (id) {
            case R.id.radio1:
                return 0;
            case R.id.radio2:
                return 1;
            case R.id.radio3:
                return 2;
            case R.id.radio4:
                return 3;
            default:
                return 4;
        }
    }
}