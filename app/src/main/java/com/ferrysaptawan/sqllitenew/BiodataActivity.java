package com.ferrysaptawan.sqllitenew;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BiodataActivity extends AppCompatActivity {
    private DBHelper2 dbHelper;
    private TextView textViewData;

    Button hapus2;
    EditText edt2;
    DBHelper2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        dbHelper = new DBHelper2(this);
        textViewData = findViewById(R.id.textViewData);
        hapus2 = findViewById(R.id.btnhapus2);
        edt2 = findViewById(R.id.edtnim2);
        db = new DBHelper2(this);

        hapus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimToDelete = edt2.getText().toString();

                boolean isDeleted = db.deleteData(nimToDelete);

                if (isDeleted) {
                    Toast.makeText(BiodataActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(BiodataActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Cursor res = dbHelper.tampildata();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String nim = res.getString(0);
                String nama = res.getString(1);
                String jeniskelamin = res.getString(2);
                String alamat = res.getString(3);
                String email = res.getString(4);

                data.append("NIM : ").append(nim).append("\n");
                data.append("Nama : ").append(nama).append("\n");
                data.append("Jenis Kelamin : ").append(jeniskelamin).append("\n");
                data.append("Alamat : ").append(alamat).append("\n");
                data.append("Email : ").append(email).append("\n\n\n");
            }
            textViewData.setText(data.toString());
        }

        res.close();
    }

    private void loadData() {
        Cursor res = dbHelper.tampildata();
        if (res.getCount() == 0) {
            Toast.makeText(BiodataActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String nim = res.getString(0);
                String nama = res.getString(1);
                String jeniskelamin = res.getString(2);
                String alamat = res.getString(3);
                String email = res.getString(4);

                data.append("NIM : ").append(nim).append("\n");
                data.append("Nama : ").append(nama).append("\n");
                data.append("Jenis Kelamin : ").append(jeniskelamin).append("\n");
                data.append("Alamat : ").append(alamat).append("\n");
                data.append("Email : ").append(email).append("\n\n\n");
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
