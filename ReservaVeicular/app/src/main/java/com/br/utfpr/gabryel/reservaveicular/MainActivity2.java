package com.br.utfpr.gabryel.reservaveicular;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.utfpr.gabryel.reservaveicular.adapter.MotoristaAdapter;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

public class MainActivity2 extends AppCompatActivity {

    private ListView listViewMotoristas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewMotoristas = findViewById(R.id.listViewMotorista);
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
    }


}