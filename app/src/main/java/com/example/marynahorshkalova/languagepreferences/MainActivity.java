package com.example.marynahorshkalova.languagepreferences;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String language = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView languageChosen = findViewById(R.id.languageChosen);

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_map)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton(language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        languageChosen.setText(language);
                    }
                })
                .setNegativeButton("Ukrainian", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        language = "Ukrainian";
                        languageChosen.setText(language);

                    }
                }).show();

    }
}
