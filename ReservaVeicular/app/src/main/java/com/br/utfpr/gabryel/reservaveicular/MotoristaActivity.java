package com.br.utfpr.gabryel.reservaveicular;

import static android.os.AsyncTask.*;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.br.utfpr.gabryel.reservaveicular.fragment.DatePickerFragment;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;
import com.br.utfpr.gabryel.reservaveicular.model.enums.TipoCnh;
import com.br.utfpr.gabryel.reservaveicular.repository.ReservaVeicularDataBase;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MotoristaActivity extends AppCompatActivity {

    private Spinner spinnerCnh;
    private EditText nomeEdit;
    private EditText dtNascimentoEdit;
    private CheckBox checkAtivo;
    private RadioButton radioSim, radioNao;

    public static final String MODO = "MODO";
    public static final Integer NEW = 0;
    public static final Integer EDIT = 1;
    public static Integer MODO_ATIVO = -1;
    public static Long ID_MOTORISTA_EDIT = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);
        startCompomentes();
        spinnerCnh.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TipoCnh.descricaoList()));

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MODO_ATIVO = bundle.getInt(MODO, NEW);
        if (MODO_ATIVO == NEW)
            setTitle(getString(R.string.label_cadastro_novo));
        else {
            setTitle(getString(R.string.label_alterar_cadastro));
            atualizarCampos(bundle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_motorista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.salvar_motorista) {
            String mensagem = validarCampos();
            if (StringUtils.isNotBlank(mensagem))
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (menuItem.getItemId() == R.id.item_limpar) {
            nomeEdit.setText(null);
            dtNascimentoEdit.setText(null);
            checkAtivo.setChecked(false);
            radioSim.setChecked(false);
            radioNao.setChecked(false);
            spinnerCnh.setSelection(0);
            Toast.makeText(this, getString(R.string.info_limpar_campos), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public static void editarMotorista(AppCompatActivity activity, final Motorista motorista) {
        Intent intent = new Intent(activity, MotoristaActivity.class);
        intent.putExtra(MODO, EDIT);
        intent.putExtras(Motorista.motoristaParseBundle(motorista));
        activity.startActivityForResult(intent, EDIT);
    }

    public static void novoMotorista(AppCompatActivity activity) {
        Intent intent = new Intent(activity, MotoristaActivity.class);
        intent.putExtra(MODO, NEW);
        activity.startActivityForResult(intent, NEW);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(dtNascimentoEdit);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void validarEar(View v) {
        if (radioSim.isChecked()) radioNao.setChecked(false);
        if (radioNao.isChecked()) radioSim.setChecked(false);
    }

    private String validarCampos() {
        String nome = nomeEdit.getText().toString();
        String dtNascinemto = dtNascimentoEdit.getText().toString();
        boolean possuiEar = radioSim.isChecked() == radioNao.isChecked();

        if (StringUtils.isBlank(nome)) {
            nomeEdit.requestFocus();
            return getString(R.string.info_nome, getString(R.string.info_obridatorio));
        }
        if (StringUtils.isBlank(dtNascinemto)) {
            dtNascimentoEdit.requestFocus();
            return getString(R.string.info_data_nascimento, getString(R.string.info_obridatorio));
        }
        if (possuiEar) {
            checkAtivo.requestFocus();
            return getString(R.string.info_possui_ear, getString(R.string.info_obridatorio));
        }

        salvar(nome, checkAtivo.isChecked(), radioSim.isChecked() ? radioSim.isChecked() : radioNao.isChecked(),
                LocalDate.parse(dtNascinemto, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                TipoCnh.values()[spinnerCnh.getSelectedItemPosition()]
        );
        return null;
    }

    private void salvar(String nome, boolean ativo, boolean possuiEar, LocalDate dtNascinemto, TipoCnh cnh) {

        Motorista motorista = new Motorista(ID_MOTORISTA_EDIT, nome, dtNascinemto, cnh, possuiEar, ativo);

        execute(() -> {
            ReservaVeicularDataBase database = ReservaVeicularDataBase.getDatabase(MotoristaActivity.this);
            if (MODO_ATIVO == NEW)
                motorista.setId(database.motoristaDao().insert(motorista));
            else
                database.motoristaDao().update(motorista);
        });
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void startCompomentes() {
        spinnerCnh = findViewById(R.id.spinnerCnh);
        nomeEdit = findViewById(R.id.editNomeMotorista);
        dtNascimentoEdit = findViewById(R.id.editDtNascimento);
        checkAtivo = findViewById(R.id.cadastroAtivo);
        radioSim = findViewById(R.id.radioSim);
        radioNao = findViewById(R.id.radioNao);
    }

    private void atualizarCampos(Bundle bundle) {
        if (bundle == null) return;
        Motorista motorista = new Motorista().bundleParseMotorista(bundle);
        ID_MOTORISTA_EDIT = motorista.getId();
        nomeEdit.setText(motorista.getNome());
        dtNascimentoEdit.setText(bundle.getString("dt_nascimento_motorista"));
        spinnerCnh.setSelection(getIndex(spinnerCnh, motorista.getCnh()));
        checkAtivo.setChecked(motorista.isAtivo());
        if (motorista.isPossuiEar())
            radioSim.setChecked(true);
        else
            radioNao.setChecked(true);
    }

    private int getIndex(Spinner spinner, TipoCnh cnh) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(cnh.getNome())) {
                return i;
            }
        }
        return 0;
    }
}