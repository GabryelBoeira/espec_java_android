package com.br.utfpr.gabryel.reservaveicular;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private static final List<Motorista> motoristaList = new Motorista().criarBaseDados();
    private Integer cadastroSelecionado = -1;
    private MotoristaAdapter adapter;

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

    private void excluirMotorista(int posicao) {
        motoristaList.remove(posicao);
        Toast.makeText(this, R.string.info_removido_sucesso, Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
//        listViewMotoristas.setOnItemClickListener((parent, view, position, id) ->
//                editarMotorista(position, (Motorista) parent.getItemAtPosition(position))
//        );

        carregarInformacoesListView();
        registerForContextMenu(listViewMotoristas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item_sobre) {
            startActivity(new Intent(this, AppInfoActivity.class));
            return true;
        }
        if (menuItem.getItemId() == R.id.item_adicionar_motorista) {
            novoMotorista();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        getMenuInflater().inflate(R.menu.menu_flutuante_activity_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        var info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.item_alterar) {
            editarMotorista(info.position, motoristaList.get(info.position));
            return true;
        }
        if (item.getItemId() == R.id.item_excluir) {
            excluirMotorista(info.position);
            return true;
        }
        return super.onContextItemSelected(item);
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