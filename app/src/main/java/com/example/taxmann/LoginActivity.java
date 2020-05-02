package com.example.taxmann;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5; //5 login attempts in total
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog; //login may take time, so show progress. ProgressDialog is deprecated, so may need to change

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Info = (TextView)findViewById(R.id.textAttempts);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser(); //check if user has already logged into the app
        if(user!=null){ //if user logged in, then move on to MainActivity
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view){
             validate(Name.getText().toString(), Password.getText().toString());
         }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

       private void validate(String userName, String userPass){

           progressDialog.setMessage("Validation in Progress");
           progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(userName, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;
//                        Info.setText("No of attempts remaining: "+ String.valueOf(counter));
                        if (counter == 0) Login.setEnabled(false);    //disable the login button
                    }
                }
            });


        //simple case login
//        if((userName.equals("Admin"))  && (userPass.equals("1234"))){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }else{
//            counter--;
//            Info.setText("No of attempts remaining: "+ String.valueOf(counter));
//            if (counter == 0) {
//                Login.setEnabled(false);    //disable the login button
//            }
//        }
    }
}
