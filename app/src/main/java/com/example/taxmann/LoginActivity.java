package com.example.taxmann;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5; //5 login attempts in total

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Info = (TextView)findViewById(R.id.textAttempts);
        Login = (Button)findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view){
             validate(Name.getText().toString(), Password.getText().toString());
         }
        });
    }

    private void validate(String userName, String userPass){
        if((userName == "Admin") && (userPass == "1234")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            counter--;
            if (counter == 0) {
                Login.setEnabled(false);    //disable the login button
            }
        }
    }
}
