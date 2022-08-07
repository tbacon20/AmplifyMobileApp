package com.example.amplify_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
/*
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;
*/
import java.util.Objects;

public class Register extends AppCompatActivity {

    // Text Fields
    EditText email;
    EditText username;
    EditText password;
    Button click;
    public static final String EXTRA_TEXT = "com.example.amplify_java.example.EXTRA_TEXT";

    // Error dialog
    /*AlertDialog.Builder builderDialog;*/
    AlertDialog errorPopup;
    String errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        email = findViewById(R.id.EmailAddress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        click = findViewById(R.id.button2);

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        errorText = "Unknown Error";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.error_popup, null);
        Button errorOK = view.findViewById(R.id.errorOK_btn);
        TextView errorToText = findViewById(R.id.loginError);

        errorOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss Dialog
                errorToText.setText(errorText);
                errorPopup.dismiss();
            }
        });
        builder.setView(view);
        errorPopup = builder.create();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO call SendSessionData();

                registerUser(username.getText().toString(), email.getText().toString(), password.getText().toString());
                /*
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                        .build();
                try {
                    Amplify.Auth.signUp(username.getText().toString(), password.getText().toString(), options,
                            result -> {
                                Log.i("AuthQuickStart", "Result: " + result);
                                Intent intent = new Intent(Register.this, Confirm.class);
                                intent.putExtra(EXTRA_TEXT, username.getText().toString());
                                startActivity(intent);
                            },
                            error -> {
                                Log.e("AuthQuickStart", "Sign up failed", error);
                                String invalidParameter = Objects.requireNonNull(error.getCause()).toString();
                                invalidParameter = invalidParameter.substring(invalidParameter.indexOf(": ") + 1, invalidParameter.indexOf("(") - 1);
                                errorText = invalidParameter;
                                errorPopup.show();
                                //showAlertDialog(R.layout.error_popup, invalidParameter);
                            }
                    );
                } catch (Exception e) {
                    Log.e("AuthQuickStart", "Sign up failed", e);
                    //showAlertDialog(R.layout.error_popup, e);
                }
                */
            }
        });
    }

    public void registerUser(String username, String email, String password) {
        System.out.println(username);
        System.out.println("password accepted");
        //result.setText("Hello "+name.getText());

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build();
        try {
            Amplify.Auth.signUp(username, password, options,
                    result -> {
                        Log.i("AuthQuickStart", "Result: " + result);

                        assert result.getUser() != null;
                        String userID = Objects.requireNonNull(result.getUser().getUserId());
                        System.out.println("User ID = " + userID);
                        //System.out.println(result.getUser().getUserId());

                        // Start Confirmation Activity
                        Intent intent = new Intent(this, Confirm.class);
                        intent.putExtra(EXTRA_TEXT, username);
                        startActivity(intent);
                    },
                    error -> {
                        Log.e("AuthQuickStart", "Sign up failed", error);
                        String invalidParameter = Objects.requireNonNull(error.getCause()).toString();
                        invalidParameter = invalidParameter.substring(invalidParameter.indexOf(": ") + 1, invalidParameter.indexOf("(") - 1);
                        errorText = invalidParameter;
                        //errorPopup.show();
                        //showAlertDialog(R.layout.error_popup, invalidParameter);
                    }
            );
        } catch (Exception e) {
            Log.e("AuthQuickStart", "Sign up failed", e);
            //showAlertDialog(R.layout.error_popup, e);
        }
    }

    //TODO Show alert dialog if the input parameters are invalid (username already in use, invalid email, or invalid password)
    private void showAlertDialog(int errorLayout, String error) {
        System.out.println(error);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Register.this);
        //View layoutView = getLayoutInflater().inflate(errorLayout, null);
        builder.setTitle("Error");
        builder.setMessage(error);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();


        /*
        builderDialog = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(errorLayout, null);

        //Looper.prepare();
        runOnUiThread(() -> {
            AppCompatButton dialogButton = layoutView.findViewById(R.id.errorOK_btn);
            builderDialog.setView(layoutView);
            alertDialog = builderDialog.create();
            alertDialog.show();

            TextView textView = findViewById(R.id.loginError);
            textView.setText(error);

            // On click
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss Dialog
                    alertDialog.dismiss();
                }
            });
        });*/
    }

    public void SendSessionData() {
        // TODO remove after testing
        /*
        Amplify.DataStore.observe(Todo.class,
                started -> Log.i("AppSync", "Secure AppSync Observation began."),
                change -> Log.i("AppSync", change.item().toString()),
                failure -> Log.e("AppSync", "Secure AppSync Observation failed.", failure),
                () -> Log.i("AppSync", "Secure AppSync Observation complete.")
        );

        Todo item = Todo.builder()
                .name(username.getText().toString())
                .priority(Priority.NORMAL)
                .build();

        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );


        // TODO WITH FABIAN -> Send necessary data upon login
        // Amplify.DataStore.observe(KeysAreUs.class);
        /* User user = User.builder()
                        .userid(userID)
                        .username(username)
                        .publicKey(publicKey)
                        .clientID(267uci6re8796okkmh99ijmaoh)
                */


    }
}