package com.br.utfpr.gabryel.reservaveicular;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private static final List<Motorista> motoristaList = new Motorista().criarBaseDados();
    private Integer cadastroSelecionado = -1;
    private MotoristaAdapter adapter;
    private ActionMode carregarAcoes;
    private View viewSelecionadaAcao;

    private static final String ARQUIVO_PREFERENCIA_KEY = "com.br.utfpr.gabryel.reservaveicular.sharedPreferences";
    private static final String MODO = "MODO";
    private static final String SELECIONADO = "SELECIONADO";

    private void carregarInformacoesListView() {
        listViewMotoristas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMotoristas.setOnItemClickListener((parent, view, position, id) ->
                editarMotorista(position, (Motorista) parent.getItemAtPosition(position))
        );

        listViewMotoristas.setOnItemLongClickListener((parent, view, position, id) -> {
            if (carregarAcoes != null) return false;
            cadastroSelecionado = position;
            viewSelecionadaAcao = view;
            listViewMotoristas.setEnabled(false);
            carregarAcoes = startSupportActionMode(action);
            return true;
        });

        adapter = new MotoristaAdapter(this, motoristaList);
        listViewMotoristas.setAdapter(adapter);
    }

    private ActionMode.Callback action = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.menu_flutuante_activity_main, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            var info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (item.getItemId() == R.id.item_alterar) {
                editarMotorista(cadastroSelecionado, motoristaList.get(cadastroSelecionado));
                mode.finish();
                return true;
            }
            if (item.getItemId() == R.id.item_excluir) {
                excluirMotorista(cadastroSelecionado);
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionadaAcao != null)
                viewSelecionadaAcao.setBackgroundColor(Color.TRANSPARENT);
            carregarAcoes = null;
            viewSelecionadaAcao = null;
            listViewMotoristas.setEnabled(true);
        }
    };

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

        SharedPreferences preferencia = getSharedPreferences(ARQUIVO_PREFERENCIA_KEY, Context.MODE_PRIVATE);
        AppCompatDelegate.setDefaultNightMode(preferencia.getInt(MODO, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));
        setTheme(preferencia.getBoolean(SELECIONADO, false) ? R.style.Theme_ReservaVeicular_dark :  R.style.Theme_ReservaVeicular_light);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
        carregarInformacoesListView();
        registerForContextMenu(listViewMotoristas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences preferencia = getSharedPreferences(ARQUIVO_PREFERENCIA_KEY, Context.MODE_PRIVATE);
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        menu.getItem(2).setChecked(preferencia.getBoolean(SELECIONADO, false));
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
        if (menuItem.getItemId() == R.id.item_modo_noturno) {
            if (menuItem.isChecked()) {
                menuItem.setChecked(false);
                atualizarPreferenciarModoNoturno(AppCompatDelegate.MODE_NIGHT_NO, false);
            } else {
                menuItem.setChecked(true);
                atualizarPreferenciarModoNoturno(AppCompatDelegate.MODE_NIGHT_YES, true);
            }
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void atualizarPreferenciarModoNoturno(final int isModoNoturno, boolean check) {
        SharedPreferences.Editor editor = getSharedPreferences(ARQUIVO_PREFERENCIA_KEY, Context.MODE_PRIVATE).edit();
        editor
                .putInt(MODO, isModoNoturno)
                .putBoolean(SELECIONADO, check)
                .apply();
        AppCompatDelegate.setDefaultNightMode(isModoNoturno);
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