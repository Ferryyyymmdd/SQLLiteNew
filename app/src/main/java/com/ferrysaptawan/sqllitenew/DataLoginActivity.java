package com.ferrysaptawan.sqllitenew;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataLoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    EditText user;
    DBHelper db;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_login);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        delete = findViewById(R.id.delete);
        user = findViewById(R.id.username2);
        db = new DBHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userToDelete = user.getText().toString();
                boolean isDeleted = db.deleteData2(userToDelete);

                if (isDeleted) {
                    Toast.makeText(DataLoginActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(DataLoginActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loadData();
    }

    private void loadData() {
        Cursor res = dbHelper.tampildata2();
        if (res.getCount() == 0) {
            Toast.makeText(DataLoginActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String user = res.getString(0);
                String pass = res.getString(1);

                data.append("user: ").append(user).append("\n");
                data.append("Pass: ").append(pass).append("\n\n\n");
            }

            textViewData.setText(data.toString());
            res.close();
        }
    }

    private void refreshData() {
        textViewData.setText(""); // Menghapus teks sebelum memuat ulang data
        loadData(); // Memuat ulang data setelah penghapusan
    }
}
