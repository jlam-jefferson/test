package br.com.jlam;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;

public class MainActivity extends FragmentActivity {
	private GoogleMap mapa;
	private SlidingPaneLayout slider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		FragmentManager fmanager = getSupportFragmentManager();
		Fragment fragment1 = fmanager.findFragmentById(R.id.map);
		Fragment fragment2 = fmanager.findFragmentById(R.id.list);


		slider=(SlidingPaneLayout)findViewById(R.id.conteudo);
		slider.openPane();

		//configurando mapa
		SupportMapFragment mapfragment = (SupportMapFragment)fragment1;
		mapa = mapfragment.getMap();
		mapa.setMyLocationEnabled(true);
		mapa.getUiSettings().setMyLocationButtonEnabled(true);
		mapa.setOnMarkerClickListener((ListaFragment)fragment2);

	}
	Marker addLocal(LatLng local,String nome,String end){
		//chamada para adicionar um local no mapa
		MarkerOptions marker = new MarkerOptions();
		marker.position(local).title(nome);
		marker.snippet(end);

		//retorna um marcardor no mapa
		return mapa.addMarker(marker);

	}
	void selecionarLocal(LatLng local){
		//chamada para visualizar um local no mapa
		CameraPosition cameraPosition = new CameraPosition.Builder().target(local).zoom(12).build();
		mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		slider.closePane();


	}

}


