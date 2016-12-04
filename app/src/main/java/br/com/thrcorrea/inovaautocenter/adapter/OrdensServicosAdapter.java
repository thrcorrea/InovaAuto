package br.com.thrcorrea.inovaautocenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.thrcorrea.inovaautocenter.R;
import br.com.thrcorrea.inovaautocenter.model.OrdemServico;

/**
 * Created by thale on 29/11/2016.
 */

public class OrdensServicosAdapter extends BaseAdapter {

    private List<OrdemServico> ordensServicos;
    private final Context context;

    public OrdensServicosAdapter(Context context, List<OrdemServico> ordensServicos) {
        this.context = context;
        this.ordensServicos = ordensServicos;
    }

    public void setOrdensServicos(List<OrdemServico> ordensServicos) {
        this.ordensServicos = ordensServicos;
    }

    @Override
    public int getCount() {
        return ordensServicos.size();
    }

    @Override
    public Object getItem(int position) {
        return ordensServicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OrdemServico ordemServico = ordensServicos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.item_lista_os, parent, false);
        }

        TextView campoNumeroOs = (TextView) view.findViewById(R.id.item_lista_numero_os);
        TextView campoCarro = (TextView) view.findViewById(R.id.item_lista_carro);
        TextView campoData = (TextView) view.findViewById(R.id.item_lista_data);
        TextView campoStatus = (TextView) view.findViewById(R.id.item_lista_status_os);

        campoNumeroOs.setText(ordemServico.getNumeroOs());
        campoCarro.setText(ordemServico.getCarro());
        campoData.setText(ordemServico.getData());
        campoStatus.setText(ordemServico.getStatus());

        return view;
    }


}
