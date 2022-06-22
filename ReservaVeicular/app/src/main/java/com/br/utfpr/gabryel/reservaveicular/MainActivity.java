package com.br.utfpr.gabryel.reservaveicular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private static final List<Motorista> motoristaList = new Motorista().criarBaseDados();
    private Integer cadastroSelecionado = -1;
    private MotoristaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
        listViewMotoristas.setOnItemClickListener((parent, view, position, id) ->
                editarMotorista(position, (Motorista) parent.getItemAtPosition(position))
        );
        listViewMotoristas.setOnItemLongClickListener((parent, view, position, id) -> {
            editarMotorista(position, (Motorista) parent.getItemAtPosition(position));
            return true;
        });

        carregarInformacoesListView();
    }

    private void carregarInformacoesListView() {
        adapter = new MotoristaAdapter(this, motoristaList);
        listViewMotoristas.setAdapter(adapter);
    }

    public void novoMotorista(View v) {
        MotoristaActivity.novoMotorista(this);
    }

    public void sobre(View v) {
        startActivity(new Intent(this, AppInfoActivity.class));
    }

    private void editarMotorista(final int position, Motorista motorista) {
        this.cadastroSelecionado = position;
        MotoristaActivity.editarMotorista(this, motorista);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();

            Motorista motoristaResult = new Motorista().bundleParseMotorista(bundle, cadastroSelecionado);
            if (requestCode == MotoristaActivity.EDIT)
                motoristaList.set(cadastroSelecionado, motoristaResult);
            else
                motoristaList.add(motoristaResult);
            adapter.notifyDataSetChanged();
        }
    }
}