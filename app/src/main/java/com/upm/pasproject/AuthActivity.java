package com.upm.pasproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAnalytics analytics;
    private FirebaseAuth mAuth;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Analytics Event
        analytics = FirebaseAnalytics.getInstance((this));
        bundle = new Bundle();
        bundle.putString("message","Integracion de Firebase completa");
        analytics.logEvent("InitScreen", bundle);

        // Initialize callbacks
        initFirebaseCallbacks();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Reset password and email fields after restart (Comming back from Drawer activity)

        EditText emailText = (EditText) findViewById(R.id.emailEditText);
        EditText passwordText = (EditText) findViewById(R.id.passwordEditText);

        emailText.setText("");
        passwordText.setText("");
    }

    private void initFirebaseCallbacks(){

        setTitle("Authentication");

        mAuth = FirebaseAuth.getInstance();

        // Button declaration

        Button logInButton = (Button)findViewById(R.id.logInButton);
        Button registerButton = (Button)findViewById(R.id.registerButton);

        logInButton.setOnClickListener(logInListener);
        registerButton.setOnClickListener(registerListener);

    }

    private View.OnClickListener logInListener = new View.OnClickListener() {
        public void onClick(View v){

            // get email and password as string from View
            String emailString = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
            String passwordString = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

            if( !emailString.isEmpty() && !passwordString.isEmpty())
            {
                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(signUpCompletion);
            }
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        public void onClick(View v){

            // get email and password as string from View
            String emailString = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
            String passwordString = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

            if( !emailString.isEmpty() && !passwordString.isEmpty())
            {
                mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(signUpCompletion);
            }
        }
    };

    private OnCompleteListener signUpCompletion = new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull @NotNull Task task) {
            if(task.isSuccessful()){
                showHome(mAuth.getCurrentUser().getEmail(), ProviderType.BASIC);
            } else{
                showAlert();
            }
        }
    } ;

    private void showAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando al usuario");
        builder.setPositiveButton("Aceptar",null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void showHome(String email, ProviderType provider){

        Log.d("SHOWNAME", "showHome: email: "+email+ " provider: "+provider);

        Intent homeIntent = new Intent(this, RestApi_Drawer.class)
                .putExtra("email",email)
                .putExtra("provider",provider.toString());

        // Start Activity HOME
        startActivity(homeIntent);

        // Finish current activity to not allow the user to go back to log in again without logging out
        finish();
    }
}