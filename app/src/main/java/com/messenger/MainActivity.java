package com.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), 100);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Welcome"+FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Toast.LENGTH_LONG).show();
            displayChatMessage();
        }
    }

    private void displayChatMessage()
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            if(resultCode==RESULT_OK)
            {
                Toast.makeText(MainActivity.this,"Successfully Registered, Welcome!",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
    }
}
