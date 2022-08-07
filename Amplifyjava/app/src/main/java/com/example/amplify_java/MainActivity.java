package com.example.amplify_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.AWSDataStorePlugin;

/*
import com.amplifyframework.datastore.generated.model.Priority;
import com.amplifyframework.datastore.generated.model.Todo;
 */

import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        // TODO CHECK IF USER IS SIGNED IN PAGE
        /* TODO direct authenticated traffic
            if isSignUpComplete = true
            Result: AuthSignUpResult{isSignUpComplete=true, nextStep=AuthNextSignUpStep{signUpStep=CONFIRM_SIGN_UP_STEP, additionalInfo={}, codeDeliveryDetails=AuthCodeDeliveryDetails{destination='a***@g***', deliveryMedium=EMAIL, attributeName='email'}}, user=AuthUser{userId='be476df9-27e5-4c69-a7d4-437431f7b7cf', username='test'}}
        */

        /* TODO if (something like this) -> AuthSignUpResult{isSignUpComplete = true}
            startActivity(new Intent(MainActivity.this, LoginUI.class));
            else startActivity(new Intent(LoginUI.this, Register.class));
         */




























        /*    Amplify.DataStore.observe(Todo.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

         Todo item = Todo.builder()
                .name("Build Android application")
                .priority(Priority.NORMAL)
                .build();

        /* Amplify.DataStore.query(Todo.class,
            Where.matches(Todo.PRIORITY.eq(Priority.HIGH)),
            todos -> {
                while (todos.hasNext()) {
                    Todo todo = todos.next();

                    Log.i("Tutorial", "==== Todo ====");
                    Log.i("Tutorial", "Name: " + todo.getName());

                    if (todo.getPriority() != null) {
                        Log.i("Tutorial", "Priority: " + todo.getPriority().toString());
                    }

                    if (todo.getCompletedAt() != null) {
                        Log.i("Tutorial", "CompletedAt: " + todo.getCompletedAt().toString());
                    }
                }
            },
            failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );

        /*
        try {
            //Amplify.addPlugin(new AWSApiPlugin()); // uncomment this when backend is deployed
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Amplify", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("Amplify", "Could not initialize Amplify", error);
        }

        Amplify.DataStore.save(
                item,
                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
                error -> Log.e("Amplify", "Could not save item to DataStore", error)
        );
        */
    }
}