package com.azhar.biodatasiswa.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.azhar.biodatasiswa.model.KelasModel;
import com.azhar.biodatasiswa.model.SiswaModel;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 06/05/2020.
 */

@Dao
public interface KelasDao {

    // Mengambil data
    @Query("SELECT * FROM KELAS ORDER BY nama_kelas ASC")
    List<KelasModel> select();

    // Memasukkan data
    @Insert
    void insert(KelasModel kelasModel);

    // Menghapus data
    @Delete
    void delete(KelasModel kelasModel);

    // Mengupdate data
    @Update
    void update(KelasModel kelasModel);

    // Mengambil data siswa
    @Query("SELECT * FROM TB_SISWA WHERE id_kelas = :id_kelas ORDER BY nama_siswa ASC")
    List<SiswaModel> selectSiswa(int id_kelas);

    // Memasukkan data siswa
    @Insert
    void insertSiswa(SiswaModel siswaModel);

    // Menghapus data siswa
    @Delete
    void deleteSiswa(SiswaModel siswaModel);

    // Mengupdate data
    @Update
    void updateSiswa(SiswaModel siswaModel);
}
