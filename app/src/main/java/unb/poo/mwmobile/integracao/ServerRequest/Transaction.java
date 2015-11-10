package unb.poo.mwmobile.integracao.ServerRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import unb.poo.mwmobile.models.User;

/**
 * Interface que tem que ser implementada
 * para receber os dados do servidor
 *
 *
 * Created by Scartezini on 05/11/2015.
 */
public interface Transaction {


    /**
     * Atravez desse metodo o servidor vai mandar um
     * JsonArray para quem deu o requeste
     * nesse array vai esta a resposta do servidor
     *
     * @param jsonObject
     */
    public void doAfter(JSONObject jsonObject);
}
