package com.azhar.biodatasiswa.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.azhar.biodatasiswa.database.Constant;

/**
 * Created by Azhar Rivaldi on 06/05/2020.
 */

@Entity(tableName = "tb_siswa")

public class SiswaModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_siswa")
    private int id_siswa;

    @ColumnInfo(name = Constant.id_kelas)
    private int id_kelas;

    @ColumnInfo(name = "nama_siswa")
    private String nama_siswa;

    @ColumnInfo(name = "umur")
    private String umur;

    @ColumnInfo(name = "jenis_kelamin")
    private String jenis_kelamin;

    @ColumnInfo(name = "asal")
    private String asal;

    @ColumnInfo(name = "email")
    private String email;

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
