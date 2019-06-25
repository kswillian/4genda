package com.example.nelso.a4genda.DAO;

import com.example.nelso.a4genda.model.Contato;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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
            realm.copyToRealm(contato);
            realm.commitTransaction();
            realm.close();
    }

    public void updateContato(Contato contato){
        realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(contato);
            realm.commitTransaction();
            realm.close();
    }

    public void queryContato(IContatoDAO callback){
        realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            RealmQuery<Contato> query = realm.where(Contato.class);
            RealmResults<Contato> results = query.findAll();
            List<Contato> contatos = realm.copyFromRealm(results);
            callback.onSuccessList(contatos);
            realm.commitTransaction();
        } catch (Exception exception){
            exception.printStackTrace();
            callback.onError(exception.getMessage());
        }finally {
            realm.close();
        }
    }

    public void queryContatoById(long contatoId){

    }

    public void queryContatoByName(String query, IContatoDAO callback2){
        realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            RealmResults<Contato> resultados = realm.where(Contato.class).contains("nome", query).findAll();
            callback2.onSuccessList(resultados);
            realm.commitTransaction();
        } catch (Exception exception){
            exception.printStackTrace();
            callback2.onError(exception.getMessage());
        }finally {
            realm.close();
        }
    }

    public void removeContato(long contatoId, IContatoDAO callback) {
        realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();

            Contato contatoDeletar = realm.where(Contato.class).equalTo("id", contatoId).findFirst();
            if(contatoDeletar != null)
                contatoDeletar.deleteFromRealm();

            realm.commitTransaction();

            if(contatoDeletar != null)
                callback.onSuccessRemove();
            else
                callback.onError("Erro ao tentar excluir");

        } catch (Exception ex){
            ex.printStackTrace();
            callback.onError(ex.getMessage());
        }finally {
            realm.close();
        }


    }

}
