package com.br.utfpr.gabryel.reservaveicular;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    public static void editarMotorista(AppCompatActivity activity, final Motorista motorista) {
        Intent intent = new Intent(activity, MotoristaActivity.class);
        intent.putExtra(MODO, EDIT);
        intent.putExtras(putBundle(motorista));
        activity.startActivityForResult(intent, EDIT);
    }

    public static void novoMotorista(AppCompatActivity activity) {
        Intent intent = new Intent(activity, MotoristaActivity.class);
        intent.putExtra(MODO, NEW);
        activity.startActivityForResult(intent, NEW);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);
        startCompomentes();

        spinnerCnh.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TipoCnh.descricaoList()));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int modo = bundle.getInt(MODO, NEW);
        if (modo == NEW)
            setTitle(getString(R.string.title_cadastro_motorista));
        else {
            setTitle(getString(R.string.title_alterar_motorista));
            atualizarCampos();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(dtNascimentoEdit);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void validarEar(View v) {
        if (radioSim.isChecked()) radioNao.setChecked(false);
        if (radioNao.isChecked()) radioSim.setChecked(false);
    }

    public void cancelarEdicao(View v) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void validarAlteracoes(View v) {
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
        intent.putExtra("id_motorista", motorista.getId());
        intent.putExtra("nome_motorista", motorista.getNome());
        intent.putExtra("dt_nascimento_motorista", motorista.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        intent.putExtra("cnh_motorista", motorista.getCnh().getKey());
        intent.putExtra("ear_motorista", motorista.isPossuiEar());
        intent.putExtra("ativo_motorista", motorista.isAtivo());
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

    private static Bundle putBundle(Motorista motorista) {
        Bundle bundle = new Bundle();
        bundle.putInt("id_motorista", motorista.getId());
        bundle.putString("nome_motorista", motorista.getNome());
        bundle.putString("dt_nascimento_motorista", motorista.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        bundle.putString("cnh_motorista", motorista.getCnh().getKey());
        bundle.putBoolean("ear_motorista", motorista.isPossuiEar());
        bundle.putBoolean("ativo_motorista", motorista.isAtivo());
        return bundle;
    }

    private void startCompomentes() {
        spinnerCnh = findViewById(R.id.spinnerCnh);
        nomeEdit = findViewById(R.id.editNomeMotorista);
        dtNascimentoEdit = findViewById(R.id.editDtNascimento);
        checkAtivo = findViewById(R.id.cadastroAtivo);
        radioSim = findViewById(R.id.radioSim);
        radioNao = findViewById(R.id.radioNao);
    }

    private void atualizarCampos() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null) return;

        int id = bundle.getInt("id_motorista");
        String nome = bundle.getString("nome_motorista");
        String dtNascinemto = bundle.getString("dt_nascimento_motorista");
        TipoCnh tipoCnh = TipoCnh.valueOf(bundle.getString("cnh_motorista"));
        boolean ear = bundle.getBoolean("ear_motorista");
        boolean ativo = bundle.getBoolean("ativo_motorista");

        if (StringUtils.isNotBlank(nome)) nomeEdit.setText(nome);
        if (StringUtils.isNotBlank(dtNascinemto)) dtNascimentoEdit.setText(dtNascinemto);
        spinnerCnh.setSelection(getIndex(spinnerCnh, tipoCnh));
        checkAtivo.setChecked(ativo);
        if (ear) {
            radioSim.setChecked(true);
            radioNao.setChecked(false);
        } else {
            radioSim.setChecked(false);
            radioNao.setChecked(true);
        }

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