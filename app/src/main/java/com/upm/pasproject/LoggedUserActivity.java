package com.upm.pasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

enum ProviderType{
    BASIC
}

public class LoggedUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_user);
        Intent intent = getIntent();

        Log.d("LUA", "onCreate: provider for user: " + intent.getStringExtra("provider"));

        // Setup
        setup(intent.getStringExtra("email"), intent.getStringExtra("provider"));
    }

    private void setup(String email, String provider) {

        setTitle("Logged Success");

        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView providerTextView = findViewById(R.id.providerTextView);

        emailTextView.setText(email);
        providerTextView.setText(provider);

        Button logOut = (Button) findViewById(R.id.logOutButton);

        logOut.setOnClickListener(logOutlistenerAnonymous);

    }


    // Anonymous classes syntax declaration for View.OnClickListener
    View.OnClickListener logOutlistenerAnonymous = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            onBackPressed();
        }
    };

    // Declaration of class logOutlistener that implements the interface View.OnClicklistener
    public class logOutlistener implements View.OnClickListener {

        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            onBackPressed();
        }
    }
}