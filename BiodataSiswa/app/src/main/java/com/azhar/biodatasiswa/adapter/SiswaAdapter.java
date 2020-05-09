package com.azhar.biodatasiswa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.SiswaModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Azhar Rivaldi on 06/05/2020.
 */

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private final Context context;
    private final List<SiswaModel> siswaModelList;
    private Bundle bundle;
    private String firstName;
    private SiswaDatabase siswaDatabase;

    public SiswaAdapter(Context context, List<SiswaModel> siswaModelList) {
        this.context = context;
        this.siswaModelList = siswaModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // memindahkan data yang dipilih ke dalam list
        final SiswaModel siswaModel = siswaModelList.get(position);

        // Menampilkan data ke layar
        holder.txtNameSiswa.setText(siswaModel.getNama_siswa());

        // Mengambil huruf pertama
        String nama = siswaModel.getNama_siswa();
        if (!nama.isEmpty()) {
            firstName = nama.substring(0, 1);
        } else {
            firstName = " ";
        }

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().buildRound(firstName, color);
        holder.imgView.setImageDrawable(drawable);

        // Membuat onclick icon overflow
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Buat object database
                siswaDatabase = siswaDatabase.createDatabase(context);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure delete " + siswaModel.getNama_siswa() + " ?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Melakukan operasi delete data
                        siswaDatabase.kelasDao().deleteSiswa(siswaModel);

                        // Menghapus data yang telash di hapus pada List
                        siswaModelList.remove(position);

                        // Memberitahu bahwa data sudah hilang
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(0, siswaModelList.size());

                        Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return siswaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view)
        ImageView imgView;
        @BindView(R.id.txt_name_siswa)
        TextView txtNameSiswa;
        @BindView(R.id.btnDelete)
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
