package unb.poo.mwmobile.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scartezini on 10/11/2015.
 */
public class MencaoConfig {

    private final Map<String,Integer>  mencao = new HashMap<>();

    public MencaoConfig(){
        mencao.put("SR",0);
        mencao.put("II",1);
        mencao.put("MI",2);
        mencao.put("MM",3);
        mencao.put("MS",4);
        mencao.put("SS",5);
    }


    public int pesoMencao(String mencao){
        return this.mencao.get(mencao);
    }



}
