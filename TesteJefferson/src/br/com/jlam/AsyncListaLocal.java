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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class AsyncListaLocal extends AsyncTaskLoader<List<Local>> {


	public AsyncListaLocal(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	 

	  
@Override
public List<Local> loadInBackground() 
 {
	
		List<Local> result = new ArrayList<Local>();
		try {
			URL u = new URL("http://www.mesainc.com.br/testes/geolocalizacao/localidades.json");
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			InputStream is = conn.getInputStream();

			// Read the stream

			byte[] b = new byte[50000];

			ByteArrayOutputStream baos = new ByteArrayOutputStream();



			while ( is.read(b) != -1){
				Log.v("yep",""+b );

				baos.write(b);
				Log.v("yep",""+b );

			}
			String JSONResp = new String(baos.toByteArray());


			JSONArray arr = new JSONArray(JSONResp);

			for (int i=1; i < arr.length(); i++) {

				result.add(getLocal(arr.getJSONObject(i)));

			}



			return result;

		}

		catch(Throwable t) {

			t.printStackTrace();

		}

		return null;

	}

	private Local getLocal(JSONObject jsonObject) throws JSONException {
		return new Local(jsonObject.getString("name"), jsonObject.getString("address"), jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"));
	}





}
