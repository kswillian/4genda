package com.example.nelso.a4genda.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contato extends RealmObject {

    @PrimaryKey
    private long id;
    private String nome;
    private long telefone;
    private long celular;
    private long aniversario;
    private String filiacao;

    public Contato(){}

    public Contato(long id, String nome, long telefone, long celular, long aniversario, String filiacao){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.aniversario = aniversario;
        this.filiacao = filiacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public long getAniversario() {
        return aniversario;
    }

    public void setAniversario(long aniversario) {
        this.aniversario = aniversario;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }
}
