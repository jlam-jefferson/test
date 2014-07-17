package br.com.jlam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ListaFragment extends ListFragment implements OnLoadCompleteListener<List<Local>>, OnMarkerClickListener {
	private listaAdapter adap;
	private TextView titulo;
	private AsyncListaLocal asyn;
	private Map<String,Integer> markerToPosi;
	private ListView listView;
	//fragmento com  a lista de locais
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// TODO Auto-generated method stub
		//inicializando variaveis
		asyn = new  AsyncListaLocal(getActivity());
		markerToPosi=new HashMap<String, Integer>();
		listView=getListView();

		//configurando a visao da lista de locais
		listView.setBackgroundResource(R.drawable.select_list);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		//titulo do mapa
		titulo=(TextView) getActivity().findViewById(R.id.titulo_mapa);
		titulo.setText("selecione um local para ver no mapa");

		//requisita a lista de locais do servidor
		asyn.registerListener(1, this);
		asyn.startLoading();
		asyn.forceLoad();


	}

	@Override
	public void onLoadComplete(Loader<List<Local>> arg0, final List<Local> arg1) {
		// TODO Auto-generated method stub
		//chamada ao se termminar a requisicao com o servidor

		if(arg1==null || getView()==null){//requisicao falhou tentar novamente
			asyn.cancelLoad();
			asyn.reset();
			asyn.forceLoad();
			return;
		}
		//caso tenha sucesso execute isso na thread principal
		getView().post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				adap= new listaAdapter(arg1,getActivity());
				setListAdapter(adap);//inserir uma visao para a lista de locais do servidor

				int id=0;
				//para cada local da lista 
				for (Iterator<Local> iterator = arg1.iterator(); iterator.hasNext();) {
					Local local = (Local) iterator.next();
					//inserir marcador no mapa para o local e salvar qual o marcardor deste item
					local.marcador=((MainActivity)getActivity()).addLocal(local.getLocal(),local.getNome(),local.getEnd());
					//inseri salvar de qual item desse marcador
					markerToPosi.put(local.marcador.getId(), id++);
				}			
			}
		});

	}
	@Override
	public void onListItemClick(ListView lv,View v, int posi, long id){
		//chamada quando um item da lista de locais e clicado

		Local escolha = (Local)adap.getItem(posi);
		((MainActivity)getActivity()).selecionarLocal(escolha.getLocal());

		//mostra a informacao do marcardor
		escolha.marcador.showInfoWindow();

		//destacar item clicadona lista
		listView.setItemChecked(posi, true);
		listView.setSelection(posi);

		//subistituir a tela da imagem do local
		SupportStreetViewPanoramaFragment imagem = SupportStreetViewPanoramaFragment.newInstance(new StreetViewPanoramaOptions().position(escolha.getLocal()));
		getFragmentManager().beginTransaction().replace(R.id.imagen, imagem).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

		//subistituir titulo do mapa
		if(titulo!=null)
			titulo.setText(escolha.getNome());

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		//chamado quando um marcardor no mapa e clicado
		if(markerToPosi.containsKey(marker.getId())){
			//resgatar o item da lista de locais q possue esse marcardor
			int id= markerToPosi.get(marker.getId());
			onListItemClick(getListView(),getListView().getChildAt(id),id,0);//clicar item na lista
		}
		return false;
	}
}
