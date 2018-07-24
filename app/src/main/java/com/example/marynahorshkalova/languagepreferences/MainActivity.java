package com.example.marynahorshkalova.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> languages;

    ArrayList<String> deserializedLanguages;

    SharedPreferences sharedPreferences;

    TextView languageChosen;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageChosen = findViewById(R.id.languageChosen);

        language = languageChosen.getText().toString();

        // SAVE AN ARRAYLIST USING ObjectSerializer

        sharedPreferences = this.getSharedPreferences("com.example.marynahorshkalova.languagepreferences", Context.MODE_PRIVATE);

        languages = new ArrayList<String>();

        deserializedLanguages = new ArrayList<String>();

        languages.add("English");
        languages.add("Ukrainian");

        try {

            sharedPreferences.edit().putString("languages", ObjectSerializer.serialize(languages)).apply();

        } catch (IOException e) {

            e.printStackTrace();
        }

        // BUILD ALERT DIALOG

        if (language == "") {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_map)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            deserializeLanguage();

                            languageChosen.setText(deserializedLanguages.get(0));
                        }
                    })
                    .setNegativeButton("Ukrainian", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            deserializeLanguage();

                            languageChosen.setText(deserializedLanguages.get(1));
                        }
                    }).show();

        } else {

            Toast.makeText(this, "TextView is not empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deserializeLanguage() {

        try {

            deserializedLanguages = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("languages", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
