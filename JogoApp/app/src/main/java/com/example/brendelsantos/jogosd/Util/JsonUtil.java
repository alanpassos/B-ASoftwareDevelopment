package com.example.brendelsantos.jogosd.Util;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Brendel Santos on 18/05/2016.
 */
public class JsonUtil {

    private <T> ArrayList<T> parseLista(Class<T> tipo, String mensagem) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<T>>() {
        }.getType();
        ArrayList<T> listaObjetos = gson.fromJson(mensagem, collectionType);
        return listaObjetos;
    }

    public <T>ArrayList gerarLista(Class<T> tipo, String mensagem) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(mensagem).getAsJsonArray();
        ArrayList<T> array = new ArrayList<T>();
        for(JsonElement elemento: jArray){
            array.add((T) parseObjeto(tipo, elemento.toString()));
        }
        return array;
    }

    public <T> Object parseObjeto(Class<T> tipo, String jsonObjeto) {
        Gson gson = new Gson();
        T objeto = new Gson().fromJson(jsonObjeto, tipo);
        return objeto;
    }
}
