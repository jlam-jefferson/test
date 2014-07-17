package br.com.jlam;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class listaAdapter extends BaseAdapter {
	//torna visivel uma lista de locais para o user
	private List<Local> lista;
	private LayoutInflater mInflater;

	public listaAdapter(List<Local> lista, Context context) {
		super();
		this.lista=lista;
		this.mInflater= LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(lista!=null)
			return lista.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(lista!=null && lista.size()>position)
			return lista.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		if(lista!=null && lista.size()>position)
			return lista.get(position).hashCode();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//retorna o visao de um local da lista de locais pra o user
		// TODO Auto-generated method stub
		if(convertView==null){
			//caso uma visao n tenha sido criada anteriormente
			convertView= mInflater.inflate(R.layout.list_item, parent,false);
			((TextView)convertView.findViewById(R.id.textView1)).setText(lista.get(position).getNome());
			((TextView)convertView.findViewById(R.id.textView2)).setText(lista.get(position).getEnd());

		}
		convertView.setBackgroundResource(R.drawable.select_list);
		return convertView;
	}

}
