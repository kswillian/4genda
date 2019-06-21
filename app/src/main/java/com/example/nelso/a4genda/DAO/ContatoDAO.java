package com.example.nelso.a4genda.DAO;

import com.example.nelso.a4genda.model.Contato;

import io.realm.Realm;

public class ContatoDAO {

    private Realm realm;

    public ContatoDAO(){ }

    public void salvarContato(Contato contato){

        realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            long maxValue = 0;
            Number n = realm.where(Contato.class).max("id");
            if (n != null)
                maxValue = n.longValue();

            long pk = 0;
            if(maxValue == 0){
                maxValue = 1;
                pk = maxValue;
            }else if(maxValue >= 1){
                pk = maxValue + 1;
            }
            contato.setId(pk);
            realm.insert(contato);
            realm.commitTransaction();
            realm.close();
    }

    public void updateContato(Contato contato){

    }

    public void queryContato(long experimentId){

    }

    public void queryContatoById(long contatoId){

    }

    public void removeContato(long evaluationId) {

    }

}
