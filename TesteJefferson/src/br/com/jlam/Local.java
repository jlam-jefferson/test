package br.com.jlam;
import com.google.android.gms.maps.model.LatLng;

public class Local {
	private String nome;
	private String end;
	private LatLng local;

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
