package com.example.marynahorshkalova.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // SET UP MENU
    // create File --> New --> Android resourse file --> type (Menu)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // link options menu with main_menu.xml

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {

            setLanguage("English");

        } else if (item.getItemId() == R.id.ukrainian) {

            setLanguage("Ukrainian");
        }

        return true;
    }

    SharedPreferences sharedPreferences;

    TextView languageTextView;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languageTextView = findViewById(R.id.languageTextView);

        language = languageTextView.getText().toString();

        sharedPreferences = this.getSharedPreferences("com.example.marynahorshkalova.languagepreferences", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("language", "");

        // BUILD ALERT DIALOG

        if (language == "") {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_map)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Ukrainian", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            setLanguage("Ukrainian");
                        }
                    }).show();

        } else {

            languageTextView.setText(language);
        }
    }

    public void setLanguage(String language) {

        sharedPreferences.edit().putString("language", language).apply();

        languageTextView.setText(language);

    }
}
