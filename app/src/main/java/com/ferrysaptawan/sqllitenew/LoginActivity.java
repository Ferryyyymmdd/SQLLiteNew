package com.ferrysaptawan.sqllitenew;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnSignIn, btnrahasia;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.signin);
        btnrahasia = findViewById(R.id.rahasia);


        DB = new DBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Semua Field Wajib Diisi", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUserPw = DB.checkUsernamePassword(user, pass);
                    if (checkUserPw) {
                        Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login Gagal. Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnrahasia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.tampildata2();
                if (res.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), DataLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
