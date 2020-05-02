package com.example.taxmann;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword; //not related to the IDs
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth; //firebase authenticator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();  //create an object

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //uplaod data to the db
                    String user_email = userEmail.getText().toString().trim();  //trim() to remove any whitespaces
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //tell the user if successful
                                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

            }
        });
    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etName);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
    }

    private Boolean validate(){
        Boolean result = false;
        String name = userName.getText().toString();
        String pass = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if (name.isEmpty() || pass.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }

        return result;
    }
}
