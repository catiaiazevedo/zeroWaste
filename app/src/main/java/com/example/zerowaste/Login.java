package com.example.zerowaste;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLEngineResult;

public class Login extends AppCompatActivity{
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "Login";
    SignInButton signInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private CollectionReference collRef = FirebaseFirestore.getInstance().collection("users");
    User user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    // Set the dimensions of the sign-in button.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new User();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
            private void signIn() {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }


        });



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);



            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG,"signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    //Change UI according to user data.
    public void updateUI(GoogleSignInAccount account){

        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            user.setEmail(account.getEmail());
            user.setFname(account.getGivenName());
            user.setLname(account.getFamilyName());
            writeNewPost(user.getFname(),user.getLname(),user.getEmail(), user.getId());
            startActivity(new Intent(this,MainActivity.class));

        }else {
            Toast.makeText(this,"U Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }

    private void writeNewPost(String fname,String lname, String email, int id) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        Map<String, Object> usermap = new HashMap<>();
        usermap.put("id",id);
        usermap.put("fname",fname);
        usermap.put("lname",lname);
        usermap.put("email",email);


        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("users/" + id ,usermap);
        collRef.add(childUpdates);
    }




}
