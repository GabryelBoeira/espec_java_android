package com.br.utfpr.gabryel.reservaveicular;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
        int modo = bundle.getInt(MODO, NEW);
        if (modo == NEW)
            setTitle(getString(R.string.title_cadastro_motorista));
        else {
            setTitle(getString(R.string.title_alterar_motorista));
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
            validarAlteracoes();
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

    private void validarAlteracoes() {
        String mensagem;
        String nome = nomeEdit.getText().toString();
        String dtNascinemto = dtNascimentoEdit.getText().toString();
        mensagem = validarEInformar(nome, dtNascinemto, radioSim.isChecked() == radioNao.isChecked());

        if (StringUtils.isNotBlank(mensagem)) {
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            return;
        }

        Motorista motorista = new Motorista(nome,
                LocalDate.parse(dtNascinemto, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                TipoCnh.values()[spinnerCnh.getSelectedItemPosition()],
                radioSim.isChecked() ? radioSim.isChecked() : radioNao.isChecked(),
                checkAtivo.isChecked());

        Intent intent = new Intent();
        intent.putExtras(Motorista.motoristaParseBundle(motorista));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private String validarEInformar(String nome, String dtNascinemto, boolean possuiEar) {
        if (StringUtils.isBlank(nome))
            return getString(R.string.info_nome, getString(R.string.info_obridatorio));
        if (StringUtils.isBlank(dtNascinemto))
            return getString(R.string.info_data_nascimento, getString(R.string.info_obridatorio));
        if (possuiEar)
            return getString(R.string.info_possui_ear, getString(R.string.info_obridatorio));
        return null;
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
        Motorista motorista = new Motorista().bundleParseMotorista(bundle, bundle.getInt("id_motorista"));

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