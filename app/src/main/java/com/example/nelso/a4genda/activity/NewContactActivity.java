package com.example.nelso.a4genda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nelso.a4genda.DAO.ContatoDAO;
import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.model.Contato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewContactActivity extends AppCompatActivity {

    private EditText nome, telefone, celular, aniversario, filiacao;
    private ContatoDAO dao;
    private NewContactActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nome = findViewById(R.id.et_nome);
        telefone = findViewById(R.id.et_telefone);
        celular = findViewById(R.id.et_celular);
        aniversario = findViewById(R.id.et_aniversario);
        filiacao = findViewById(R.id.et_filiacao);


        Button salvarButton = findViewById(R.id.btn_salvar);
        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().equals("")){
                    nome.setError(getResources().getString(R.string.aviso));
                }else if (telefone.getText().toString().equals("")){
                    telefone.setError(getResources().getString(R.string.aviso));
                }else if (celular.getText().toString().equals("")){
                    celular.setError(getResources().getString(R.string.aviso));
                }else if (aniversario.getText().toString().equals("")) {
                    aniversario.setError(getResources().getString(R.string.aviso));
                } else if(filiacao.getText().toString().equals("")){
                    filiacao.setError(getResources().getString(R.string.aviso));
                }else {
                    //activity.showLoading();
                    novoContato();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void novoContato(){
        Contato contato = new Contato();
        contato.setNome(nome.getText().toString());
        contato.setTelefone(Long.parseLong(telefone.getText().toString()));
        contato.setCelular(Long.parseLong(celular.getText().toString()));
        contato.setAniversario(Long.parseLong(aniversario.getText().toString()));
        contato.setFiliacao(filiacao.getText().toString());

        dao.salvarContato(contato);
        Intent intent = new Intent(NewContactActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
