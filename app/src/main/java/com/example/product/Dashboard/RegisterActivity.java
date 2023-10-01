package com.example.product.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.product.HelperMethod.DBHelper;
import com.example.product.R;

public class RegisterActivity extends AppCompatActivity {


    EditText et_reg_password, et_reg_email, et_reg_mobile_number, et_reg_name;
    Button btn_reg_register;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_reg_email = findViewById(R.id.et_reg_email);
        et_reg_password = findViewById(R.id.et_reg_password);
        et_reg_mobile_number = findViewById(R.id.et_reg_mobile_number);
        et_reg_name = findViewById(R.id.et_reg_name);
        btn_reg_register = findViewById(R.id.btn_reg_register);

        dbHelper = new DBHelper(this);


        et_reg_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });

        btn_reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredName = et_reg_name.getText().toString().trim();
                String enteredMobileNumber = et_reg_mobile_number.getText().toString().trim();
                String enteredEmail = et_reg_email.getText().toString().trim();
                String enteredPassword = et_reg_password.getText().toString().trim();

                boolean isUserAdded = dbHelper.addUser(enteredName, enteredMobileNumber, enteredEmail, enteredPassword);

                if (!isUserAdded) {
                    Toast.makeText(RegisterActivity.this, "Error: User not added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "User" +
                            " added successfully", Toast.LENGTH_SHORT).show();
                    et_reg_name.setText("");
                    et_reg_mobile_number.setText("");
                    et_reg_email.setText("");
                    et_reg_password.setText("");

                    Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToLogin);

                }
            }
        });


    }
}