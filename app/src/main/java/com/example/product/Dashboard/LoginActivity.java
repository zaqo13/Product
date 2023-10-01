package com.example.product.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.product.HelperMethod.DBHelper;
import com.example.product.HelperMethod.DBHelper_projection;
import com.example.product.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText et_login_email, et_login_password;
    Button btn_login, btn_register;
    DBHelper dbHelper;
    ImageButton ib_eye_pwd;
    Snackbar snackbar;

    DBHelper_projection.UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_email = findViewById(R.id.et_login_email);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        ib_eye_pwd = findViewById(R.id.ib_eye_pwd);


        dbHelper = new DBHelper(this);
//        userRepository.addUser("user_01", "1234567895", "sample@gmail.com", "password");


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = et_login_email.getText().toString().trim();
                String enteredPassword = et_login_password.getText().toString().trim();

/*                if(email.isEmpty() || password.isEmpty() || (email.isEmpty() && password.isEmpty())){
                    return -2;
                }*/

                if (!enteredEmail.isEmpty() && !enteredPassword.isEmpty()) {

                    int result = dbHelper.getUserEmail(enteredEmail, enteredPassword);

                    if (result == 1) {          // Indicates valid email and password
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else if (result == -1) {          // Indicates invalid password
                        snackbar = Snackbar.make(v, "Please entered correct password", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                snackbar.dismiss();
                            }
                        }, 2000);
                        //or
//                        Toast.makeText(LoginActivity.this, "Please entered correct password", Toast.LENGTH_SHORT).show();
                    } else if (result == 0) {               // Indicates user/email ID not found
                        Toast.makeText(LoginActivity.this, "User/email id not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please enter the email and password", Toast.LENGTH_SHORT).show();
                }

                et_login_email.setText("");
                et_login_password.setText("");
            }
        });


        ib_eye_pwd.setOnClickListener(new View.OnClickListener() {
            boolean passwordVisible = false;

            @Override
            public void onClick(View v) {
                if (passwordVisible) {
                    et_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordVisible = false;

                } else {
                    et_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    passwordVisible = true;

                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private boolean validateInput(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}


