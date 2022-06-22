package com.br.utfpr.gabryel.reservaveicular;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private static final List<Motorista> motoristaList = new Motorista().criarBaseDados();
    private Integer cadastroSelecionado = -1;
    private MotoristaAdapter adapter;
    FloatingActionButton fab;

    private void carregarInformacoesListView() {
        adapter = new MotoristaAdapter(this, motoristaList);
        listViewMotoristas.setAdapter(adapter);
    }

    private void editarMotorista(final int position, Motorista motorista) {
        this.cadastroSelecionado = position;
        MotoristaActivity.editarMotorista(this, motorista);
    }

    private void novoMotorista() {
        MotoristaActivity.novoMotorista(this);
    }

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

        fab = findViewById(R.id.fab_novo_motorista);
        fab.setOnClickListener(view -> novoMotorista());
        carregarInformacoesListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item_sobre) {
            startActivity(new Intent(this, AppInfoActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
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