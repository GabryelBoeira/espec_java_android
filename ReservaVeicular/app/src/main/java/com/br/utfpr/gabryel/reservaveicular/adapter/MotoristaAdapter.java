package com.br.utfpr.gabryel.reservaveicular.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.utfpr.gabryel.reservaveicular.R;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MotoristaAdapter extends BaseAdapter {

    private Context contexto;
    private List<Motorista> motoristaList;

    private static class MotoristaLayout {
        public TextView textViewNome;
        public TextView textViewDtNascimento;
        public TextView textViewCnh;
        public TextView textViewAtivo;
    }

    public MotoristaAdapter(final Context contexto, final List<Motorista> motoristaList) {
        this.contexto = contexto;
        this.motoristaList = motoristaList;
    }

    @Override
    public int getCount() {
        return motoristaList.size();
    }

    @Override
    public Object getItem(int i) {
        return motoristaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicao, View view, ViewGroup grupo) {
        MotoristaLayout layout;
        Motorista motorista = (Motorista) getItem(posicao);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_adapter_view_motorista, grupo, false);

            layout = new MotoristaLayout();
            layout.textViewNome = view.findViewById(R.id.info_nome_motorista);
            layout.textViewDtNascimento = view.findViewById(R.id.info_dt_nascimento);
            layout.textViewCnh = view.findViewById(R.id.info_cnh);
            layout.textViewAtivo = view.findViewById(R.id.info_ativo);

            view.setTag(layout);
        } else {
            layout = (MotoristaLayout) view.getTag();
        }

        layout.textViewNome.setText(motorista.getNome());
        layout.textViewDtNascimento.setText(motorista.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        layout.textViewCnh.setText(contexto.getString(R.string.info_cnh, motorista.getCnh().name()));
        layout.textViewAtivo.setText(motorista.isAtivo() ? contexto.getString(R.string.label_ativo) : contexto.getString(R.string.label_inativo));
        return view;
    }
}