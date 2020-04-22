package com.example.loginsignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignIn;
    TextView tvsignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.button2);
        tvsignUp = findViewById(R.id.textView);
        mFirebaseAuth = FirebaseAuth.getInstance();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             mFirebaseAuth.signInWithEmailAndPassword(emailId.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                 @Override
                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                     if (task.isSuccessful()){
                                                      if (mFirebaseAuth.getCurrentUser().isEmailVerified()){
                                                          Toast.makeText(LogInActivity.this,"You are Logged In",Toast.LENGTH_SHORT).show();
                                                          Intent i = new Intent(LogInActivity.this,HomeActivity.class);
                                                          startActivity(i);
                                                      }else {
                                                          Toast.makeText(LogInActivity.this,"Please Verify your email Address",Toast.LENGTH_SHORT).show();
                                                      }
                                                     }else{
                                                         Toast.makeText(LogInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             });
                                         }
                                     }
        );
        tvsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this,MainActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAuth.addAuthStateListener(authStateListener);
//    }
}
