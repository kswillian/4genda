package com.example.nelso.a4genda.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nelso.a4genda.R;
import com.example.nelso.a4genda.model.Contato;
import com.orhanobut.hawk.Hawk;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Contato contato = Hawk.get("contato");

        Toolbar toolbar = findViewById(R.id.toolbar_contato);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView nomeContato = findViewById(R.id.tv_toolbar_nome);
        TextView telefoneContato = findViewById(R.id.tv_telefone);
        TextView celularContato = findViewById(R.id.tv_celular);
        TextView aniversarioContato = findViewById(R.id.tv_aniversario);
        TextView filiacaoContato = findViewById(R.id.tv_filiacao);
        TextView editarButton = findViewById(R.id.tv_toolbar_editar);

        nomeContato.setText(contato.getNome());
        telefoneContato.setText(String.format(Locale.getDefault(), "Telefone: %s", MaskWatcher.maskTextView(String.valueOf(contato.getTelefone()), "(##) ####-####")));
        celularContato.setText(String.format(Locale.getDefault(), "Celular: %s", MaskWatcher.maskTextView(String.valueOf(contato.getCelular()), "(##) #####-####")));
        aniversarioContato.setText(String.format(Locale.getDefault(), "Aniversário: %s", MaskWatcher.maskTextView(String.valueOf(contato.getAniversario()), "##/##/####")));
        filiacaoContato.setText(String.format(Locale.getDefault(), "Filiação: %s", contato.getFiliacao()));

        editarButton.setOnClickListener(v -> {
            //do nothing
        });

        Hawk.delete("contato");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
