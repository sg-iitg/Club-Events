package com.example.clubevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.FirestoreClient;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.clubevents.MESSAGE";
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private EditText emailET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailET= findViewById(R.id.email);
        passwordET= findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }
    private boolean validateForm(String email, String password) {
        boolean valid = true;

        if (TextUtils.isEmpty(email)) {
            emailET.setError("Required.");
            valid = false;
        } else {
            emailET.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            passwordET.setError("Required.");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }
    public void login(View view) {
        String email= emailET.getText().toString();
        String password = passwordET.getText().toString();
        signIn(email, password);
    }
    public void signup(View view) {
        String email= emailET.getText().toString();
        String password = passwordET.getText().toString();
        createAccount(email, password);
    }
    private void createAccount(String email, String password) {
        if (!validateForm(email, password))
        {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Signed Up Successfully",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this, ClubsList.class);
                            startActivity(intent);

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Enter a valid E-mail Id",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                })
    }
    private void signIn(String email, String password) {
        if (!validateForm(email, password))
        {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Logged In Successfully",
                                    Toast.LENGTH_SHORT).show();
                            String email= user.getEmail();
                            Intent intent=new Intent(MainActivity.this, ClubsList.class);
                            startActivity(intent);

                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Incorrect username or password",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}
