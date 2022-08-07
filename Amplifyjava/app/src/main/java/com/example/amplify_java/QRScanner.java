package com.example.amplify_java;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

// Graphql Framework
/*
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.CreateNewSessionID;
import com.amplifyframework.datastore.generated.model.initializeSessionType;
*/
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class QRScanner extends AppCompatActivity {
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        btnScan = findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(v-> {
            try {
                userSession("hello");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Place QR in frame");
        //options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        launchScan.launch(options);
    }

    ActivityResultLauncher<ScanOptions> launchScan = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QRScanner.this);
            builder.setTitle("Scan Successful");
            builder.setMessage("Session ID: " + result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // userSession("12345abc"); // This will be result.getContents()
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });


    private void userSession(String contentsQR) throws JSONException {
        System.out.println("QR CODE: " + contentsQR);

        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
                            assert cognitoAuthSession.getUserPoolTokens().getValue() != null;
                            System.out.println("ID Token: " + cognitoAuthSession.getUserPoolTokens().getValue().getIdToken());
                            System.out.println("Access Token: " + cognitoAuthSession.getUserPoolTokens().getValue().getAccessToken());
                            break;
                        case FAILURE:
                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }
                },
                error -> Log.e("AuthQuickStart", error.toString())
        );

        RestOptions options = RestOptions.builder()
                .addPath("/getsystempublickeys")
                .build();

        Amplify.API.get(options,
                restResponse -> {
                    Log.i("AmplifyApp", "GET STATUS: " + restResponse.getCode());
                    String str = null;
                    try {
                        str = new String(restResponse.getData().getRawBytes(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("RETURNED: " + str);
                    // TextView textView = findViewById(R.id.returnedValue);
                    // textView.setText("Returned Config: " + str);
                },
                apiFailure -> Log.e("Amplify App", "GET failed.", apiFailure)
        );

        // String userId = Amplify.Auth.getCurrentUser().getUserId();
        // String sessionID = contentsQR;
        String username = Amplify.Auth.getCurrentUser().getUsername();
        String ECCKeyId = "ECCKEYIDSTRING";
        String ECCPublicKey = "ECCPUBLICKEY";
        int EPOCHTime = 1864322016;
        String JSONAPI = null;
        try {
            // CREATE JSON OBJECT
            final JSONObject JSONAPIO = new JSONObject();

            // BUILD JSON OBJECT
            JSONAPIO.put("user_id", username);
            JSONAPIO.put("ecc_key_id", ECCKeyId);
            JSONAPIO.put("ecc_public_key", ECCPublicKey);
            JSONAPIO.put("ttl", EPOCHTime);

            // CONVERT JSON TO STRING
            JSONAPI = JSONAPIO.toString();

        } catch (JSONException e) {
            Log.e("Amplify App", "Failed to create JSONObject", e);
        }

        System.out.println(JSONAPI);


        RestOptions postOptions = RestOptions.builder()
                .addPath("/send_user_keys")
                .addBody(JSONAPI.getBytes(StandardCharsets.UTF_8))
                .build();

        Amplify.API.post(postOptions,
                success -> {
                    Log.e("AmplifyApp", "POST successful: " + success);
                    System.out.println("RETURNED CODE: " + success.getCode());
                    try {
                        System.out.println("SENT STRING: " + new String(success.getData().getRawBytes(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.i("AmplifyApp", "POST failure" + error)
                );


        /*
        RestOptions authOptions = RestOptions.builder()
                .addPath("/session")
                .addBody(JSONAPI.getBytes())
                .build();

        Amplify.API.get(authOptions,
                restResponse -> {
                    Log.i("AmplifyApp", "GET STATUS: " + restResponse.getCode());
                    String str = null;
                    try {
                        str = new String(restResponse.getData().getRawBytes(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        // TextView textView = findViewById(R.id.returnedValue2);
                        // textView.setText("Returned Session: " + str);
                    }
                    System.out.println("RETURNED: " + str);
                },
                apiFailure -> Log.e("Amplify App", "GET failed.", apiFailure)

        );
        */
    }
        // TODO validate session data using the SystemPublicKey

            /*
            TODO user clicks a button to sign in via QR (This is the website)
                - Query to generate sessionID (3 minute live time) RSA_4096, ECC_NIST_P521
                - Query to GET sessionID (string that is hashed and signed)
                - Generate QR code
                - Host port 8080 on EC2 with an Apache webserver (Store website in there)

                TODO this will most likely be loaded in byte format
             TODO send login-request via API call
              {
              UserId, // in clear
              SystemKeyId, // in clear
              asymEncrypt(UserKeyId,RSA SystemPublicKey), // This is my key Id; System please decrypt it b64decode(ecc_private_key.encode(‘utf-8’))
              asymEncrypt(SessionId,ECC UserPrivateKey), // This is the session that I want to join. Decrypt it using my public key. You know it.
              sign(<all previous data>,ECC UserPrivateKey)
              }

              Todo item = Todo.builder()
                .name("Build Android application")
                .priority(Priority.NORMAL)
                .build();

        // String sessionID = contentsQR;
        String username = Amplify.Auth.getCurrentUser().getUsername();
        String userId = Amplify.Auth.getCurrentUser().getUserId();
        */
    /*
        initializeSessionType newSession = initializeSessionType.builder()
                .clearSessionId(contentsQR)
                .userId(userId)
                .username(username)
                .build();
                /*TODO Add keys later
                .userId("UserID")
                .systemKeyID("SystemKeyId")
                .userKeyIdEncryptedSystemPublicKey("RSA SystemPublicKey")
                .sessionIdEncryptedByUserPrivateKey("ECC UserPrivateKey")


        Amplify.API.mutate(ModelMutation.create(newSession),
                response -> Log.i("Initiation Request", "Session Initiation sent. Response: " + response.getData().toString()),
                error -> Log.e("Initiation Request", "Create Failed", error)
        );
    */
        // TODO grab and insert JWT token from Cognito
            /* TODO if user has an active public key
                StartQRScanner activity
              else
                create public key (expiring in 90 days) and send to system
         */
        /* TODO Send username, userID, and clientID as part of the payload in an api call to AppSync
            find how to create RSA keys here RSA_4096 -> https://www.javainterviewpoint.com/rsa-encryption-and-decryption/
            user.builder()
                .userid(userID)
                .username(username)
                .publicKey(publicKey)
                .clientID(267uci6re8796okkmh99ijmaoh)

            TODO pull ECC key to see if it's there (JSON)
                - 1ECC public key (With unique id and EPOCH time)
                - 1RSA public key (With unique id and EPOCH time)

            TODO store these keys (clear text is fine)


        // TODO Client ID -> https://docs.aws.amazon.com/cognito/latest/developerguide/user-pool-settings-client-apps.html
        // ClientID = 267uci6re8796okkmh99ijmaoh;
        */
     /*
    public class SessionInitiationRequest {
        private String encryptedString;
    }
    */
}