package com.example.amplify_java;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.amazonaws.services.securitytoken.model.Tag;
import com.amplifyframework.core.Amplify;

public class Confirm extends AppCompatActivity {
    EditText confirmationCode;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .4));


        Intent intent = getIntent();
        String username = intent.getStringExtra(Register.EXTRA_TEXT);
        confirmationCode = findViewById(R.id.confirmationNumber);
        click = findViewById(R.id.confirmButton);

        TextView textView = findViewById(R.id.usernameConfirm);
        textView.setText(username);


        System.out.println("Confirmation page opened successfully");
        System.out.println("Username successfully received: " + username);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmUser(username, confirmationCode.getText().toString());
            }
        });

    }

    public void confirmUser(String username, String confirmationCode) {

        Amplify.Auth.confirmSignUp(
                username,
                confirmationCode,
                result -> {
                    Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    System.out.println("User confirmation complete.");
                    Intent intent2 = new Intent(this, LoginUI.class);
                    startActivity(intent2);
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }



    /*EditText confirmationCode;
    EditText username;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        confirmationCode = findViewById(R.id.confirmationNumber);
        username = findViewById(R.id.username);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.confirmSignUp(
                        username.getText().toString(),
                        Integer.parseInt(confirmationCode.getText().toString()),
                        result -> {
                            Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                            startActivity(new Intent(Confirm.this, MainActivity.class));
                        },
                        //Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

    }*/
}
