package com.example.nelso.a4genda.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nelso.a4genda.DAO.ContatoDAO;
import com.example.nelso.a4genda.DAO.IContatoDAO;
import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.adapter.ContatoAdapter;
import com.example.nelso.a4genda.model.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements IContatoDAO {

    private List<Contato> contatoList;
    private ContatoDAO dao;
    protected ProgressDialog progressDialog;
    private TextView semContato;
    private RecyclerView rvContatos;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hawk.init(this).build();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        showLoading();
        FloatingActionButton fab = findViewById(R.id.fab);
        dao = new ContatoDAO();
        rvContatos = findViewById(R.id.rv_todos);
        semContato = findViewById(R.id.tv_sem_contatos);
        searchView = findViewById(R.id.searchview);

        dao.queryContato(MainActivity.this);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NovoContatoActivity.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                dao.queryContatoByName(query);
//                if(contatoList.contains()){
//
//                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void showLoading(){
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.carregando), true);
    }

    public void hideLoading(){
        if(progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onSuccessRemove() {
        Toast.makeText(MainActivity.this, getResources().getString(R.string.contato_excluido), Toast.LENGTH_SHORT).show();
        showLoading();
        dao.queryContato(MainActivity.this);
    }

    @Override
    public void onSuccessList(List<Contato> lista) {
        hideLoading();

        contatoList = lista;

        if(contatoList != null && contatoList.size() > 0){
            rvContatos.setVisibility(View.VISIBLE);
            semContato.setVisibility(View.GONE);
        }else{
            rvContatos.setVisibility(View.GONE);
            semContato.setVisibility(View.VISIBLE);
        }

        ContatoAdapter contatoAdapter = new ContatoAdapter(dao, contatoList, this);
        rvContatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvContatos.setAdapter(contatoAdapter);
    }

    @Override
    public void onError(String mensagemErro) {
        hideLoading();
        Toast.makeText(MainActivity.this, mensagemErro, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessQueryId(Contato contato) {
        //sem ação
    }

    @Override
    public void onSuccessSave() {
        //sem ação
    }

    @Override
    public void onSuccessUpdate() {
        //sem ação
    }
}
