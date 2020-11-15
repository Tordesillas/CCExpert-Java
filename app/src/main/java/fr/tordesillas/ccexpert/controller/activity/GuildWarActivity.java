package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.GuildWarProcessor;

public class GuildWarActivity extends BaseActivity {
    private EditText playerPower;
    private EditText playerScore;
    private TextView resultLabel;
    private GuildWarProcessor gwp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guildwar);

        gwp = new GuildWarProcessor(getResources());

        ((TextView) findViewById(R.id.guildWarTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        playerPower = findViewById(R.id.playerPower);
        playerScore = findViewById(R.id.playerScore);
        resultLabel = findViewById(R.id.resultLabel);

        TextWatcher listener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onFormChange();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        playerScore.addTextChangedListener(listener);
        playerPower.addTextChangedListener(listener);
    }

    private void onFormChange() {
        if (playerPower.getText().length() >= 4 && playerScore.getText().length() >= 3) {
            resultLabel.setText(
                    gwp.printStats(
                            Integer.parseInt(playerPower.getText().toString()),
                            Integer.parseInt(playerScore.getText().toString())
                    )
            );
        } else {
            resultLabel.setText("");
        }
    }
}
