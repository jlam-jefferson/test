package br.com.jlam;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class AsyncListaLocal extends AsyncTaskLoader<List<Local>> {
//comunicacao parelela com o servidor para pregar os locais

	public AsyncListaLocal(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	@Override
	public List<Local> loadInBackground() 
	{

		List<Local> result = new ArrayList<Local>();
		try {
			//conetando o servidor
			URL u = new URL("http://www.mesainc.com.br/testes/geolocalizacao/localidades.json");
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			//lendo o conteudo
			InputStream is = conn.getInputStream();
			byte[] b = new byte[50000];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ( is.read(b) != -1){
				baos.write(b);
			}
			
			//interpretando o arquivo json
			String JSONResp = new String(baos.toByteArray());
			JSONArray arr = new JSONArray(JSONResp);
			for (int i=1; i < arr.length(); i++) {
				result.add(getLocal(arr.getJSONObject(i)));//add lista de locais
			}

			return result;//retornando lista de locais
		}
		catch(Throwable t) {
			t.printStackTrace();
		}

		return null;//conexao falhou
	}

	private Local getLocal(JSONObject jsonObject) throws JSONException {//trasforma uma classe no json em uma classe java
		
		return new Local(jsonObject.getString("name"), jsonObject.getString("address"), jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"));
	}





}
