package com.br.utfpr.gabryel.reservaveicular;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
        fab = findViewById(R.id.fab);
        carregarInformacoesListView();
        carregarInformacoesFab();
    }

    private void carregarInformacoesListView() {
        MotoristaAdapter adapter = new MotoristaAdapter(this, new Motorista().criarBaseDados());
        listViewMotoristas.setAdapter(adapter);

        listViewMotoristas.setOnItemClickListener((parent, view, position, id) -> {
            final Motorista motorista = (Motorista) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, MotoristaActivity.class);

            intent.putExtra("id_motorista", motorista.getId());
            intent.putExtra("nome_motorista", motorista.getNome());
            intent.putExtra("dt_nascimento_motorista", motorista.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            intent.putExtra("cnh_motorista", motorista.getCnh().getKey());
            intent.putExtra("ear_motorista", motorista.isPossuiEar());
            intent.putExtra("ativo_motorista", motorista.isAtivo());

            startActivity(intent);
        });
    }

    private void carregarInformacoesFab() {
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, MotoristaActivity.class);
            startActivity(intent);
        });
    }

}