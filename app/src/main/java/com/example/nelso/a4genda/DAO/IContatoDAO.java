package com.example.nelso.a4genda.DAO;

import com.example.nelso.a4genda.model.Contato;

import java.util.List;

public interface IContatoDAO {
    void onSuccessSave();
    void onSuccessUpdate();
    void onSuccessRemove();
    void onSuccessList(List<Contato> lista);
    void onSuccessQueryId(Contato contato);
    void onError(String mensagemErro);
}
