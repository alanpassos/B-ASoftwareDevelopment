package br.com.servicor.sd.servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GenericFactory<T> {
	/**
	 * metodo responsavel por transformar o json no objeto para a persistencia
	 *
	 * gerarObjeto
	 * 
	 * @author P. Alan <alanpassossi@gmail.com>
	 * @param <T>
	 * @create 29 de out de 201521:37:45
	 * @param json
	 * @return CategoriaModel
	 */
	public T gerarObjeto(Class classe, String json) {
		Gson gson = new Gson();
		// gson = new GsonBuilder()
		// .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		JsonParser parser = new JsonParser();
		JsonObject jObject = parser.parse(json).getAsJsonObject();
		return gson.fromJson(jObject, classe);
	}

	public String returnJson(T t) {
		Gson gson = new Gson();

		return gson.toJson(t);
	}

	public ArrayList<T> gerarLista(Class classe, String json) {
		JsonParser parser = new JsonParser();
		JsonArray jArray = parser.parse(json).getAsJsonArray();
		ArrayList<T> array = new ArrayList<>();
		for (JsonElement elemento : jArray) {
			array.add(gerarObjeto(classe, elemento.toString()));
		}

		return array;
	}

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}