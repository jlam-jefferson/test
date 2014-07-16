package br.com.jlam;

import java.util.Iterator;
import java.util.List;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListaFragment extends ListFragment implements OnLoadCompleteListener<List<Local>> {
	listaAdapter adap;
	@Override 
public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);
	// TODO Auto-generated method stub
	AsyncListaLocal asyn = new  AsyncListaLocal(getActivity());
	Log.v("uhu", "no1p");

	asyn.registerListener(1, this);
	Log.v("uhu", "nop2");

	asyn.startLoading();
	asyn.forceLoad();
	Log.v("uhu", "nop3"+ asyn.isStarted());


}

@Override
public void onLoadComplete(Loader<List<Local>> arg0, List<Local> arg1) {
	// TODO Auto-generated method stub
	
 adap= new listaAdapter(arg1,getActivity());
	setListAdapter(adap);
	for (Iterator<Local> iterator = arg1.iterator(); iterator.hasNext();) {
		Local local = (Local) iterator.next();
		((MainActivity)getActivity()).addLocal(local.getLocal(),local.getNome(),local.getEnd());
		
	}
}
@Override
public void onListItemClick(ListView lv,View v, int posi, long id){
	((MainActivity)getActivity()).selecionarLocal(((Local)adap.getItem(posi)).getLocal());
	
}
}
