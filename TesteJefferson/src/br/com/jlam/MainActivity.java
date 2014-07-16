package br.com.jlam;

import java.util.List;

import com.google.android.gms.internal.fm;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
 
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MotionEvent;
 
public class MainActivity extends FragmentActivity {
private GoogleMap supportMap;
private SlidingPaneLayout slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fmanager = getSupportFragmentManager();
        Fragment fragment = fmanager.findFragmentById(R.id.map);
        slider=(SlidingPaneLayout)findViewById(R.id.conteudo);
        slider.openPane();
        SupportMapFragment supportmapfragment = (SupportMapFragment)fragment;
        supportMap = supportmapfragment.getMap();
        supportMap.setMyLocationEnabled(true);
        supportMap.getUiSettings().setMyLocationButtonEnabled(true);

//
    }
    void addLocal(LatLng local,String nome,String end){
    	MarkerOptions marker = new MarkerOptions();
    	marker.position(local).title(nome);
    	marker.snippet(end);
    	
    	supportMap.addMarker(marker);
    }
    void selecionarLocal(LatLng local){
    	
    	CameraPosition cameraPosition = new CameraPosition.Builder().target(local).zoom(12).build();
    	supportMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    	Point point =supportMap.getProjection().toScreenLocation(local);

        slider.closePane();

    
    }
 }
   
 
