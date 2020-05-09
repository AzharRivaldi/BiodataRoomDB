package com.azhar.biodatasiswa.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.KelasModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    SiswaDatabase siswaDatabase;
    String namaKelas, namaWali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Tambah Kelas");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        siswaDatabase = SiswaDatabase.createDatabase(this);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {

        getData();

        saveData();

        clearData();

        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void clearData() {
        edtNamaKelas.setText("");
        edtNamaWali.setText("");
    }

    private void saveData() {

        // Membuat object KelasModel untuk menampung data
        KelasModel kelasModel = new KelasModel();

        // Memasukkan data ke dalam KelasModel
        kelasModel.setNama_kelas(namaKelas);
        kelasModel.setNama_wali(namaWali);

        // Perintah untuk melakukan operasi Insert menggunakan siswaDatabase
        siswaDatabase.kelasDao().insert(kelasModel);
    }

    private void getData() {
        namaKelas = edtNamaKelas.getText().toString();
        namaWali = edtNamaWali.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
