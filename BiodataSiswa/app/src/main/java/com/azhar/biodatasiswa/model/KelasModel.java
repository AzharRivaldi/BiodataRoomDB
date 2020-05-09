package com.azhar.biodatasiswa.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.azhar.biodatasiswa.database.Constant;

/**
 * Created by Azhar Rivaldi on 06/05/2020.
 */

@Entity(tableName = Constant.nama_table)

public class KelasModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.id_kelas)
    private int id_kelas;

    @ColumnInfo(name = Constant.nama_kelas)
    private String nama_kelas;

    @ColumnInfo(name = Constant.nama_wali)
    private String nama_wali;

    public int getId_kelas() {
        return id_kelas;
    }

    public void setId_kelas(int id_kelas) {
        this.id_kelas = id_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_wali() {
        return nama_wali;
    }

    public void setNama_wali(String nama_wali) {
        this.nama_wali = nama_wali;
    }
}
