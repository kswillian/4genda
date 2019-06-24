package com.example.nelso.a4genda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nelso.a4genda.DAO.ContatoDAO;
import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.model.Contato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NovoContatoActivity extends AppCompatActivity {

    private EditText nome, telefone, celular, aniversario, filiacao;
    private ContatoDAO dao;

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

        telefone.addTextChangedListener(MaskWatcher.insert(MaskWatcher.TELEFONE_MASK, telefone));
        celular.addTextChangedListener(MaskWatcher.insert(MaskWatcher.CELULAR_MASK, celular));
        aniversario.addTextChangedListener(MaskWatcher.insert(MaskWatcher.ANIVERSARIO_MASK, aniversario));

        dao = new ContatoDAO();

        Button salvarButton = findViewById(R.id.btn_salvar);
        salvarButton.setOnClickListener(v -> {
            if(nome.getText().toString().equals("")) {
                nome.setError(getResources().getString(R.string.aviso));
            } else if (telefone.getText().toString().equals("")) {
                telefone.setError(getResources().getString(R.string.aviso));
            } else if (celular.getText().toString().equals("")) {
                celular.setError(getResources().getString(R.string.aviso));
            } else if (aniversario.getText().toString().equals("")) {
                aniversario.setError(getResources().getString(R.string.aviso));
            } else if(filiacao.getText().toString().equals("")) {
                filiacao.setError(getResources().getString(R.string.aviso));
            }

            novoContato();
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
        Intent intent = new Intent(NovoContatoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
