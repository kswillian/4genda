package com.example.nelso.a4genda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nelso.a4genda.DAO.ContatoDAO;
import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.activity.MainActivity;
import com.example.nelso.a4genda.model.Contato;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ContatoViewHolder> {

    private List<Contato> contatosList;
    private View v;
    private ContatoDAO dao;
    private MainActivity activity;

    public TodosAdapter(ContatoDAO dao, List<Contato> contatosList, MainActivity activity){
        this.dao = dao;
        this.contatosList = contatosList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_contatos, parent, false);
        return new ContatoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {

        Contato contato = contatosList.get(position);

        holder.tvContato.setText(contato.getNome());

        holder.llContato.setOnLongClickListener(v -> {
            new AlertDialog.Builder(activity)
                    .setTitle(R.string.apagar_contato)
                    .setMessage(R.string.apagar_contato_mensagem).setPositiveButton(R.string.confirmacao, (dialog, which) -> {
                dao.removeContato(contato.getId());
            })
                    .setNegativeButton(R.string.negacao, null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ContatoViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llContato;
        TextView tvContato;

        ContatoViewHolder(View itemView) {
            super(itemView);

            tvContato = itemView.findViewById(R.id.tv_contato);
            llContato = itemView.findViewById(R.id.ll_contato);
        }
    }
}
