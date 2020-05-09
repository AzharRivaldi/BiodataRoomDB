package com.azhar.biodatasiswa.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.database.Constant;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.KelasModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    Bundle bundle;
    SiswaDatabase siswaDatabase;
    int id_kelas;
    String nama_kelas, nama_wali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kelas);
        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Update Data");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();

        // Buat object database
        siswaDatabase = SiswaDatabase.createDatabase(this);

        // Menampilkan data sebelumnya ke layar
        showData();
    }

    private void showData() {
        // mengambil data di dalam bundle
        id_kelas = bundle.getInt(Constant.KEY_ID_KELAS);
        nama_kelas = bundle.getString(Constant.KEY_NAMA_KELAS);
        nama_wali = bundle.getString(Constant.KEY_NAMA_WALI);

        // Menampilkan ke layar
        edtNamaKelas.setText(nama_kelas);
        edtNamaWali.setText(nama_wali);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {

        // Mengambil data
        getData();

        // Mengirim data ke sqlite
        saveData();

        Toast.makeText(this, "Berhasil di update", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void getData() {
        // Mengambil inputan user dan dimasukkan ke dalam variable
        nama_kelas = edtNamaKelas.getText().toString();
        nama_wali = edtNamaWali.getText().toString();
    }

    private void saveData() {

        // Membuat object kelasmodel
        KelasModel kelasModel = new KelasModel();

        // Memasukkan data ke kelasmodel
        kelasModel.setId_kelas(id_kelas);
        kelasModel.setNama_kelas(nama_kelas);
        kelasModel.setNama_wali(nama_wali);

        // Melakukan operasi update untuk mengupdate data ke sqlite
        siswaDatabase.kelasDao().update(kelasModel);
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
