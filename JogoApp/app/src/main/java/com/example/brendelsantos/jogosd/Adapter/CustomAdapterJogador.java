package com.example.brendelsantos.jogosd.Adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.brendelsantos.jogosd.Model.Jogador;
import com.example.brendelsantos.jogosd.R;


/**
 * Created by Brendel Santos on 26/10/2015.
 */
public class CustomAdapterJogador extends BaseAdapter {

    Context context;
    List<Jogador> jogadores;

    public CustomAdapterJogador(Context context, List<Jogador> jogadores) {
        this.context = context;
        this.jogadores = jogadores;
    }

    @Override
    public int getCount() {
        return jogadores.size();
    }

    @Override
    public Object getItem(int position) {
        return jogadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return jogadores.indexOf(getItem(position));
    }

    private class ViewHolder {
        TextView nome;
        TextView informacao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_jogador, null);
            holder = new ViewHolder();
            holder.nome = (TextView) convertView.findViewById(R.id.nome_jogador);
            holder.informacao = (TextView) convertView
                    .findViewById(R.id.informacao_jogador);

            Jogador jogadorPosicao = jogadores.get(position);
            holder.nome.setText("Nome: " + jogadorPosicao.getNome());
            holder.informacao.setText("Nível: " + jogadorPosicao.getNivel());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
