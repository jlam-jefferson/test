package br.com.jlam;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Local {
	private String nome;
	private String end;
	private LatLng local;//endereco no mapa
	public Marker marcador;//marcador no mapa
//um local no servidor
	public Local(String nome, String end,double latitude,double longitude){
		this.nome=nome;
		this.end=end;

		this.local= new LatLng(latitude, longitude);

	}
	public String getNome(){

		return nome;
	}
	public	LatLng getLocal(){
		return local;

	}
	public	String getEnd(){
		return end;

	}
}
