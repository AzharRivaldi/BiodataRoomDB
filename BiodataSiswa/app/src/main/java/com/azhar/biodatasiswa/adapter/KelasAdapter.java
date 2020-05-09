package com.azhar.biodatasiswa.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.azhar.biodatasiswa.R;
import com.azhar.biodatasiswa.activities.ListSiswaActivity;
import com.azhar.biodatasiswa.activities.UpdateKelasActivity;
import com.azhar.biodatasiswa.database.Constant;
import com.azhar.biodatasiswa.database.SiswaDatabase;
import com.azhar.biodatasiswa.model.KelasModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Azhar Rivaldi on 06/05/2020.
 */

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    private final Context context;
    private final List<KelasModel> kelasModelList;
    private SiswaDatabase siswaDatabase;
    private Bundle bundle;

    public KelasAdapter(Context context, List<KelasModel> kelasModelList) {
        this.context = context;
        this.kelasModelList = kelasModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_kelas, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Memindahkan data di dalam list dengan index position ke dalam KelasModel
        final KelasModel kelasModel = kelasModelList.get(position);

        // Menampilkan data ke layar
        holder.tvNamaWali.setText(kelasModel.getNama_wali());
        holder.tvNamaKelas.setText(kelasModel.getNama_kelas());

        ColorGenerator generator = ColorGenerator.MATERIAL;

        // generate random color
        int color = generator.getRandomColor();
        holder.cvKelas.setCardBackgroundColor(color);

        // Onlick pada itemview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();

                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                context.startActivity(new Intent(context, ListSiswaActivity.class).putExtras(bundle));
            }
        });

        // Membuat onclick icon overflow
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // Buat object database
                siswaDatabase = siswaDatabase.createDatabase(context);

                // Membuat object popumemu
                PopupMenu popupMenu = new PopupMenu(context, view);

                // Inflate menu ke dalam popupmenu
                popupMenu.inflate(R.menu.popup_menu);

                // Menampilkan menu
                popupMenu.show();

                // Onclick pada salah satu menu pada popupmenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete:

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                alertDialogBuilder.setMessage("Are you sure delete " + kelasModel.getNama_kelas() + " ?");
                                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        // Melakukan operasi delete data
                                        siswaDatabase.kelasDao().delete(kelasModel);

                                        // Menghapus data yang telash di hapus pada List
                                        kelasModelList.remove(position);

                                        // Memberitahu bahwa data sudah hilang
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(0, kelasModelList.size());

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

                                break;

                            case R.id.edit:

                                // Membuat object bundle
                                bundle = new Bundle();

                                // Mengisi bundle dengan data
                                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                                bundle.putString(Constant.KEY_NAMA_KELAS, kelasModel.getNama_kelas());
                                bundle.putString(Constant.KEY_NAMA_WALI, kelasModel.getNama_wali());

                                // Berpindah halaman dengan membawa data
                                context.startActivity(new Intent(context, UpdateKelasActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelasModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNamaKelas)
        TextView tvNamaKelas;
        @BindView(R.id.tvNamaWali)
        TextView tvNamaWali;
        @BindView(R.id.cvKelas)
        CardView cvKelas;
        @BindView(R.id.overflow)
        ImageButton overflow;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
