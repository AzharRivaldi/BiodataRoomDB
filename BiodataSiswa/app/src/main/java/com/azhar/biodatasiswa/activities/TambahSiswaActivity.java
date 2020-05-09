package com.azhar.biodatasiswa.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.database.Constant;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.SiswaModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahSiswaActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaSiswa)
    EditText edtNamaSiswa;
    @BindView(R.id.edtUmur)
    EditText edtUmur;
    @BindView(R.id.radio_laki)
    RadioButton radioLaki;
    @BindView(R.id.radio_perempuan)
    RadioButton radioPerempuan;
    @BindView(R.id.edtAsal)
    EditText edtAsal;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.radio_jenis_kelamin)
    RadioGroup radioJenisKelaminGroup;

    SiswaDatabase siswaDatabase;
    int id_kelas;
    String namaSiswa, asal, umur, jenis_kelamin, email;
    boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_siswa);
        ButterKnife.bind(this);

        Toolbar tbDetailDokter = findViewById(R.id.toolbar);
        tbDetailDokter.setTitle("Tambah Siswa");
        setSupportActionBar(tbDetailDokter);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        id_kelas = getIntent().getIntExtra(Constant.KEY_ID_KELAS, 0);

        siswaDatabase = SiswaDatabase.createDatabase(this);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {

        // Memastikan semuanya terisi
        cekData();

        if (!empty) {
            saveData();
            clearData();
            Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Masih ada data yang kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearData() {
        edtNamaSiswa.setText("");
        edtUmur.setText("");
        edtAsal.setText("");
        edtEmail.setText("");
        radioJenisKelaminGroup.clearCheck();
    }

    private void saveData() {

        // Membuat penampung dengan membaut object SiswaModel
        SiswaModel siswaModel = new SiswaModel();

        // kita masukkan data ke dalam siswaModel
        siswaModel.setId_kelas(id_kelas);
        siswaModel.setNama_siswa(namaSiswa);
        siswaModel.setAsal(asal);
        siswaModel.setUmur(umur);
        siswaModel.setJenis_kelamin(jenis_kelamin);
        siswaModel.setEmail(email);

        // Kita lakukan operasi insert
        siswaDatabase.kelasDao().insertSiswa(siswaModel);
    }

    private void cekData() {
        namaSiswa = edtNamaSiswa.getText().toString();
        asal = edtAsal.getText().toString();
        umur = edtUmur.getText().toString();
        email = edtEmail.getText().toString();

        empty = namaSiswa.isEmpty() || asal.isEmpty() || umur.isEmpty() || email.isEmpty() || jenis_kelamin.isEmpty();
    }

    @OnClick({R.id.radio_laki, R.id.radio_perempuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_laki:
                jenis_kelamin = radioLaki.getText().toString();
                break;
            case R.id.radio_perempuan:
                jenis_kelamin = radioPerempuan.getText().toString();
                break;
        }
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
