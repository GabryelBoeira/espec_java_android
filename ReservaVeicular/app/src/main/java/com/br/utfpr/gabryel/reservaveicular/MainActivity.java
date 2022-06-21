package com.br.utfpr.gabryel.reservaveicular;

import android.os.Bundle;
import android.view.View;
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
import com.br.utfpr.gabryel.reservaveicular.utils.MontarUtils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private final MontarUtils montarUtils;
    private Spinner spinnerCnh;
    private EditText nomeEdit;
    private EditText dtNascimentoEdit;
    private CheckBox checkAtivo;
    private RadioButton radioSim, radioNao;

    public MainActivity() {
        this.montarUtils = new MontarUtils(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCnh = findViewById(R.id.spinnerCnh);
        spinnerCnh.setAdapter(montarUtils.carregarDadosSpinner(TipoCnh.descricaoList()));

        nomeEdit = findViewById(R.id.editNomeMotorista);
        dtNascimentoEdit = findViewById(R.id.editDtNascimento);
        checkAtivo = findViewById(R.id.cadastroAtivo);
        radioSim = findViewById(R.id.radioSim);
        radioNao = findViewById(R.id.radioNao);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(dtNascimentoEdit);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void validarEar(View v) {
        if (radioSim.isChecked()) radioNao.setChecked(false);
        if (radioNao.isChecked()) radioSim.setChecked(false);
    }

    public void limparCampos(View v) {
        nomeEdit.setText(null);
        dtNascimentoEdit.setText(null);
        checkAtivo.setChecked(false);
        radioSim.setChecked(false);
        radioNao.setChecked(false);
        spinnerCnh.setSelection(0);

        Toast.makeText(this, getString(R.string.info_limpar_campos), Toast.LENGTH_SHORT).show();
    }

    public void validarAlteracoes(View v) {
        String mensagem;
        String nome = nomeEdit.getText().toString();
        String dtNascinemto = dtNascimentoEdit.getText().toString();
        mensagem = validarEInformar(nome, dtNascinemto, radioSim.isChecked() == radioNao.isChecked());

        if (StringUtils.isBlank(mensagem)) {
            mensagem = salvar(nome, dtNascinemto, radioSim.isChecked() ? radioSim.isChecked() : radioNao.isChecked(),
                    TipoCnh.values()[spinnerCnh.getSelectedItemPosition()], checkAtivo.isChecked());

            if (mensagem.equalsIgnoreCase(getString(R.string.info_sucesso_salvar))) {
                nomeEdit.setText(null);
                dtNascimentoEdit.setText(null);
                checkAtivo.setChecked(false);
                radioSim.setChecked(false);
                radioNao.setChecked(false);
                spinnerCnh.setSelection(0);
            }
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private String salvar(String nome, String dtNascinemto, Boolean possuiEar, TipoCnh tipoCnh, boolean ativo) {
        try {
            Motorista motorista = new Motorista(nome, LocalDate.parse(dtNascinemto, DateTimeFormatter.ofPattern("dd/MM/yyyy")), tipoCnh, possuiEar,ativo);
            return motorista.getNome() != null ? getString(R.string.info_sucesso_salvar) : getString(R.string.info_erro_salvar);
        } catch (Exception e) {
            return getString(R.string.info_erro_interno);
        }
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

}