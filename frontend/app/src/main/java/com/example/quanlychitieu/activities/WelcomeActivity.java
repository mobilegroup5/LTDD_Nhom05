package com.example.quanlychitieu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.SpinnerLanguageAdapter;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    private long backPressedTime;
    List<String> languages = new ArrayList<>();
    Spinner spinnerLanguage;
    Button btnSwitchToSignUp, btnSwitchToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        languages.add(getString(R.string.language_vn));
        languages.add(getString(R.string.language_en));

        initializeElement();
        handleSwitchToRegisterActivity();
        handleSwitchToLoginActivity();

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(WelcomeActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SpinnerLanguageAdapter adapter = new SpinnerLanguageAdapter(WelcomeActivity.this, languages);
        spinnerLanguage.setAdapter(adapter);
    }

    private void handleSwitchToLoginActivity() {
        btnSwitchToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleSwitchToRegisterActivity() {
        btnSwitchToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeElement() {
        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        btnSwitchToSignUp = findViewById(R.id.btnSwitchToSignUp);
        btnSwitchToSignIn = findViewById(R.id.btnSwitchToSignIn);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}