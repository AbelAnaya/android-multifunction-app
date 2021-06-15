package com.upm.pasproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.upm.pasproject.databinding.ActivityRestApiDrawerBinding;

public class RestApi_Drawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityRestApiDrawerBinding binding;
    private String userEmailIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userEmailIntent = getIntent().getStringExtra("email");

        binding = ActivityRestApiDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarRestApiDrawer.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_crypto, R.id.nav_sensor, R.id.nav_map)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_rest_api_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()){
            case R.id.action_logout:
                logOutAlert();
                return true;
            case R.id.action_help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rest_api__drawer, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {

        // Set text for user email in navigator
        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText(userEmailIntent);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_rest_api_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void logOutAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("OK",logOutListener);
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    void exitAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to exit the application?");
        builder.setPositiveButton("Yes",logOutListener);
        builder.setNegativeButton("No",null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    DialogInterface.OnClickListener logOutListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            FirebaseAuth.getInstance().signOut();
            startActivity(createIntentToAuth());
            finish();
        }
    };

    Intent createIntentToAuth(){
        return new Intent(this, AuthActivity.class);
    }

    void showHelp (){

        // TODO: Explicar que hace cada opcion del navigator en este alert

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Help");
        builder.setMessage("This is a placeholder string for help button feature");
        builder.setPositiveButton("OK",null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //TODO: Detect when the user is going to exit the application
    }
}