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

    public void updateContato(Contato contato, IContatoDAO callback){
        realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            Contato contatoAlterado = realm.copyToRealmOrUpdate(contato);
            realm.commitTransaction();

            if(contatoAlterado != null)
                callback.onSuccessUpdate();
            else
                callback.onError("Houve um erro ao tentar atualizar o registro.");
        }catch (Exception ex){
            ex.printStackTrace();
            callback.onError(ex.getMessage());
        }finally {
            realm.close();
        }
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
