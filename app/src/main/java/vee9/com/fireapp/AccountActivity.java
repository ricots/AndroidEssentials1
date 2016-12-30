package vee9.com.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private Button mLogOutBtn;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth=FirebaseAuth.getInstance();

        user=mAuth.getCurrentUser();

        mLogOutBtn=(Button)findViewById(R.id.logoutBtn);

        StringBuilder sb=new StringBuilder();
        sb.append(user.getDisplayName()).append(user.getEmail()).append(user.getUid());

        Toast.makeText(this, ""+ sb.toString(), Toast.LENGTH_SHORT).show();
        user.sendEmailVerification();

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null) {
                    Toast.makeText(AccountActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AccountActivity.this, MainActivity.class));
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }
}
