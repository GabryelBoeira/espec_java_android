package com.br.utfpr.gabryel.reservaveicular;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMotoristas;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
        fab = findViewById(R.id.fab);
        carregarInformacoes();
    }

    private void carregarInformacoes() {
        MotoristaAdapter adapter = new MotoristaAdapter(this, new Motorista().criarBaseDados());
        listViewMotoristas.setAdapter(adapter);

        listViewMotoristas.setOnItemClickListener((parent, view, position, id) -> {
            final Motorista motorista = (Motorista) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),
                            getString(R.string.info_motorista_selecionado, motorista.getNome()), Toast.LENGTH_SHORT)
                    .show();
        });

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, MotoristaActivity.class);

            startActivity(intent);
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null).show();
        });
    }

}