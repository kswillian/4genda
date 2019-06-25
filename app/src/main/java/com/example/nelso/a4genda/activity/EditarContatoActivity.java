package com.example.nelso.a4genda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nelso.a4genda.DAO.ContatoDAO;
import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.model.Contato;
import com.orhanobut.hawk.Hawk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditarContatoActivity extends AppCompatActivity {

    private EditText nome, telefone, celular, aniversario, filiacao;
    private ContatoDAO dao;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contato);

        if(Hawk.contains("contato"))
            contato = Hawk.get("contato");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nome = findViewById(R.id.et_editar_nome);
        telefone = findViewById(R.id.et_editar_telefone);
        celular = findViewById(R.id.et_editar_celular);
        aniversario = findViewById(R.id.et_editar_aniversario);
        filiacao = findViewById(R.id.et_editar_filiacao);

        telefone.addTextChangedListener(MaskWatcher.insert(MaskWatcher.TELEFONE_MASK, telefone));
        celular.addTextChangedListener(MaskWatcher.insert(MaskWatcher.CELULAR_MASK, celular));
        aniversario.addTextChangedListener(MaskWatcher.insert(MaskWatcher.ANIVERSARIO_MASK, aniversario));

        nome.setText(contato.getNome());
        telefone.setText(String.valueOf(contato.getTelefone()));
        celular.setText(String.valueOf(contato.getCelular()));
        aniversario.setText(String.valueOf(contato.getAniversario()));
        filiacao.setText(contato.getFiliacao());

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

            editarContato(contato);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void editarContato(Contato contato){
        contato.setNome(MaskWatcher.unmask(nome.getText().toString()));
        contato.setTelefone(Long.parseLong(MaskWatcher.unmask(telefone.getText().toString())));
        contato.setCelular(Long.parseLong(MaskWatcher.unmask(celular.getText().toString())));
        contato.setAniversario(Long.parseLong(MaskWatcher.unmask(aniversario.getText().toString())));
        contato.setFiliacao(filiacao.getText().toString());

        dao.updateContato(contato);
        Intent intent = new Intent(EditarContatoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
